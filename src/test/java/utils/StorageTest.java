package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import command.ByeCommand;
import command.MarkCommand;
import command.TodoCommand;
import task.TaskList;
import ui.Lmbd;

public class StorageTest {
    private static final String SAVE_PATH = "StorageTest.save";
    private Lmbd lmbd;

    public StorageTest() {
        System.setIn(new ByteArrayInputStream("todo test\ntodo test2\nmark 2\nbye\n".getBytes()));
        lmbd = new Lmbd(SAVE_PATH, new TodoCommand(), new MarkCommand(), new ByeCommand());
        lmbd.getResponse("todo test");
        lmbd.getResponse("todo test2");
        lmbd.getResponse("mark 2");
        lmbd.getResponse("bye");
    }

    @Test
    public void saveAndLoad_todoEvents_loadTodoEvents() {
        try {
            TaskList tl = Storage.load(SAVE_PATH);
            assertEquals(2, tl.getTaskSize(), "Expected task size to be 2");
            assertEquals("test", tl.getTaskTitle(0), "Expected name of first task to be \"test\"");
            assertEquals("test2", tl.getTaskTitle(1), "Expected name of second task to be \"test2\"");
            assertEquals(false, tl.isMarked(0), "Expected first task to not be marked as done");
            assertEquals(true, tl.isMarked(1), "Expected second task to be marked as done");
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @AfterAll
    public static void cleanup() {
        new File(SAVE_PATH).delete();
    }
}
