package jaiden.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import jaiden.storage.Storage;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;

public class AddCommandTest {
    @Test
    public void executeTest() throws Exception {
        String[] commands1 = {"todo", "test"};
        TaskList test1 = new TaskList();
        new AddCommand(commands1).execute(test1, new Storage("data/test.txt"));
        ArrayList<Task> tasks1 = new ArrayList<>();
        tasks1.add(new Todo("test"));
        TaskList expected1 = new TaskList(tasks1);
        assertEquals(expected1, test1);

        String[] commands2 = {"deadline", "test", "/by", "2025-08-22"};
        TaskList test2 = new TaskList();
        new AddCommand(commands2).execute(test2, new Storage("data/test.txt"));
        ArrayList<Task> tasks2 = new ArrayList<>();
        tasks2.add(new Todo("test"));
        TaskList expected2 = new TaskList(tasks2);
        assertEquals(expected2, test2);

        String[] commands3 = {"event", "test", "/from", "2025-08-22", "/to", "2025-08-22"};
        TaskList test3 = new TaskList();
        new AddCommand(commands3).execute(test3, new Storage("data/test.txt"));
        ArrayList<Task> tasks3 = new ArrayList<>();
        tasks3.add(new Todo("test"));
        TaskList expected3 = new TaskList(tasks3);
        assertEquals(expected3, test3);
    }

    @Test
    public void getStringTest() throws Exception {
        String[] commands1 = {"todo", "test"};
        TaskList test1 = new TaskList();
        Command command1 = new AddCommand(commands1);
        command1.execute(test1, new Storage("data/test.txt"));
        assertEquals("Got it. I've added this task:\n[T][ ] test\nNow you have 1 tasks in the list.",
                command1.getString());

        String[] commands2 = {"deadline", "test", "/by", "2025-08-22"};
        TaskList test2 = new TaskList();
        Command command2 = new AddCommand(commands2);
        command2.execute(test2, new Storage("data/test.txt"));
        assertEquals("Got it. I've added this task:\n[D][ ] test (by: Aug 22 2025)\n"
                + "Now you have 1 tasks in the list.", command2.getString());

        String[] commands3 = {"event", "test", "/from", "2025-08-22", "/to", "2025-08-22"};
        TaskList test3 = new TaskList();
        Command command3 = new AddCommand(commands3);
        command3.execute(test3, new Storage("data/test.txt"));
        assertEquals("Got it. I've added this task:\n[E][ ] test (from: Aug 22 2025 to: Aug 22 2025)\n"
                + "Now you have 1 tasks in the list.", command3.getString());
    }

    @Test
    public void getCommandTest() {
        String[] commands = {"todo", "test"};
        Command command = new AddCommand(commands);
        assertEquals(CommandType.ADDCOMMAND, command.getCommandType());
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"todo", "test1"};
        String[] commands2 = {"todo", "test1"};
        String[] commands3 = {"todo", "test2"};
        assertEquals(new AddCommand(commands1), new AddCommand(commands2));
        assertNotEquals(new AddCommand(commands1), new AddCommand(commands3));
    }
}
