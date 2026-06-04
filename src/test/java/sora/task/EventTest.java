package sora.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    public void toString_notDone_returnsCorrectFormat() {
        LocalDateTime from = LocalDateTime.of(2006, 2, 8, 23, 59);
        LocalDateTime to = LocalDateTime.of(2025, 2, 8, 23, 59);
        Event event = new Event("Do something", from, to);
        assertEquals("[E][ ] Do something (from: Feb 08 2006 2359 to: Feb 08 2025 2359)", event.toString());
    }

    @Test
    public void toString_markedDone_returnsCorrectFormat() {
        LocalDateTime from = LocalDateTime.of(2006, 2, 8, 23, 59);
        LocalDateTime to = LocalDateTime.of(2025, 2, 8, 23, 59);
        Event event = new Event("Do something", from, to);
        event.markAsDone();
        assertEquals("[E][X] Do something (from: Feb 08 2006 2359 to: Feb 08 2025 2359)", event.toString());
    }

    @Test
    public void toFormat_notDone_returnsCorrectFormat() {
        LocalDateTime from = LocalDateTime.of(2006, 2, 8, 23, 59);
        LocalDateTime to = LocalDateTime.of(2025, 2, 8, 23, 59);
        Event event = new Event("Do something", from, to);
        assertEquals("E | 0 | Do something | Feb 08 2006 2359 | Feb 08 2025 2359", event.toFormat());
    }

    @Test
    public void toFormat_done_returnsCorrectFormat() {
        LocalDateTime from = LocalDateTime.of(2006, 2, 8, 23, 59);
        LocalDateTime to = LocalDateTime.of(2025, 2, 8, 23, 59);
        Event event = new Event("Do something", from, to);
        event.markAsDone();
        assertEquals("E | 1 | Do something | Feb 08 2006 2359 | Feb 08 2025 2359", event.toFormat());
    }
}

