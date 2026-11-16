package com.ernesto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StaffMember alex = new StaffMember("RA-001", "Alex", 15);
        StaffMember jordan = new StaffMember("RA-002", "Jordan", 15);
        List<StaffMember> staffPool = Arrays.asList(alex, jordan);

        ShiftCalendar calendar = new ShiftCalendar(2026, 11);
        ScheduleSolver solver = new ScheduleSolver(staffPool, calendar);

        boolean generated = solver.generateSchedule(2026, 11);
        System.out.println("November 2026 schedule generated: " + generated);
        if (!generated) {
            return;
        }

        for (LocalDate date : Arrays.asList(
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 15),
                LocalDate.of(2026, 11, 30))) {
            System.out.println(date + ": " + calendar.getStaffForDate(date).get(0).getName());
        }

        System.out.println(alex.getName() + " assigned shifts: " + alex.getAssignedShiftsCount());
        System.out.println(jordan.getName() + " assigned shifts: " + jordan.getAssignedShiftsCount());
        System.out.println("Fairness variance: " + solver.calculateFairnessScore());
    }
}
