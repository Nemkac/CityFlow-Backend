package com.example.demo.DTO;

public class EmploymentStatisticsDTO {
    private long employedCount;
    private long terminatedCount;

    public EmploymentStatisticsDTO() {
    }

    public EmploymentStatisticsDTO(long employedCount, long terminatedCount) {
        this.employedCount = employedCount;
        this.terminatedCount = terminatedCount;
    }

    public long getEmployedCount() {
        return employedCount;
    }

    public void setEmployedCount(long employedCount) {
        this.employedCount = employedCount;
    }

    public long getTerminatedCount() {
        return terminatedCount;
    }

    public void setTerminatedCount(long terminatedCount) {
        this.terminatedCount = terminatedCount;
    }
}
