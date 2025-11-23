package stewie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Tests for DeadlineTask.
 */
public class DeadlineTaskTest {
    private final LocalDateTime testDateTime = LocalDateTime.of(2025, 8, 30, 15, 30);

    @Test
    void testDeadlineTaskDescriptionFormat() {
        DeadlineTask task = new DeadlineTask("Finish CS2103T Assignment", testDateTime);

        String expectedDescription = "[D][ ] Finish CS2103T Assignment (by: 30 Aug 2025 15:30)";
        assertEquals(expectedDescription, task.getDescription());

        task.markAsDone();

        String expectedDoneDescription = "[D][X] Finish CS2103T Assignment (by: 30 Aug 2025 15:30)";
        assertEquals(expectedDoneDescription, task.getDescription());
    }

    @Test
    void testDeadlineTaskToFileFormat() {
        DeadlineTask task = new DeadlineTask("Pay bills", testDateTime);

        String expectedFileFormat = "D | 0 | Pay bills | 30/08/2025 15:30";
        assertEquals(expectedFileFormat, task.toFileFormat());

        task.markAsDone();

        String expectedDoneFileFormat = "D | 1 | Pay bills | 30/08/2025 15:30";
        assertEquals(expectedDoneFileFormat, task.toFileFormat());
    }
}
