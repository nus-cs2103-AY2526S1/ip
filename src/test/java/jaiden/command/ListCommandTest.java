package jaiden.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import jaiden.storage.Storage;
import jaiden.task.TaskList;

public class ListCommandTest {
    @Test
    public void executeTest() throws Exception {
        TaskList test = new TaskList();

        String[] commands1 = {"list"};
        new ListCommand(commands1).execute(test, new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);

        String[] commands2 = {"show", "2025-08-22"};
        new ListCommand(commands2).execute(test, new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);

        String[] commands3 = {"find", "test"};
        new ListCommand(commands3).execute(test, new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);
    }

    @Test
    public void getStringTest() throws Exception {
        TaskList test = new TaskList();

        String[] commands1 = {"list"};
        Command command1 = new ListCommand(commands1);
        command1.execute(test, new Storage("data/test.txt"));
        assertEquals("Here are the tasks in your list:\n", command1.getString());

        String[] commands2 = {"view", "2025-08-22"};
        Command command2 = new ListCommand(commands2);
        command2.execute(test, new Storage("data/test.txt"));
        assertEquals("Here are the tasks on Aug 22 2025 in your list:\n", command2.getString());

        String[] commands3 = {"find", "test"};
        Command command3 = new ListCommand(commands3);
        command3.execute(test, new Storage("data/test.txt"));
        assertEquals("Here are the matching tasks in your list:\n", command3.getString());
    }

    @Test
    public void getCommandTest() {
        String[] commands = {"list"};
        Command command = new ListCommand(commands);
        assertEquals(CommandType.LISTCOMMAND, command.getCommandType());
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"list"};
        String[] commands2 = {"list"};
        String[] commands3 = {"show", "2025-08-22"};
        assertEquals(new ListCommand(commands1), new ListCommand(commands2));
        assertNotEquals(new ListCommand(commands1), new ListCommand(commands3));
    }
}
