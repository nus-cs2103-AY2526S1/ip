package tinkerton.task;

import org.junit.jupiter.api.Test;
import tinkerton.util.Date;
import tinkerton.core.TinkertonException;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    void testToString_notCompleted_success() {
        Deadline deadline = null;
        try {
            deadline = new Deadline("Read book",
                    false,
                    "2025-12-12 1800");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("[D][ ] Read book (by: 12 December 2025 18:00)", deadline.toString());
    }

    @Test
    void testToString_completed_success() {
        Deadline deadline = null;
        try {
            deadline = new Deadline("Read book",
                    true,
                    "2025-12-12 1800");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("[D][X] Read book (by: 12 December 2025 18:00)", deadline.toString());
    }

    @Test
    void testConstructor_exceptionThrown() {
        try {
            new Deadline("Read book",
                    true,
                    "1999-12-12 1800");
        } catch (TinkertonException e) {
            assertEquals("Deadline that is already overdue? A bit too late to add that...", e.getMessage());
        }
    }

    @Test
    void testToFile_notCompleted_success() {
        Deadline deadline = null;
        try {
            deadline = new Deadline("Read book",
                    false,
                    "2025-12-12 1800");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("D | 0 | Read book | 2025-12-12 1800", deadline.toFile());
    }

    @Test
    void testToFile_completed_success() {
        Deadline deadline = null;
        try {
            deadline = new Deadline("Read book",
                    true,
                    "2025-12-12 1800");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("D | 1 | Read book | 2025-12-12 1800", deadline.toFile());
    }

    @Test
    void testOnDate_isTrue_success() {
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
            date = new Date("2025-12-12 1800");
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(deadline.onDate(date));
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
