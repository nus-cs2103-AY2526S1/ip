package dume.task;

import dume.util.DateTimeHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    void toFileFormat_includes_start_end() {
        Event e = new Event("meeting", "8/8/2008 1200", "8/8/2008 1300");
        assertEquals("E | 0 | meeting | 8/8/2008 1200 to 8/8/2008 1300", e.toFileFormat());
    }

    @Test
    void toString_prints_range() {
        Event e = new Event("meeting", "8/8/2008 1200", "8/8/2008 1300");
        String exp = "[E][ ] meeting (from: " +
                DateTimeHelper.convert("8/8/2008 1200") +
                " to: " +
                DateTimeHelper.convert("8/8/2008 1300") + ")";
        assertEquals(exp, e.toString());
    }
}