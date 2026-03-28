package clare.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import clare.exception.StringConvertExceptions;

public class EventTest {

    @Test
    void testConstructor_validInput_withDateAndTime() throws StringConvertExceptions {
        Event event = new Event("project presentation", "2023-11-20 10:00", "2023-11-20 11:30", false);
        assertEquals("project presentation", event.getTitle());
        assertEquals("0", event.getIsDoneInt());
        assertEquals("E", event.getTypeString());
        assertEquals(TaskType.E, event.getType());
    }

    @Test
    void testConstructor_validInput_noTime() throws StringConvertExceptions {
        Event event = new Event("team meeting", "2023-11-21", "2023-11-21", false);
        assertEquals("team meeting", event.getTitle());
        assertEquals("0", event.getIsDoneInt());
        assertEquals("E", event.getTypeString());
        assertEquals(TaskType.E, event.getType());
    }

    @Test
    void testConstructor_nowKeyword() throws StringConvertExceptions {
        Event event = new Event("start event now", "now", "2023-12-01", false);
        assertEquals("start event now", event.getTitle());
        assertEquals("0", event.getIsDoneInt());
        assertEquals("E", event.getTypeString());
        assertEquals(TaskType.E, event.getType());
    }

    @Test
    void testConstructor_invalidStartTimeFormat() {
        assertThrows(StringConvertExceptions.class, () ->
                new Event("invalid start", "2023/11/22", "2023-11-22", false));
    }

    @Test
    void testConstructor_invalidDeadlineFormat() {
        assertThrows(StringConvertExceptions.class, () ->
                new Event("invalid deadline", "2023-11-22", "22-11-2023", false));
    }

    @Test
    void testCompareStartTime_earlierEvent() throws StringConvertExceptions {
        Event event1 = new Event("earlier event", "2023-12-01", "2023-12-01", false);
        Event event2 = new Event("later event", "2023-12-02", "2023-12-02", false);
        assertEquals(-1, event1.compareStartTime(event2));
    }

    @Test
    void testCompareStartTime_laterEvent() throws StringConvertExceptions {
        Event event1 = new Event("later event", "2023-12-02", "2023-12-02", false);
        Event event2 = new Event("earlier event", "2023-12-01", "2023-12-01", false);
        assertEquals(1, event1.compareStartTime(event2));
    }

    @Test
    void testCompareStartTime_sameDate() throws StringConvertExceptions {
        Event event1 = new Event("event 1", "2023-12-03", "2023-12-03", false);
        Event event2 = new Event("event 2", "2023-12-03", "2023-12-03", false);
        assertEquals(0, event1.compareStartTime(event2));
    }

    @Test
    void testToString_withTimes() throws StringConvertExceptions {
        Event event = new Event("conference call", "2023-12-05 09:00", "2023-12-05 10:30", true);
        assertEquals("[E][X] conference call (from: Dec 5 2023 09:00 to: Dec 5 2023 10:30)", event.toString());
    }

    @Test
    void testToString_noTimes() throws StringConvertExceptions {
        Event event = new Event("holiday", "2023-12-25", "2023-12-26", false);
        assertEquals("[E][ ] holiday (from: Dec 25 2023 to: Dec 26 2023)", event.toString());
    }

    @Test
    void testToSaveString_withTimes() throws StringConvertExceptions {
        Event event = new Event("project meeting", "2023-12-10 15:00", "2023-12-10 16:00", false);
        assertEquals("E|0|project meeting|2023-12-10 16:00|2023-12-10 15:00", event.toSaveString());
    }

    @Test
    void testToSaveString_noTimes() throws StringConvertExceptions {
        Event event = new Event("day trip", "2023-12-15", "2023-12-15", false);
        assertEquals("E|0|day trip|2023-12-15|2023-12-15", event.toSaveString());
    }
}
