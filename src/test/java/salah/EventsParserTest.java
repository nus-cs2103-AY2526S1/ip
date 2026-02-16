package salah;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventsParserTest {

    @Test
    @DisplayName("Parses a valid event with from/to datetime")
    void parsesValidEvent() throws Exception {
        Events ev = Events.parser("event project meeting /from 2019-12-02 0900 /to 2019-12-02 1030");
        assertEquals("project meeting", ev.getDescription());
        assertEquals(LocalDateTime.of(2019, 12, 2, 9, 0), ev.getFrom());
        assertEquals(LocalDateTime.of(2019, 12, 2, 10, 30), ev.getTo());
        assertFalse(ev.getIsComplete());
    }

    @Test
    @DisplayName("Rejects event missing /from")
    void rejectsMissingFrom() {
        TaskFormattingException ex = assertThrows(
                TaskFormattingException.class,
                () -> Events.parser("event concert /to 2019-12-20")
        );
        assertTrue(ex.getMessage().toLowerCase().contains("invalid event format"));
    }

    @Test
    @DisplayName("Rejects event missing /to")
    void rejectsMissingTo() {
        TaskFormattingException ex = assertThrows(
                TaskFormattingException.class,
                () -> Events.parser("event rehearsal /from 2019-12-20")
        );
        assertTrue(ex.getMessage().toLowerCase().contains("invalid event format"));
    }

    @Test
    @DisplayName("Rejects empty description")
    void rejectsEmptyDescription() {
        EmptyDescriptionException ex = assertThrows(
                EmptyDescriptionException.class,
                () -> Events.parser("event   /from 2019-12-20 /to 2019-12-21")
        );
        assertTrue(ex.getMessage().toLowerCase().contains("description"));
    }
}
