package jimmy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jimmy.exception.JimmyException;

public class EventTest {

    @Test
    public void testMarkDone() {
        try {
            Event event1 = new Event("Event 1", false, Task.EMPTY_TAG, "2025-08-08 1300",
                    "2025-08-08 1400");
            event1.markDone();
            assertEquals("[E][X] Event 1 (from: Aug 8 2025 13:00 to: Aug 8 2025 14:00)", event1.toString(),
                    "Event is not marked done successfully");
            event1.markNotDone();
            assertEquals("[E][ ] Event 1 (from: Aug 8 2025 13:00 to: Aug 8 2025 14:00)", event1.toString(),
                    "Event is not marked not done successfully");
        } catch (JimmyException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testToStorageString() {
        try {
            Event event1 = new Event("Event 1", false, Task.EMPTY_TAG, "2025-08-08 1300",
                    "2025-08-08 1400");
            event1.markDone();
            assertEquals("|EVENT|Event 1|true|2025-08-08 1300|2025-08-08 1400", event1.toStorageString(),
                    "Event toStorageString() is not correct");
            event1.markNotDone();
            assertEquals("|EVENT|Event 1|false|2025-08-08 1300|2025-08-08 1400", event1.toStorageString(),
                    "Event toStorageString() is not correct");
        } catch (JimmyException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testToString() {
        try {
            Event event1 = new Event("Event 1", false, Task.EMPTY_TAG, "2025-08-08 1300",
                    "2025-08-08 1400");
            event1.markDone();
            assertEquals("[E][X] Event 1 (from: Aug 8 2025 13:00 to: Aug 8 2025 14:00)", event1.toString(),
                    "Event toString() is not correct");
            event1.markNotDone();
            assertEquals("[E][ ] Event 1 (from: Aug 8 2025 13:00 to: Aug 8 2025 14:00)", event1.toString(),
                    "Event toString() is not correct");
        } catch (JimmyException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testDefaultTime() {
        try {
            Event event1 = new Event("Event 1", false, Task.EMPTY_TAG, "2025-08-08", "2025-08-08");
            assertEquals("[E][ ] Event 1 (from: Aug 8 2025 00:00 to: Aug 8 2025 00:00)", event1.toString(),
                    "Event does not initialise to 00:00 if not provided a specific time");

        } catch (JimmyException e) {
            System.out.println(e);
        }
    }
}
