package fish.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void toString_format() {
        Event event = new Event("barre", "2025-08-25 1800", "2025-08-25 1915");
        String expected = "[E][ ] barre (from: 2025-08-25T18:00, to: 2025-08-25T19:15)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void date_Valid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Event("barre", "2025-08-25 1800", "2025-08-25 1700"));
    }
}
