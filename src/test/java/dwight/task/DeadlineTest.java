package dwight.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Unit tests for the {@link Deadline} class. Verifies behavior for string representation and
 * serialization with a due date.
 */
public class DeadlineTest {

    /**
     * Tests the string representation of a {@code Deadline} task, ensuring that type, status,
     * description, and due date are formatted correctly.
     */
    @Test
    public void testToString() {
        LocalDate date = LocalDate.of(2023, 11, 15);
        Deadline deadline = new Deadline("submit report", date);

        // Unmarked deadline
        assertEquals(
                "[D][ ] submit report (by: 15 Nov)",
                deadline.toString(),
                "Unmarked deadline should display with [ ] and correct date");

        // Marked deadline
        deadline.mark();
        assertEquals(
                "[D][X] submit report (by: 15 Nov)",
                deadline.toString(),
                "Marked deadline should display with [X] and correct date");
    }

    /**
     * Tests the serialization of a {@code Deadline} task, ensuring that type, status flag,
     * description, and due date are saved in the correct format.
     */
    @Test
    public void testSerialize() {
        LocalDate date = LocalDate.of(2023, 11, 15);
        Deadline deadline = new Deadline("submit report", date);

        // Unmarked deadline
        assertEquals(
                "D | 0 | submit report | 15 Nov 2023",
                deadline.serialize(),
                "Unmarked deadline should serialize with 0 and correct date");

        // Marked deadline
        deadline.mark();
        assertEquals(
                "D | 1 | submit report | 15 Nov 2023",
                deadline.serialize(),
                "Marked deadline should serialize with 1 and correct date");
    }
}
