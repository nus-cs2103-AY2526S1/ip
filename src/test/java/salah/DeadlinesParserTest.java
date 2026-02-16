package salah;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeadlinesParserTest {

    @Test
    @DisplayName("Parses a well-formed deadline command with datetime")
    void parsesValidDeadline() throws Exception {
        Deadlines d = Deadlines.parser("deadline submit report /by 2019-12-15 1800");
        assertEquals("submit report", d.getDescription());
        assertEquals(LocalDateTime.of(2019, 12, 15, 18, 0), d.getBy());
        assertFalse(d.getIsComplete());
    }

    @Test
    @DisplayName("Parses a well-formed deadline command with date only")
    void parsesDateOnlyDeadline() throws Exception {
        Deadlines d = Deadlines.parser("deadline return book /by 2019-12-02");
        assertEquals(LocalDateTime.of(2019, 12, 2, 0, 0), d.getBy());
    }

    @Test
    @DisplayName("Rejects missing /by section")
    void rejectsMissingBy() {
        TaskFormattingException ex = assertThrows(
                TaskFormattingException.class,
                () -> Deadlines.parser("deadline submit report")
        );
        assertTrue(ex.getMessage().toLowerCase().contains("invalid deadline format"));
    }

    @Test
    @DisplayName("Rejects empty description")
    void rejectsEmptyDescription() {
        EmptyDescriptionException ex = assertThrows(
                EmptyDescriptionException.class,
                () -> Deadlines.parser("deadline   /by 2019-12-02")
        );
        assertTrue(ex.getMessage().toLowerCase().contains("description"), "should mention description");
    }

    @Test
    @DisplayName("Rejects empty date")
    void rejectsEmptyDate() {
        EmptyDescriptionException ex = assertThrows(
                EmptyDescriptionException.class,
                () -> Deadlines.parser("deadline x /by   ")
        );
        assertTrue(ex.getMessage().toLowerCase().contains("deadline"), "should mention deadline");
    }
}
