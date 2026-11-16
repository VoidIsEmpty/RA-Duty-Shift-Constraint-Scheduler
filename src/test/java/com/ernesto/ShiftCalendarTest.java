package com.ernesto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ShiftCalendarTest {

    private ShiftCalendar calendar;
    private StaffMember staff;

    @BeforeEach
    public void setUp() {
        calendar = new ShiftCalendar(2026, 11); // November 2026
        staff = new StaffMember("RA01", "Alex Smith", 4);
    }

    @Test
    public void testAssignAndGetStaffForDate() {
        LocalDate date = LocalDate.of(2026, 11, 1);
        calendar.assignShift(date, staff);

        List<StaffMember> assignedStaff = calendar.getStaffForDate(date);
        assertNotNull(assignedStaff);
        assertEquals(1, assignedStaff.size());
        assertEquals("Alex Smith", assignedStaff.get(0).getName());
    }

    @Test
    public void testHasAssignedShift() {
        LocalDate date = LocalDate.of(2026, 11, 5);
        assertFalse(calendar.hasAssignedShift(date, staff));

        calendar.assignShift(date, staff);
        assertTrue(calendar.hasAssignedShift(date, staff));
    }

    @Test
    public void testUnassignedDateReturnsEmptyList() {
        LocalDate date = LocalDate.of(2026, 11, 10);
        List<StaffMember> assignedStaff = calendar.getStaffForDate(date);
        assertNotNull(assignedStaff);
        assertTrue(assignedStaff.isEmpty());
    }
}