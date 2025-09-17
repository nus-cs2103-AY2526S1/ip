package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import misc.PepeException;

public class DeadlineTest {
    @Test
    public void testDeadlineSucceedInput() {
        String userInput = "meet Pepe /by 2025-08-16 1400";
        try {
            Deadline deadline = Deadline.fromInput(userInput.split(" "));
            assertEquals(new Deadline("meet Pepe", LocalDateTime.of(2025, 8, 16, 14, 0)), deadline);
        } catch (PepeException e) {
            assert false;
        }
    }

    @Test
    public void testDeadlineFailInputMinutes() {
        String userInput = "meet Pepe /by 2025-08-16 140";
        assertThrows(DateTimeParseException.class, () -> Deadline.fromInput(userInput.split(" ")));
    }

    @Test
    public void testDeadlineFailInputMissing() {
        String userInput = "meet Pepe /by 2025-";
        assertThrows(PepeException.class, () -> Deadline.fromInput(userInput.split(" ")));
    }

    @Test
    public void testDeadlineFailInputMissingTwo() {
        String userInput = "meet Pepe /by 2025- 1400";
        assertThrows(DateTimeParseException.class, () -> Deadline.fromInput(userInput.split(" ")));
    }

    @Test
    public void testDeadlineFailNoByClause() {
        String userInput = "meet Pepe /from 2025-08-16 140";
        PepeException pepeException = assertThrows(PepeException.class, () -> Deadline.fromInput(userInput.split(" ")));
        assertTrue(pepeException.getMessage().contains("Expected deadline date formatted string yyyy-MM-dd HHmm."));
    }

    @Test
    public void testDeadlineFailWithTwoByClauseConsecutively() {
        String userInput = "meet Pepe /by /by 2025-08-16 140";
        assertThrows(DateTimeParseException.class, () -> Deadline.fromInput(userInput.split(" ")));
    }

    @Test
    public void testDeadlineFailWithExtraInput() {
        String userInput = "meet Pepe /by 2025-08-16 1400 hehe";
        assertThrows(PepeException.class, () -> Deadline.fromInput(userInput.split(" ")));
    }
}
