package tinkerton.task;

import org.junit.jupiter.api.Test;
import tinkerton.util.Date;
import tinkerton.core.TinkertonException;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    void testToString_notCompleted_success() {
        Event event = null;
        try {
            event = new Event("Meeting",
                    false,
                    "2025-12-12 1800",
                    "2025-12-12 2000");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("[E][ ] Meeting (from: 12 December 2025 18:00 to: 12 December 2025 20:00)", event.toString());
    }

    @Test
    void testToString_completed_success() {
        Event event = null;
        try {
            event = new Event("Meeting",
                    true,
                    "2025-12-12 1800",
                    "2025-12-12 2000");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("[E][X] Meeting (from: 12 December 2025 18:00 to: 12 December 2025 20:00)", event.toString());
    }

    @Test
    void testConstructor_endEarlierThanStart_exceptionThrown() {
        try {
            new Event("Meeting",
                    true,
                    "2025-12-12 1800",
                    "2025-12-12 1600");
        } catch (TinkertonException e) {
            assertEquals("Deadline that is already overdue? A bit too late to add that...", e.getMessage());
        }
    }

    @Test
    void testConstructor_startBeforeNow_exceptionThrown() {
        try {
            new Event("Meeting",
                    true,
                    "1999-12-12 1800",
                    "2025-12-12 1600");
        } catch (TinkertonException e) {
            assertEquals("Deadline that is already overdue? A bit too late to add that...", e.getMessage());
        }
    }

    @Test
    void testToFile_notCompleted_success() {
        Event event = null;
        try {
            event = new Event("Meeting",
                    false,
                    "2025-12-12 1800",
                    "2025-12-12 2000");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("E | 0 | Meeting | 2025-12-12 1800 | 2025-12-12 2000)", event.toFile());
    }

    @Test
    void testToFile_completed_success() {
        Event event = null;
        try {
            event = new Event("Meeting",
                    true,
                    "2025-12-12 1800",
                    "2025-12-12 2000");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("E | 1 | Meeting | 2025-12-12 1800 | 2025-12-12 2000)", event.toFile());
    }

    @Test
    void testOnDate_isTrue_success() {
        Event event = null;
        try {
            event = new Event("Meeting",
                    false,
                    "2025-12-12 1800",
                    "2025-12-12 2000");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }
        Date date = null;
        try {
            date = new Date("2025-12-12 1800");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(event.onDate(date));
    }

    @Test
    void testOnDate_isFalse_success() {
        Deadline deadline = null;
        try {
            deadline = new Deadline("Read book",
                    true,
                    "2025-12-12 1800");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }
        Date date = null;
        try {
            date = new Date("2025-11-11 1800");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(deadline.onDate(date));
    }
}
