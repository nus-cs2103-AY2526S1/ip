package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import misc.PepeException;

public class EventTest {
    @Test
    public void testEventSucceedInput() {
        String userInput = "meet Pepe /from 2025-08-16 1400 /to 2025-08-16 1600";
        try {
            Event event = Event.fromInput(userInput.split(" "));
            LocalDateTime from = LocalDateTime.of(2025, 8, 16, 14, 0);
            LocalDateTime to = LocalDateTime.of(2025, 8, 16, 16, 0);
            assertEquals(new Event("meet Pepe", from, to), event);
        } catch (PepeException e) {
            System.out.println(e.getMessage());
            assert false;
        }
    }

    @Test
    public void testEventFailInputMissingOne() {
        String userInput = "meet Pepe /from 2025-08-16 1400";
        assertThrows(PepeException.class, () -> Event.fromInput(userInput.split(" ")));
    }

    @Test
    public void testEventFailInputMissingTwo() {
        String userInput = "meet Pepe /from 2025-08-16 1400 /to";
        assertThrows(PepeException.class, () -> Event.fromInput(userInput.split(" ")));
    }

    @Test
    public void testEventFailInputMissingThree() {
        String userInput = "meet Pepe /from 2025-08-16 1400 /to 2025";
        assertThrows(PepeException.class, () -> Event.fromInput(userInput.split(" ")));
    }

    @Test
    public void testEventFailInputMissingFour() {
        String userInput = "meet Pepe /from 2025-08-16 1400 /to 2025-08-16";
        assertThrows(PepeException.class, () -> Event.fromInput(userInput.split(" ")));
    }

    @Test
    public void testEventFailInputMissingFive() {
        String userInput = "meet Pepe /from 2025-08-16 1400 /to 2025-08-16 16002";
        assertThrows(DateTimeParseException.class, () -> Event.fromInput(userInput.split(" ")));
    }

    @Test
    public void testEventFailInputMissingSix() {
        String userInput = "meet Pepe /from 2025-08-16 1400 /to 2025-08-16 1600 asd";
        assertThrows(PepeException.class, () -> Event.fromInput(userInput.split(" ")));
    }

    @Test
    public void testDeadlineFailNoFromClause() {
        String userInput = "meet Pepe /by 2025-08-16 140";
        PepeException pepeException = assertThrows(PepeException.class, () -> Event.fromInput(userInput.split(" ")));
        assertTrue(pepeException.getMessage().contains("Expected /from date formatted string yyyy-MM-dd HHmm."));
    }

    @Test
    public void testEventFailWithTwoFromClauseConsecutively() {
        String userInput = "meet Pepe /from /from 2025-08-16 140";
        assertThrows(DateTimeParseException.class, () -> Event.fromInput(userInput.split(" ")));
    }
}
