package evansbot.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private Path tempFile;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("evansbot", ".txt");
        storage = new Storage(tempFile.toString());
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testSaveAndLoad_ToDo() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book", true));

        storage.saveTasks(tasks);
        ArrayList<Task> loaded = storage.loadTasks();

        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0) instanceof ToDo);
        assertEquals("read book", loaded.get(0).getDescription());
        assertTrue(loaded.get(0).isDone());
    }

    @Test
    public void testSaveAndLoad_Deadline() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("submit report", "2025-08-30"));

        storage.saveTasks(tasks);
        ArrayList<Task> loaded = storage.loadTasks();

        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0) instanceof Deadline);
        assertEquals("submit report", loaded.get(0).getDescription());
        Deadline d = (Deadline) loaded.get(0);
        assertEquals("2025-08-30", d.byRaw);
    }

    @Test
    public void testSaveAndLoad_Event() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Event("team meeting", "2025-08-28", "2025-08-29"));

        storage.saveTasks(tasks);
        ArrayList<Task> loaded = storage.loadTasks();

        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0) instanceof Event);
        assertEquals("team meeting", loaded.get(0).getDescription());
        Event e = (Event) loaded.get(0);
        assertEquals("2025-08-28", e.fromRaw);
        assertEquals("2025-08-29", e.toRaw);
    }

    @Test
    public void testLoad_EmptyFile() throws IOException {
        ArrayList<Task> loaded = storage.loadTasks();
        assertTrue(loaded.isEmpty());
    }

}
