package cate.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cate.task.Deadline;
import cate.task.Event;
import cate.task.Task;
import cate.task.TaskList;
import cate.task.Todo;

public class StorageTest {
    private File tempFile;
    private Storage storage;

    @BeforeEach
    public void setUp() throws Exception {
        tempFile = File.createTempFile("testStorage", ".txt");
        storage = new Storage(tempFile.getAbsolutePath());
    }

    @AfterEach
    public void tearDown() {
        tempFile.delete();
    }

    @Test
    public void load_emptyFile_returnsEmptyList() {
        List<Task> tasks = storage.load();
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void saveTask_singleTodo_loadedCorrectly() {
        Todo todo = new Todo("Test todo");
        storage.saveTask(todo);

        List<Task> loaded = storage.load();
        assertEquals(1, loaded.size());
        assertEquals(todo.toString(), loaded.get(0).toString());
    }

    @Test
    public void saveTask_markedTodo_loadedCorrectly() {
        Todo todo = new Todo("Do something");
        todo.markDone();
        storage.saveTask(todo);

        List<Task> loaded = storage.load();
        assertEquals(1, loaded.size());
        assertEquals(todo.toString(), loaded.get(0).toString());
    }

    @Test
    public void save_multipleTasks_loadedCorrectly() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("Todo 1");
        Deadline deadline = new Deadline("Deadline 1", LocalDateTime.now());
        Event event = new Event("Event 1", "10:00", "11:00");
        tasks.addTask(todo);
        tasks.addTask(deadline);
        tasks.addTask(event);

        storage.save(tasks);

        List<Task> loaded = storage.load();
        assertEquals(3, loaded.size());
        assertEquals(todo.toString(), loaded.get(0).toString());
        assertEquals(deadline.toString(), loaded.get(1).toString());
        assertEquals(event.toString(), loaded.get(2).toString());
    }

    @Test
    public void saveTask_appendsMultipleTasks() {
        Todo t1 = new Todo("First");
        Todo t2 = new Todo("Second");
        storage.saveTask(t1);
        storage.saveTask(t2);

        List<Task> loaded = storage.load();
        assertEquals(2, loaded.size());
        assertEquals(t1.toString(), loaded.get(0).toString());
        assertEquals(t2.toString(), loaded.get(1).toString());
    }

    @Test
    public void ensureFileExists_createsFileIfMissing() throws Exception {
        File nonExistent = new File(tempFile.getAbsolutePath() + "2");
        Storage s = new Storage(nonExistent.getAbsolutePath());
        s.saveTask(new Todo("Hello"));
        assertTrue(nonExistent.exists());
    }
}
