package panda.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import panda.PandaException;

public class DeadlineTest {

    @Test
    public void parseDate_invalidDate_exceptionThrown() {
        String exceptionMessage = "Please provide a valid date in the format yyyy-MM-dd HH:mm!";
        try {
            assertNotNull(new Deadline("test", "01-09-2025 10:00"));
        } catch (PandaException e) {
            assertEquals(exceptionMessage, e.getMessage());
        }
    }
}
