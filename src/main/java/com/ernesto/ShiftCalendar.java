package com.ernesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShiftCalendar {

    private int year;
    private int month;
    private Map<LocalDate, List<StaffMember>> scheduledShifts;

    public ShiftCalendar(int year, int month) {
        this.year = year;
        this.month = month;
        this.scheduledShifts = new HashMap<>();
    }

    public void assignShift(LocalDate date, StaffMember staff) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(staff, "staff must not be null");
        scheduledShifts.computeIfAbsent(date, ignored -> new ArrayList<>()).add(staff);
    }

    public List<StaffMember> getStaffForDate(LocalDate date) {
        Objects.requireNonNull(date, "date must not be null");
        List<StaffMember> staff = scheduledShifts.get(date);
        return staff == null ? Collections.emptyList() : Collections.unmodifiableList(staff);
    }

    public boolean hasAssignedShift(LocalDate date, StaffMember staff) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(staff, "staff must not be null");
        List<StaffMember> assignedStaff = scheduledShifts.get(date);
        return assignedStaff != null && assignedStaff.contains(staff);
    }
}
