package focus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // User input and storage format for events and deadline

    @Test
    // Checks if deadline is valid
    void deadline_valid() throws Exception {
        TaskList list = new TaskList();
        FocusCommand dc = InputParser.parse("deadline Work on CS2105 Assignment -1 /by 2025-10-01 2359", 0);
        assertTrue(dc instanceof DeadlineCommand);
        dc.execute(list);
        assertEquals(1, list.getTasks().size());
        Task t = list.get(0);
        assertTrue(t instanceof Deadline);
        assertEquals(t.toString(), "       [D][ ] Work on CS2105 Assignment -1 (by: 1 October 2025 11:59 pm)");
    }

}
