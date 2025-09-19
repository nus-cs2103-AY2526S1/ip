package bytebot.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import bytebot.storage.Storage;
import bytebot.task.TaskList;

public class OtherCommandTest {

    @Test
    public void listCommand_showsTasksCount() {
        Storage storage = new Storage() {
            {
                initializeWithTaskList(new TaskList());
            }
        };
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            new ListCommand().execute(new bytebot.ui.Ui(), storage);
        } catch (Exception e) {
            fail("ListCommand execution failed with exception: " + e.getMessage());
        } finally {
            System.setOut(original);
        }
        assertTrue(baos.toString().contains("Here are the byte tasks what's on your list"));
    }

    @Test
    public void exitCommand_setsExitFlagWithFarewell() throws Exception {
        Storage storage = new Storage() {
            {
                initializeWithTaskList(new TaskList());
            }
        };
        Command cmd = new ExitCommand();
        assertTrue(cmd.isExit());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            cmd.execute(new bytebot.ui.Ui(), storage);
        } catch (Exception e) {
            fail("ExitCommand execution failed with exception: " + e.getMessage());
        } finally {
            System.setOut(original);
        }
        assertTrue(baos.toString().contains("Bytebye, Looking forward to helping you again soon."));
    }
}
