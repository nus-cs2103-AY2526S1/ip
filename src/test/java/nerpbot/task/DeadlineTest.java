package nerpbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline d = new Deadline("submit report", "2025-09-01");
        String expected = "[D][ ] submit report (by: Sep 1 2025)";
        assertEquals(expected, d.toString());
    }
}
