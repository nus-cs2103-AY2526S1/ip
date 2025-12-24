package echo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import echo.task.Deadline;
import echo.task.Task;
import echo.task.Todo;
import echo.tasklist.TaskList;

public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    public void testConstructor() {
        File f = tempDir.resolve("tempDir/echo.txt").toFile();
        new Storage(f.getAbsolutePath());
        assertTrue(f.exists());
    }

    @Test
    public void testEmptyFile() {
        File f = tempDir.resolve("tempDir/echo.txt").toFile();
        Storage storage = new Storage(f.getAbsolutePath());
        TaskList list = new TaskList(storage.readFile().getList());
        assertTrue(list.getList().isEmpty());
    }

    @Test
    public void testSaveReadFile() {
        File f = tempDir.resolve("tempDir/echo.txt").toFile();
        Storage storage = new Storage(f.getAbsolutePath());

        Task task1 = new Todo("description");
        Task task2 = new Deadline("description",
                LocalDateTime.of(2025, 9, 15, 23, 59));

        List<Task> list = List.of(task1, task2);
        storage.saveFile(new TaskList(list));

        List<Task> savedList = storage.readFile().getList();

        assertEquals(2, savedList.size());
        assertEquals("description", savedList.get(0).getDescription());
        assertEquals("description", savedList.get(1).getDescription());
    }

    @Test
    public void testMultipleSavesOverwriteFile() {
        File f = tempDir.resolve("tempDir/echo.txt").toFile();
        Storage storage = new Storage(f.getAbsolutePath());

        storage.saveFile(new TaskList(List.of(new Todo("task1"), new Todo("task2"))));
        storage.saveFile(new TaskList(List.of(new Todo("only task"))));

        List<Task> savedList = storage.readFile().getList();
        assertEquals(1, savedList.size());
        assertEquals("only task", savedList.get(0).getDescription());
    }

    @Test
    public void testSpecialCharactersInDescription() {
        File f = tempDir.resolve("echo.txt").toFile();
        Storage storage = new Storage(f.getAbsolutePath());

        String specialDescription = "hello, world |with|pipes";
        storage.saveFile(new TaskList(List.of(new Todo(specialDescription))));

        List<Task> savedList = storage.readFile().getList();
        assertEquals(1, savedList.size());
        assertEquals(specialDescription, savedList.get(0).getDescription());
    }
}
