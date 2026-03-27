package jaiden.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import jaiden.storage.Storage;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;

public class DeleteCommandTest {
    @Test
    public void executeTest() throws Exception {
        String[] commands = {"delete", "1"};
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));
        TaskList test = new TaskList(tasks);
        new DeleteCommand(commands).execute(test, new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);
    }

    @Test
    public void getStringTest() throws Exception {
        String[] commands = {"delete", "1"};
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));
        TaskList test = new TaskList(tasks);
        Command command = new DeleteCommand(commands);
        command.execute(test, new Storage("data/test.txt"));
        assertEquals("Noted. I've removed this task:\n[T][ ] test\nNow you have 0 tasks in the list.",
                command.getString());
    }

    @Test
    public void getCommandTest() {
        String[] commands = {"delete", "1"};
        Command command = new DeleteCommand(commands);
        assertEquals(CommandType.DELETECOMMAND, command.getCommandType());
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"delete", "1"};
        String[] commands2 = {"delete", "1"};
        String[] commands3 = {"delete", "2"};
        assertEquals(new DeleteCommand(commands1), new DeleteCommand(commands2));
        assertNotEquals(new DeleteCommand(commands1), new DeleteCommand(commands3));
    }
}
