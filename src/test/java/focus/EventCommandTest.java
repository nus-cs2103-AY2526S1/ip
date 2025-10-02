package focus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventCommandTest {

    private DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // User input and storage format for events and deadline

    @Test
    // Checks if event command is valid
    void event_valid() throws Exception {
        TaskList list = new TaskList();
        FocusCommand ec;
        try {
            ec = InputParser.parse("event LINE CTF 2025 /from 2025-10-18 0800 /to 2025-10-19 0800", 0);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "     End date-time cannot be before start date-time.");
        }
        try {
            ec = InputParser.parse("event LINE CTF 2025 /from 2025-10-18 0800 /to 0800 2025-10-19", 0);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "     Invalid date-time. Use yyyy-MM-dd HHmm (e.g., 2025-10-01 0930).");
        }
    }

}
