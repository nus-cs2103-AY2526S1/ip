package Skye.classes;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void event_testStringConversion(){
        Event test = new Event("Exchange Guest", LocalDate.parse("2025-10-01"),
                LocalDate.parse("2025-10-07"));
        assertEquals("[E][ ] Exchange Guest (from: Oct 01 2025 to: Oct 07 2025)", test.toString());
    }
}
