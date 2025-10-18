package uy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ParserFlowTest {
    @Test
    public void testAddMarkUnmarkDeleteFlow() throws Exception {
        Parser parser = new Parser();
        TaskList tasks = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("data");

        // add a deadline
        String addRes = parser.parseAndRun("deadline Submit report /by 2025-10-20", tasks, ui, storage);
        assertTrue(addRes.contains("A new pebble on your path"));
        assertEquals(1, tasks.getTaskCount());

        // mark the task
        String markRes = parser.parseAndRun("mark 1", tasks, ui, storage);
        assertTrue(markRes.contains("Another pebble moved"));
    assertTrue(tasks.getTask(0).getMarked());

        // unmark the task
        String unmarkRes = parser.parseAndRun("unmark 1", tasks, ui, storage);
        assertTrue(unmarkRes.contains("This pebble is back on your path"));
    assertFalse(tasks.getTask(0).getMarked());

        // delete the task
        String delRes = parser.parseAndRun("delete 1", tasks, ui, storage);
        assertTrue(delRes.contains("Letting go is part of the journey"));
        assertEquals(0, tasks.getTaskCount());
    }
}
