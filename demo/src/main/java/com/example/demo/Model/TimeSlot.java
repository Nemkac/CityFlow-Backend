package com.example.demo.Model;

import java.time.LocalDate;

public class TimeSlot {
    private LocalDate start;
    private Integer duration;

    public TimeSlot(LocalDate start, Integer duration) {
        this.start = start;
        this.duration = duration;
    }

    public TimeSlot(){}

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
