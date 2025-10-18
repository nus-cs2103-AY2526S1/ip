package uy;  

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void testEventCreation() {
        Events event = new Events("Meeting /from 2025-08-30 /to 2025-08-31");
        assertEquals(event.toString(), "[E][] Meeting (from: Aug 30 2025 to: Aug 31 2025)");
    }
}
