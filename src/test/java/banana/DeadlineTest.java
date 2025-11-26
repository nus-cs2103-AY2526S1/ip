package banana;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import banana.task.Deadline;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest() {
        assertEquals(4, 4);
    }

    @Test
    public void testToStringFormat() {
        // Test the string representation with a specific date
        Deadline deadline = new Deadline("Submit assignment", "2025-09-01 0900");
        String expectedOutput = "[D][ ] Submit assignment (by: Sept 01 2025, 9:00am)";
        assertEquals(expectedOutput, deadline.toString(), "The toString output should match the expected format");
    }

    @Test
    public void testDeadlineConstructionWithValidDate() {
        // Test with a valid date string
        Deadline deadline = new Deadline("Finish report", "2025-08-31 1430");
        LocalDateTime expectedDate = LocalDateTime.parse("2025-08-31 1430",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        assertEquals(expectedDate, deadline.getBy(), "The deadline date should be correctly parsed");
    }
}
