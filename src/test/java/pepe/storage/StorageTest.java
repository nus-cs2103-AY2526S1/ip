package pepe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;
import pepe.task.Deadlines;
import pepe.task.Events;
import pepe.task.Task;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;

class StorageTest {

    private File tempFile;
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("tasks", ".txt");
        tempFile.deleteOnExit();
        storage = new Storage(tempFile.getAbsolutePath());
    }

    @Test
    void testRawDateToString() throws PepeExceptions {
        String input = "Jan 1 2099";
        String expected = "2099-01-01";
        assertEquals(expected, storage.rawDateToString(input));
    }


    @Test
    void testSaveAndLoadWithToDos() throws IOException, PepeExceptions {
        TaskList tasks = new TaskList();
        ToDos todo1 = new ToDos("Task1");
        ToDos todo2 = new ToDos("Task2");
        todo2.markTask();
        tasks.addTask(todo1);
        tasks.addTask(todo2);

        storage.save(tasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(2, loadedTasks.size());
        assertEquals(todo1.toString(), loadedTasks.get(0).toString());
        assertEquals(todo2.toString(), loadedTasks.get(1).toString());
    }

    @Test
    void testSaveAndLoadWithDeadlines() throws IOException, PepeExceptions {
        TaskList tasks = new TaskList();
        Deadlines deadline = new Deadlines("Submit report", LocalDate.now().plusDays(2).toString());
        tasks.addTask(deadline);

        storage.save(tasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size());
        assertEquals(deadline.toString(), loadedTasks.get(0).toString());
    }

    @Test
    void testSaveAndLoadWithEvents() throws IOException, PepeExceptions {
        TaskList tasks = new TaskList();
        Events event = new Events("Conference",
                LocalDate.now().plusDays(1).toString(),
                LocalDate.now().plusDays(2).toString());
        tasks.addTask(event);

        storage.save(tasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size());
        assertEquals(event.toString(), loadedTasks.get(0).toString());
    }

    @Test
    void testLoadFileNotFoundCreatesFile() throws PepeExceptions {
        File nonExistent = new File("nonexistent_tasks.txt");
        nonExistent.delete();
        Storage newStorage = new Storage(nonExistent.getAbsolutePath());

        ArrayList<Task> loaded = newStorage.load();
        assertTrue(loaded.isEmpty());
        assertTrue(nonExistent.exists());
        nonExistent.delete();
    }
}
