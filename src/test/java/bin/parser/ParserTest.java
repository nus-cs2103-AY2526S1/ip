package bin.parser;

import bin.task.Task;
import bin.task.TaskList;
import bin.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_listCommand_success() {
        // test for list command
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo(false, "read books"));
        Parser parser = new Parser("list", new TaskList(tasks));
        String expected = "    Here are the tasks in your list:\n" +
                "    1.[T][ ] read books\n";
        assertEquals(expected, parser.parseCommand());
    }

    @Test
    public void parseCommand_todo_success() {
        // test for todo command
        String expected = "    Got it. I've added this task:\n      "
                + "[T]" + "[ ] read books\n"
                + "    Now you have 1 tasks in the list.\n";
        List<Task> tasks = new ArrayList<>();
        Parser parser = new Parser("todo read books", new TaskList(tasks));
        assertEquals(expected, parser.parseCommand());
    }
}
