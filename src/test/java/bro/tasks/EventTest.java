package bro.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void constructor_setsDescriptionAndDates() {
        Event e = new Event("desc", "2/12/2019 0000", "2/12/2019 1000");
        assertEquals("desc", e.getDescription());
        assertNotNull(e.from);
        assertNotNull(e.to);
    }

    @Test
    public void toDataString_returnsCorrectFormat() {
        Event e = new Event("desc", true, "2/12/2019 0000", "2/12/2019 1000");
        assertTrue(e.toDataString().contains("E|1|desc|2/12/2019 0000|2/12/2019 1000"),
                "Data string should contain 'E|1|desc|2/12/2019 0000|2/12/2019 1000'");
    }
}
