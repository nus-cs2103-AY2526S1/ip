package peppa.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void toSaveFileFormat_notMarkDone_success() {
        Event e = new Event("rbk birthday party",
                "15/10/2025 1900",
                "15/10/2025 2300");

        String expected = "E | 0 | rbk birthday party | 15/10/2025 1900 | 15/10/2025 2300";
        assertEquals(expected, e.toSaveFileFormat());
    }

    @Test
    void toSaveFileFormat_markDone_success() {
        Event e = new Event("team meeting",
                "1/9/2025 0900",
                "1/9/2025 1000");
        e.markAsDone();   // method inherited from Task

        String expected = "E | 1 | team meeting | 1/9/2025 0900 | 1/9/2025 1000";
        assertEquals(expected, e.toSaveFileFormat());
    }

    @Test
    void toString_success() {
        Event e = new Event("conference",
                "2/11/2025 0800",
                "2/11/2025 1700");

        String expected = "[E][ ] conference (from: Nov 2 2025 8 am to: Nov 2 2025 5 pm)";
        assertEquals(expected, e.toString());
    }
}
