package angsoontong.parser;

import angsoontong.task.*;
import angsoontong.ui.Ui;
import angsoontong.storage.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    /**
     * test for parsing command to add tasks to the task list
     */
    @Test
    public void parse_addTodo_success() {
        Storage storage = new Storage("data/test-data.txt");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        String response = Parser.parse("todo read book", tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof ToDo);
        assertTrue(response.contains("Steady! I add this already"));
    }

    /**
     * test for parsing command to mark tasks as done
     */
    @Test
    public void parse_markTodo_success() {
        Storage storage = new Storage("data/test-data.txt");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        tasks.add(new ToDo("write tests"));
        Parser.parse("mark 1", tasks, ui, storage);

        assertTrue(tasks.get(0).isDone());
    }
}
