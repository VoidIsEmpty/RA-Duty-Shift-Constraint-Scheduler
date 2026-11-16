package com.ernesto;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Solves a monthly staffing schedule by assigning staff to dates while respecting availability and monthly limits.
 */
public class ScheduleSolver {

    private List<StaffMember> staffPool;
    private ShiftCalendar calendar;

    /**
     * Creates a solver using the provided staff pool and target calendar.
     *
     * @param staffPool the staff members eligible for assignment
     * @param calendar the calendar to populate with scheduled shifts
     * @throws NullPointerException if the staff pool or calendar is null
     */
    public ScheduleSolver(List<StaffMember> staffPool, ShiftCalendar calendar) {
        Objects.requireNonNull(staffPool, "staffPool must not be null");
        this.staffPool = new ArrayList<>(staffPool);
        if (this.staffPool.contains(null)) {
            throw new NullPointerException("staffPool must not contain null");
        }
        this.calendar = Objects.requireNonNull(calendar, "calendar must not be null");
    }

    /**
     * Generates a schedule for the specified month by assigning available staff members to each unscheduled date.
     *
     * @param year the year for the schedule
     * @param month the month for the schedule
     * @return true if a valid assignment was found for all eligible dates, false otherwise
     */
    public boolean generateSchedule(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        Map<StaffMember, Integer> originalCounts = new IdentityHashMap<>();
        for (StaffMember staff : staffPool) {
            originalCounts.putIfAbsent(staff, staff.getAssignedShiftsCount());
        }

        List<LocalDate> datesToAssign = new ArrayList<>();
        List<StaffMember> selectedStaff = new ArrayList<>();
        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            LocalDate date = yearMonth.atDay(day);
            if (!calendar.getStaffForDate(date).isEmpty()) {
                continue;
            }

            List<StaffMember> eligibleStaff = new ArrayList<>();
            for (StaffMember staff : staffPool) {
                if (staff.getAssignedShiftsCount() < staff.getMaxShiftsPerMonth()
                        && staff.isAvailable(date)) {
                    eligibleStaff.add(staff);
                }
            }

            StaffMember candidate = selectBestCandidate(date, eligibleStaff);
            if (candidate == null) {
                for (Map.Entry<StaffMember, Integer> entry : originalCounts.entrySet()) {
                    entry.getKey().setAssignedShiftsCount(entry.getValue());
                }
                return false;
            }

            datesToAssign.add(date);
            selectedStaff.add(candidate);
            candidate.incrementAssignedShifts();
        }

        for (int i = 0; i < datesToAssign.size(); i++) {
            calendar.assignShift(datesToAssign.get(i), selectedStaff.get(i));
        }
        return true;
    }

    /**
     * Calculates a fairness score based on the variance of assigned shift counts across the staff pool.
     *
     * @return the average squared deviation from the mean assigned shift count, or 0.0 when no staff members exist
     */
    public double calculateFairnessScore() {
        if (staffPool.isEmpty()) {
            return 0.0;
        }

        double mean = 0.0;
        for (StaffMember staff : staffPool) {
            mean += staff.getAssignedShiftsCount();
        }
        mean /= staffPool.size();

        double squaredDifferences = 0.0;
        for (StaffMember staff : staffPool) {
            double difference = staff.getAssignedShiftsCount() - mean;
            squaredDifferences += difference * difference;
        }
        return squaredDifferences / staffPool.size();
    }

    private StaffMember selectBestCandidate(LocalDate date, List<StaffMember> eligibleStaff) {
        StaffMember bestCandidate = null;
        for (StaffMember staff : eligibleStaff) {
            if (staff.isAvailable(date)
                    && (bestCandidate == null
                    || staff.getAssignedShiftsCount() < bestCandidate.getAssignedShiftsCount())) {
                bestCandidate = staff;
            }
        }
        return bestCandidate;
    }
}
