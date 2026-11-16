package com.ernesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Stores the scheduled shifts for a specific month and provides lookup methods by date.
 */
public class ShiftCalendar {

    private int year;
    private int month;
    private Map<LocalDate, List<StaffMember>> scheduledShifts;

    /**
     * Creates a calendar for the specified year and month.
     *
     * @param year the calendar year
     * @param month the calendar month
     */
    public ShiftCalendar(int year, int month) {
        this.year = year;
        this.month = month;
        this.scheduledShifts = new HashMap<>();
    }

    /**
     * Assigns a staff member to a specific date.
     *
     * @param date the date to assign
     * @param staff the staff member to assign
     * @throws NullPointerException if either argument is null
     */
    public void assignShift(LocalDate date, StaffMember staff) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(staff, "staff must not be null");
        scheduledShifts.computeIfAbsent(date, ignored -> new ArrayList<>()).add(staff);
    }

    /**
     * Returns the staff members assigned to a given date.
     *
     * @param date the date to inspect
     * @return an unmodifiable list of assigned staff members, or an empty list if none are assigned
     * @throws NullPointerException if the date is null
     */
    public List<StaffMember> getStaffForDate(LocalDate date) {
        Objects.requireNonNull(date, "date must not be null");
        List<StaffMember> staff = scheduledShifts.get(date);
        return staff == null ? Collections.emptyList() : Collections.unmodifiableList(staff);
    }

    /**
     * Checks whether a staff member has already been assigned to a specific date.
     *
     * @param date the date to inspect
     * @param staff the staff member to check
     * @return true if the staff member is assigned on that date, false otherwise
     * @throws NullPointerException if either argument is null
     */
    public boolean hasAssignedShift(LocalDate date, StaffMember staff) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(staff, "staff must not be null");
        List<StaffMember> assignedStaff = scheduledShifts.get(date);
        return assignedStaff != null && assignedStaff.contains(staff);
    }
}
