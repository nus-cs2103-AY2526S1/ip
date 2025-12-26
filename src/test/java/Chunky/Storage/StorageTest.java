package Chunky.Storage;

import Chunky.Task.Task;
import Chunky.Task.ToDo;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StorageTest {
    private Storage storage;

    @Test
    public void testSaveAndLoadRoundTrip() throws IOException {
        storage = new Storage("./StorageTest.txt");
        ArrayList<Task> originalTasks = new ArrayList<>();
        originalTasks.add(new ToDo("test task", "capt"));

        storage.save(originalTasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size());
        assertEquals("test task", loadedTasks.get(0).getDescription());
    }

    @Test
    public void testLoadFromNonExistentFile() throws IOException {
        Storage storage = new Storage("./nonexistent.txt");
        ArrayList<Task> tasks = storage.load();
        assertEquals(0, tasks.size()); // Should return empty list
    }
}
