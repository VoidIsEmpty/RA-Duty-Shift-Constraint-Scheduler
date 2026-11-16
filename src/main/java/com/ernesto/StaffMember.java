package com.ernesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StaffMember {

    private String id;
    private String name;
    private int maxShiftsPerMonth;
    private int assignedShiftsCount;
    private List<LocalDate> unavailableDates;

    public StaffMember(String id, String name, int maxShiftsPerMonth) {
        this.id = id;
        this.name = name;
        this.maxShiftsPerMonth = maxShiftsPerMonth;
        this.unavailableDates = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxShiftsPerMonth() {
        return maxShiftsPerMonth;
    }

    public void setMaxShiftsPerMonth(int maxShiftsPerMonth) {
        this.maxShiftsPerMonth = maxShiftsPerMonth;
    }

    public int getAssignedShiftsCount() {
        return assignedShiftsCount;
    }

    public void setAssignedShiftsCount(int assignedShiftsCount) {
        this.assignedShiftsCount = assignedShiftsCount;
    }

    public List<LocalDate> getUnavailableDates() {
        return Collections.unmodifiableList(unavailableDates);
    }

    public void setUnavailableDates(List<LocalDate> unavailableDates) {
        this.unavailableDates = new ArrayList<>(Objects.requireNonNull(unavailableDates));
    }

    public boolean isAvailable(LocalDate date) {
        return !unavailableDates.contains(date);
    }

    public void incrementAssignedShifts() {
        assignedShiftsCount++;
    }
}
