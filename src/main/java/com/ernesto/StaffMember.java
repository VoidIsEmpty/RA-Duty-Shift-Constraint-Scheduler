package com.ernesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a staff member who can be assigned shifts within a monthly limit.
 */
public class StaffMember {

    private String id;
    private String name;
    private int maxShiftsPerMonth;
    private int assignedShiftsCount;
    private List<LocalDate> unavailableDates;

    /**
     * Creates a staff member with a unique identifier, display name, and monthly shift cap.
     *
     * @param id the unique identifier for this staff member
     * @param name the staff member's display name
     * @param maxShiftsPerMonth the maximum number of shifts this staff member can be assigned in a month
     */
    public StaffMember(String id, String name, int maxShiftsPerMonth) {
        this.id = id;
        this.name = name;
        this.maxShiftsPerMonth = maxShiftsPerMonth;
        this.unavailableDates = new ArrayList<>();
    }

    /**
     * Returns the unique identifier of this staff member.
     *
     * @return the staff member ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of this staff member.
     *
     * @param id the new staff member ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the staff member's name.
     *
     * @return the staff member's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the staff member's name.
     *
     * @param name the new staff member name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the maximum number of shifts this staff member can work in a month.
     *
     * @return the monthly shift limit
     */
    public int getMaxShiftsPerMonth() {
        return maxShiftsPerMonth;
    }

    /**
     * Sets the maximum number of shifts this staff member can work in a month.
     *
     * @param maxShiftsPerMonth the new monthly shift limit
     */
    public void setMaxShiftsPerMonth(int maxShiftsPerMonth) {
        this.maxShiftsPerMonth = maxShiftsPerMonth;
    }

    /**
     * Returns the number of shifts currently assigned to this staff member.
     *
     * @return the assigned shift count
     */
    public int getAssignedShiftsCount() {
        return assignedShiftsCount;
    }

    /**
     * Sets the current assigned shift count for this staff member.
     *
     * @param assignedShiftsCount the new shift total
     */
    public void setAssignedShiftsCount(int assignedShiftsCount) {
        this.assignedShiftsCount = assignedShiftsCount;
    }

    /**
     * Returns the dates on which this staff member is unavailable.
     *
     * @return an unmodifiable list of unavailable dates
     */
    public List<LocalDate> getUnavailableDates() {
        return Collections.unmodifiableList(unavailableDates);
    }

    /**
     * Replaces the list of unavailable dates for this staff member.
     *
     * @param unavailableDates the dates this staff member is unavailable
     * @throws NullPointerException if the provided list is null
     */
    public void setUnavailableDates(List<LocalDate> unavailableDates) {
        this.unavailableDates = new ArrayList<>(Objects.requireNonNull(unavailableDates));
    }

    /**
     * Checks whether this staff member is available on the given date.
     *
     * @param date the date to check
     * @return true if the staff member is available on the date, false otherwise
     */
    public boolean isAvailable(LocalDate date) {
        return !unavailableDates.contains(date);
    }

    /**
     * Increments the count of assigned shifts for this staff member by one.
     */
    public void incrementAssignedShifts() {
        assignedShiftsCount++;
    }
}
