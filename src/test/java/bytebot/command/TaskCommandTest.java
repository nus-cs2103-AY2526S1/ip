package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.task.Task;
import bytebot.task.TaskList;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskCommandTest {

    private Storage createStorage() {
        return new Storage() {
            {
                initializeWithTaskList(new TaskList());
            }

            @Override
            public void saveTasks(TaskList tasks) {
            }

            @Override
            public void addTask(Task task) {
                getTaskList().add(task);
            }
        };
    }

    @Test
    public void todoCommand_addsTaskAndNotifiesUi() throws Exception {
        Storage storage = createStorage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            new TodoCommand("read book").execute(new bytebot.ui.Ui(), storage);
        } catch (Exception e) {
            fail("TodoCommand execution failed with exception: " + e.getMessage());
        } finally {
            System.setOut(original);
        }
        assertEquals(1, storage.getSize());
        assertTrue(baos.toString().contains("I've added this task"));
    }

    @Test
    public void todoCommand_emptyDescription() {
        Storage storage = createStorage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            ByteException ex = assertThrows(ByteException.class, () ->
                    new TodoCommand(" ").execute(new bytebot.ui.Ui(), storage)
            );
            assertTrue(ex.getMessage().contains("description"));
        } catch (Exception e) {
            fail("TodoCommand empty description test failed with exception: " + e.getMessage());
        } finally {
            System.setOut(original);
        }
    }

    @Test
    public void deadlineCommand_addsTask() throws Exception {
        Storage storage = createStorage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            new DeadlineCommand("submit", "1/4/2025 0800").execute(new bytebot.ui.Ui(), storage);
        } catch (Exception e) {
            fail("DeadlineCommand execution failed with exception: " + e.getMessage());
        } finally {
            System.setOut(original);
        }
        assertEquals(1, storage.getSize());
    }

    @Test
    public void deadlineCommand_missingBy_throws() {
        Storage storage = createStorage();
        assertThrows(ByteException.class, () ->
                new DeadlineCommand("assignment", " ").execute(new bytebot.ui.Ui(), storage)
        );
    }

    @Test
    public void eventCommand_addsTask() throws Exception {
        Storage storage = createStorage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            new EventCommand("carnival", "7/1/2025 1030", "9/1/2025 1700").execute(new bytebot.ui.Ui(), storage);
        } catch (Exception e) {
            fail("EventCommand execution failed with exception: " + e.getMessage());
        } finally {
            System.setOut(original);
        }
        assertEquals(1, storage.getSize());
    }
}