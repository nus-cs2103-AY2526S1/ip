package balloon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import balloon.logic.Storage;
import balloon.task.Deadline;

public class StorageTest {
    @Test
    public void parseLine_illegalArgument_exceptionThrown() {
        String line = "UNKNOWN | 1 | description";
        try {
            Storage storage = new Storage();
            storage.parseLineForTask(line);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown task type in save file: UNKNOWN",
                    e.getMessage());
        }
    }

    @Test
    public void parseLine_success() {
        String line = "DEADLINE | 0 | CS2103T iP homework | 2025-09-01 2359";
        try {
            Storage storage = new Storage();
            assertEquals(new Deadline("CS2103T iP homework", "2025-09-01 2359"),
                    storage.parseLineForTask(line));
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
}
