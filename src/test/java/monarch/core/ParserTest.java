package monarch.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import monarch.exceptions.MonException;


public class ParserTest {
    @Test
    public void bye_success() {
        Storage storage = new Storage();
        storage.set("junit_test.txt");
        TaskList tasks = new TaskList();
        tasks.set();
        Parser parser = new Parser("bye");

        assertTrue(parser.isEnd());
    }

    @Test
    public void mark_invalidRange_exceptionThrown() {
        Storage storage = new Storage();
        storage.set("junit_test.txt");
        TaskList tasks = new TaskList();
        tasks.set();
        try {
            Parser parser = new Parser("mark 10000000");
            fail();
        } catch (MonException e) {
            assertEquals("UH-OH: The task isn't in the range of the task list.", e.getMessage());
        }

    }
}
