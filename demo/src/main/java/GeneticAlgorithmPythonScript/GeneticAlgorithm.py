from flask import Flask, request, jsonify
from datetime import datetime, timedelta
import random
import numpy as np

app = Flask(__name__)
lastTime = datetime.now
charging_schedule = []
ifDone = False
globalCounter = 0


class Bus:
    def __init__(self, id, battery_health, battery_capacity, energy_consumption, route_length, battery_level):
        self.id = id
        self.battery_health = battery_health
        self.battery_capacity = battery_capacity
        self.energy_consumption = energy_consumption
        self.route_length = route_length
        self.battery_level = battery_level

    def consume_battery(self):
        self.battery_level -= self.route_length * self.energy_consumption * (self.battery_health / 100)

    def charge_battery(self, charge_amount):
        self.battery_level = min(self.battery_capacity, self.battery_level + charge_amount)

    def needs_charging(self):
        return self.battery_level < self.battery_capacity * 0.2


class ChargingStation:
    def __init__(self, id, output_power, num_of_ports):
        self.id = id
        self.output_power = output_power
        self.num_of_ports = num_of_ports
        self.bus_charging = {}  # {bus_id: (start_time, end_time)}
        print(f"ChargingStation {self.id} initialized with {self.num_of_ports} ports")

    def assign_bus(self, bus_id, start_time, charging_time):
        global ifDone
        global globalCounter
        globalCounter += 1
        print(globalCounter)
        if len(self.bus_charging) < self.num_of_ports:
            end_time = start_time + timedelta(minutes=charging_time)
            self.bus_charging[bus_id] = (start_time, end_time)
            print(f"Bus {bus_id} assigned to ChargingStation {self.id} from {start_time} to {end_time}")
            charging_schedule.append({
                "busId": bus_id,
                "chargingStationId": self.id,
                "chargingTime": charging_time,
                "startTime": format_time(start_time),
                "endTime": format_time(end_time)
            })
            return start_time, end_time
        else:
            if bus_id == 1:
                print("1 : " + str(ifDone))
                ifDone = True
                print("2 : " + str(ifDone))
                return None
            print(f"ChargingStation {self.id} has no available ports for Bus {bus_id}, but is put after")
            end_time = start_time + timedelta(minutes=charging_time)
            self.bus_charging[bus_id] = (start_time, end_time)
            print(f"Bus {bus_id} assigned to ChargingStation {self.id} from {start_time} to {end_time}")
            charging_schedule.append({
                "busId": bus_id,
                "chargingStationId": self.id,
                "chargingTime": charging_time,
                "startTime": format_time(start_time),
                "endTime": format_time(end_time)
            })
            return start_time, end_time


def initialize_population(pop_size, num_buses, max_time):
    population = []
    for _ in range(pop_size):
        individual = [random.randint(0, max_time) for _ in range(num_buses)]
        population.append(individual)
    return population


def fitness(individual, buses, charging_stations, current_time):
    total_charge_time = 0
    penalties = 0
    for i, charge_time in enumerate(individual):
        buses[i].consume_battery()
        if buses[i].needs_charging():
            assigned = False
            for station in charging_stations:
                bus_assigning = station.assign_bus(buses[i].id, current_time, charge_time)
                if bus_assigning is not None:
                    start_time, end_time = bus_assigning
                    total_charge_time += charge_time
                    assigned = True
                    break
            if not assigned:
                penalties += 100
    return total_charge_time + penalties


def selection(population, fitnesses, num_parents):
    fitness_probs = [1.0 / f if f > 0 else 0 for f in fitnesses]
    total_fitness = sum(fitness_probs)
    fitness_probs = [f / total_fitness for f in fitness_probs]

    population = np.array(population)
    fitness_probs = np.array(fitness_probs)

    parents_indices = np.random.choice(len(population), size=num_parents, p=fitness_probs)
    parents = [population[idx].tolist() for idx in parents_indices]
    return parents


def crossover(parent1, parent2):
    crossover_point = random.randint(1, len(parent1) - 1)
    child1 = parent1[:crossover_point] + parent2[crossover_point:]
    child2 = parent2[:crossover_point] + parent1[crossover_point:]
    return child1, child2


def mutate(individual, mutation_rate, max_time):
    for i in range(len(individual)):
        if random.random() < mutation_rate:
            individual[i] = random.randint(0, max_time)
    return individual


def create_new_population(parents, offspring):
    new_population = list(parents) + list(offspring)
    return new_population


def should_terminate(generations, max_generations):
    return generations >= max_generations


def format_time(dt):
    return dt.strftime('%Y-%m-%dT%H:%M:%S')


@app.route('/runGeneticAlgorithm', methods=['POST'])
def run_algorithm():
    global ifDone
    ifDone = False
    data = request.json
    max_time = data.get('max_time', 100)
    pop_size = data.get('pop_size', 200)
    num_generations = data.get('num_generations', 100)
    mutation_rate = data.get('mutation_rate', 0.1)
    num_parents = pop_size // 2

    buses = [Bus(bus['elBusId'], bus['batteryHealth'], bus['batteryCapacity'],
                 bus['energyConsumption'], bus['routeLength'], bus['batteryLevel'])
             for bus in data['buses']]

    charging_stations = [ChargingStation(station['chargerId'], station['outPutPower'],
                                         station['numOfPorts'])
                         for station in data['chargingStations']]

    num_buses = len(buses)

    population = initialize_population(pop_size, num_buses, max_time)
    generations = 0
    current_time = datetime.now()

    while not should_terminate(generations, num_generations) and not ifDone:
        for bus in buses:
            bus.battery_level = bus.battery_capacity
        for station in charging_stations:
            station.bus_charging.clear()

        fitnesses = np.array([fitness(ind, buses, charging_stations, current_time) for ind in population])
        parents = selection(population, fitnesses, num_parents)
        offspring = []
        for i in range(0, len(parents), 2):
            if i + 1 < len(parents):
                child1, child2 = crossover(parents[i], parents[i + 1
                ])
                offspring.append(mutate(child1, mutation_rate, max_time))
                offspring.append(mutate(child2, mutation_rate, max_time))
        population = create_new_population(parents, offspring)
        generations += 1

    for bus in buses:
        bus.battery_level = bus.battery_capacity
    for station in charging_stations:
        station.bus_charging.clear()

    best_individual = min(population, key=lambda ind: fitness(ind, buses, charging_stations, current_time))
    
    print(best_individual)
    return jsonify(best_individual)


if __name__ == "__main__":
    app.run(port=5000)
