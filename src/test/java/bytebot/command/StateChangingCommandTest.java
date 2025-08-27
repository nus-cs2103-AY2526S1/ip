package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.task.Task;
import bytebot.task.TaskList;
import bytebot.task.Todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class StateChangingCommandTest {

    private Storage storage;

    @BeforeEach
    public void setup() {
        storage = new Storage() {
            {
                initializeWithTaskList(new TaskList());
            }

            @Override
            public void saveTasks(TaskList tasks) {
            }
        };
        storage.addTask(new Todo("one"));
        storage.addTask(new Todo("two"));
    }

    @Test
    public void mark_marksTaskAndDisplayUi() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            new MarkCommand(1).execute(new bytebot.ui.Ui(), storage);
        } catch (Exception e) {
            fail("MarkCommand execution failed with exception: " + e.getMessage());
        } finally {
            System.setOut(original);
        }
        Task task = storage.getTask(1);
        assertEquals("X", task.getStatusIcon());
        assertTrue(baos.toString().contains("marked this task as done"));
    }

    @Test
    public void unmark_unmarksTaskAndDisplayUi() throws Exception {
        new MarkCommand(0).execute(new bytebot.ui.Ui(), storage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            new UnmarkCommand(0).execute(new bytebot.ui.Ui(), storage);
        } catch (Exception e) {
            fail("UnmarkCommand execution failed with exception: " + e.getMessage());
        } finally {
            System.setOut(original);
        }
        Task task = storage.getTask(0);
        assertEquals(" ", task.getStatusIcon());
        assertTrue(baos.toString().contains("marked this task as not done yet"));
    }

    @Test
    public void delete_removesTaskAndDisplayUi() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            new DeleteCommand(0).execute(new bytebot.ui.Ui(), storage);
        } catch (Exception e) {
            fail("DeleteCommand execution failed with exception: " + e.getMessage());
        } finally {
            System.setOut(original);
        }
        assertEquals(1, storage.getSize());
        assertTrue(baos.toString().contains("I have removed this task"));
    }

    @Test
    public void mark_withInvalidIndex_throws() {
        assertThrows(ByteException.class, () -> new MarkCommand(5).execute(new bytebot.ui.Ui(), storage));
    }
}