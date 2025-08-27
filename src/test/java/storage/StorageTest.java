package storage;

import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private static final String TEST_FILE_PATH = "data/test_rainy.txt";

    @Test
    void testSaveAndLoadTodo() {
        Storage storage = new Storage(TEST_FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertInstanceOf(Todo.class, loadedTasks.get(0));
        assertTrue(loadedTasks.get(0).toString().contains("read book"));
    }

    @Test
    void testSaveAndLoadDeadline() {
        Storage storage = new Storage(TEST_FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("submit report", LocalDateTime.parse("2025-08-30T23:59")));
        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertInstanceOf(Deadline.class, loadedTasks.get(0));
        assertTrue(loadedTasks.get(0).toString().contains("submit report"));
    }

    @Test
    void testSaveAndLoadEvent() {
        Storage storage = new Storage(TEST_FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Event("meeting", LocalDateTime.parse("2025-08-28T14:00"), LocalDateTime.parse("2025-08-28T16:00")));
        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertInstanceOf(Event.class, loadedTasks.get(0));
        assertTrue(loadedTasks.get(0).toString().contains("meeting"));
    }

    @Test
    void testFileActuallyCreated() {
        Storage storage = new Storage(TEST_FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("check file creation"));
        storage.save(tasks);

        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists());
    }
}
