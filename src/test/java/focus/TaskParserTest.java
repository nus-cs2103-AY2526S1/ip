package focus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskParserTest {

    private DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // User input and storage format for events and deadline

    @Test
    // Parse Deadline storage line with correct date format
    void parseDeadline_valid() throws Exception {
        Task t = TaskParser.parse("D | 1 | submit report | 2025-09-10 1159");
        assertTrue(t instanceof Deadline);
        assertEquals("submit report", t.getDescription());
        assertTrue(t.isDone());
        Deadline d = (Deadline) t;
        assertEquals(LocalDateTime.parse("2025-09-10 1159", inputFormat), d.getDeadline());
        assertEquals("D | 1 | submit report | 2025-09-10 1159", d.toStorageString());
    }

    @Test
    // Parse Event storage line with start - end
    void parseEvent_valid() throws Exception {
        Task t = TaskParser.parse("E | 0 | project meeting | 2025-09-10 1159 - 2025-09-11 1159");
        assertTrue(t instanceof Event);
        assertEquals("project meeting", t.getDescription());
        assertFalse(t.isDone());
        assertEquals("E | 0 | project meeting | 2025-09-10 1159 - 2025-09-11 1159", t.toStorageString());
    }

}
