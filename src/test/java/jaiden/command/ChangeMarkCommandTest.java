package jaiden.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import jaiden.storage.Storage;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;

public class ChangeMarkCommandTest {
    @Test
    public void executeTest() throws Exception {
        String[] commands1 = {"mark", "1"};
        ArrayList<Task> tasks1 = new ArrayList<>();
        tasks1.add(new Todo("test"));
        TaskList test1 = new TaskList(tasks1);
        new ChangeMarkCommand(commands1).execute(test1, new Storage("data/test.txt"));
        ArrayList<Task> expectedTasks1 = new ArrayList<>();
        expectedTasks1.add(new Todo("test", true));
        TaskList expected1 = new TaskList(expectedTasks1);
        assertEquals(expected1, test1);

        String[] commands2 = {"unmark", "1"};
        ArrayList<Task> tasks2 = new ArrayList<>();
        tasks2.add(new Todo("test", true));
        TaskList test2 = new TaskList(tasks2);
        new ChangeMarkCommand(commands2).execute(test2, new Storage("data/test.txt"));
        ArrayList<Task> expectedTasks2 = new ArrayList<>();
        expectedTasks2.add(new Todo("test"));
        TaskList expected2 = new TaskList(expectedTasks2);
        assertEquals(expected2, test2);
    }

    @Test
    public void getStringTest() throws Exception {
        String[] commands1 = {"mark", "1"};
        ArrayList<Task> tasks1 = new ArrayList<>();
        tasks1.add(new Todo("test"));
        TaskList test1 = new TaskList(tasks1);
        Command command1 = new ChangeMarkCommand(commands1);
        command1.execute(test1, new Storage("data/test.txt"));
        assertEquals("Nice! I've marked this task as done:\n[T][X] test", command1.getString());

        String[] commands2 = {"unmark", "1"};
        ArrayList<Task> tasks2 = new ArrayList<>();
        tasks2.add(new Todo("test"));
        TaskList test2 = new TaskList(tasks2);
        Command command2 = new ChangeMarkCommand(commands2);
        command2.execute(test2, new Storage("data/test.txt"));
        assertEquals("OK, I've marked this task as not done yet:\n[T][ ] test", command2.getString());
    }

    @Test
    public void getCommandTest() {
        String[] commands = {"mark", "1"};
        Command command = new ChangeMarkCommand(commands);
        assertEquals(CommandType.CHANGEMARKCOMMAND, command.getCommandType());
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"mark", "1"};
        String[] commands2 = {"mark", "1"};
        String[] commands3 = {"mark", "2"};
        assertEquals(new ChangeMarkCommand(commands1), new ChangeMarkCommand(commands2));
        assertNotEquals(new ChangeMarkCommand(commands1), new ChangeMarkCommand(commands3));
    }
}
