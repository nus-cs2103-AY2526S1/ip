package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testConstructor_parsesDateTime() {
        Deadline deadline = new Deadline("submit hw /by 2024-09-14 1111", false);
        assertEquals("submit hw", deadline.getDescription());
        assertEquals(LocalDateTime.of(2024, 9, 14, 11, 11), deadline.getDeadline());
    }

    @Test
    public void testConstructor_parsesDateOnly() {
        Deadline deadline = new Deadline("random /by 2021-01-04", true);
        assertEquals("random", deadline.getDescription());
        assertEquals(LocalDateTime.of(2021, 1, 4, 0, 0), deadline.getDeadline());
    }

    @Test
    public void testToString_withTime() {
        Deadline deadline = new Deadline("submit report /by 2025-04-01 2100", false);
        assertEquals("[D] [ ] submit report (by: Apr 01 2025, 9:00pm)", deadline.toString());
    }

    @Test
    public void testToString_dateOnly() {
        Deadline deadline = new Deadline("sleep /by 2025-04-01", true);
        assertEquals("[D] [X] sleep (by: Apr 01 2025)", deadline.toString());
    }

    @Test
    public void testToStorageString_withTime() {
        Deadline deadline = new Deadline("sleep /by 2024-06-01 2359", false);
        assertEquals("D | 0 | sleep /by 2024-06-01 2359", deadline.toStorageString());
    }

    @Test
    public void testToStorageString_dateOnly() {
        Deadline deadline = new Deadline("shit /by 2024-06-01", true);
        assertEquals("D | 1 | shit /by 2024-06-01", deadline.toStorageString());
    }
}
