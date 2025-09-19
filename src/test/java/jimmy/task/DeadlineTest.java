package jimmy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jimmy.exception.JimmyException;


public class DeadlineTest {
    @Test
    public void testMarkDone() {
        try {
            Deadline deadline1 = new Deadline("Deadline 1", false, Task.EMPTY_TAG, "2025-08-08");
            deadline1.markDone();
            assertEquals("[D][X] Deadline 1 (by: Aug 8 2025 00:00)", deadline1.toString(),
                    "Deadline is not marked done successfully");
            deadline1.markNotDone();
            assertEquals("[D][ ] Deadline 1 (by: Aug 8 2025 00:00)", deadline1.toString(),
                    "Deadline is not marked not done successfully");
        } catch (JimmyException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testToStorageString() {
        try {
            Deadline deadline1 = new Deadline("Deadline 1", false, Task.EMPTY_TAG, "2025-08-08");
            assertEquals("|DEADLINE|Deadline 1|false|2025-08-08 0000", deadline1.toStorageString(),
                    "Deadline toStorageString() is not correct");
            deadline1.markDone();
            assertEquals("|DEADLINE|Deadline 1|true|2025-08-08 0000", deadline1.toStorageString(),
                    "Deadline toStorageString() is not correct");
        } catch (JimmyException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testToString() {
        try {
            Deadline deadline1 = new Deadline("Deadline 1", false, Task.EMPTY_TAG, "2025-08-08 1200");
            assertEquals("[D][ ] Deadline 1 (by: Aug 8 2025 12:00)", deadline1.toString(),
                    "Deadline toString() is not correct");
            deadline1.markDone();
            assertEquals("[D][X] Deadline 1 (by: Aug 8 2025 12:00)", deadline1.toString(),
                    "Deadline toString() is not correct");
        } catch (JimmyException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testDefaultTime() {
        try {
            Deadline deadline1 = new Deadline("Deadline 1", false, Task.EMPTY_TAG, "2025-08-08");
            assertEquals("[D][ ] Deadline 1 (by: Aug 8 2025 00:00)", deadline1.toString(),
                    "Deadline does not initialise to 00:00 if not provided a specific time");

        } catch (JimmyException e) {
            System.out.println(e);
        }
    }
}
