package focus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {

    private DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // User input and storage format for events and deadline

    @Test
    // Checks if event is valid
    void event_valid() throws Exception {
        TaskList list = new TaskList();
        FocusCommand ec;
        ec = InputParser.parse("event LINE CTF 2025 /from 2025-10-18 0800 /to 2025-10-19 0800", 0);
        assertTrue(ec instanceof EventCommand);
        ec.execute(list);
        assertEquals(1, list.getTasks().size());
        Task t = list.get(0);
        assertTrue(t instanceof Event);
        assertEquals(t.toString(),
                "       [E][ ] LINE CTF 2025 (from: 18 October 2025 8:00 am to: 19 October 2025 8:00 am)");
    }

}
