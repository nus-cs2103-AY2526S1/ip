package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testToString_withTime() {
        Event event = new Event("meeting /from 2024-06-01 1400 /to 2024-06-01 1600", false);
        assertEquals("[E] [ ] meeting (from: Jun 01 2024, 2:00pm to: Jun 01 2024, 4:00pm)", event.toString());
    }

    @Test
    public void testToString_dateOnly() {
        Event event = new Event("holiday /from 2024-06-01 /to 2024-06-02", true);
        assertEquals("[E] [X] holiday (from: Jun 01 2024 to: Jun 02 2024)", event.toString());
    }

    @Test
    public void testToStorageString_withTime() {
        Event event = new Event("meeting /from 2024-06-01 1400 /to 2024-06-01 1600", false);
        assertEquals("E | 0 | meeting /from 2024-06-01 1400 /to 2024-06-01 1600", event.toStorageString());
    }

    @Test
    public void testToStorageString_dateOnly() {
        Event event = new Event("holiday /from 2024-06-01 /to 2024-06-02", true);
        assertEquals("E | 1 | holiday /from 2024-06-01 /to 2024-06-02", event.toStorageString());
    }
}
