package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


class EventTest {

    @Test
    void testEvent() {
        Event e = new Event("Meeting", "2025-09-02", "2025-09-05");

        assertEquals("Meeting", e.description);
        assertFalse(e.isDone);
        assertEquals(LocalDate.parse("2025-09-02"), e.getStart());
        assertEquals(LocalDate.parse("2025-09-05"), e.getEnd());

        String expectedString = "[E][ ] Meeting(from: Sep 02 2025 to: Sep 05 2025)";
        assertEquals(expectedString, e.toString());

        String expectedFile = "chatbot.task.ToDo||" + "" + "||Meeting||2025-09-02||2025-09-05";
        assertEquals(expectedFile, e.toFile());
    }

    @Test
    void testEvent2() {
        Event e = new Event(true, "Conference", "2025-10-05", "2025-10-06");
        assertTrue(e.isDone);
        assertEquals("Conference", e.description);
    }
}
