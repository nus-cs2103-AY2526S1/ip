package dukeychatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dukeychatbot.tasktypes.Deadline;
import dukeychatbot.tasktypes.Event;

/**
 * Tests the functionality of components of Dukey chatbot.
 *
 * @author dongjun
 */
public class DukeyTest {

    @Test
    public void taskListMarkDone_indexOutOfRange_indexOutOfBoundsException() {
        try {
            Ui ui = new Ui();
            TaskList tasks = new TaskList(new ArrayList<>(), ui);
            tasks.markDone(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }
    }

    @Test
    public void deadlineToString_deadlineCommand_success() {
        Deadline deadline = new Deadline("Read Harry Potter /by 2019-10-10", false);
        assertEquals("[D] [ ] Read Harry Potter (by: Oct 10 2019)", deadline.toString());
    }

    @Test
    public void eventToString_deadlineCommand_success() {
        Event event = new Event("Utown Festival /from 2025-02-10 /to 2025-02-13", true);
        assertEquals("[E] [X] Utown Festival (from: Feb 10 2025 to: Feb 13 2025)", event.toString());
    }

}
