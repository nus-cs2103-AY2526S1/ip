package jaiden.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jaiden.storage.Storage;
import jaiden.task.TaskList;

public class UnknownCommandTest {
    @Test
    public void executeTest() throws Exception {
        String[] commands = {"test"};
        TaskList test = new TaskList();
        new UnknownCommand(commands).execute(test, new Storage("data/test.txt"));
        assertEquals(new TaskList(), test);
    }

    @Test
    public void getStringTest() throws Exception {
        String[] commands = {"test"};
        TaskList test = new TaskList();
        Command command = new UnknownCommand(commands);
        command.execute(test, new Storage("data/test.txt"));
        assertEquals("Oopsie! 😅 I’m not too sure what that means… could you help me out?",
                command.getString());
    }

    @Test
    public void getCommandTest() {
        String[] commands = {"test"};
        Command command = new UnknownCommand(commands);
        assertEquals(CommandType.ERRORCOMMAND, command.getCommandType());
    }

    @Test
    public void equalsTest() {
        String[] commands1 = {"test"};
        String[] commands2 = {"test"};
        assertEquals(new UnknownCommand(commands1), new UnknownCommand(commands2));
    }
}
