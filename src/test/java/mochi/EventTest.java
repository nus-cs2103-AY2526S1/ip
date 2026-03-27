package mochi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void createValidEventTest() {
        try {
            Event e = new Event("Running a marathon", "2025-08-12 1400", "2025-08-13");
            assertEquals("[E][ ] Running a marathon (from 12/Aug/2025 14:00 to 13/Aug/2025 00:00)", e.toString());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void invalidDateTest() {
        Throwable exception = assertThrows(EventException.class, () -> {
            Event e = new Event("Sleeping in", "2023-05-11", "2023-02-11");
        });
        assertEquals("""
                ========================== ERROR ===========================
                 Very confused, much wow. Invalid command entered.
                 Enter command 'help' for a list of commands
                ============================================================
                ------------------------- Details -------------------------
                 Invalid event command used.
                 The end time must be after the start time.
                -----------------------------------------------------------""",
                exception.toString());
    }
}
