package olaf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.io.File;
import java.util.ArrayList;

import olaf.tasks.Deadline;
import olaf.tasks.Event;
import olaf.tasks.Task;
import olaf.tasks.ToDo;

public class StorageTest {

    @Test
    public void saveAndLoad_singleToDo_successfulSavesAndLoads() {
        String testFile = "testData/dummy.txt";
        Storage storage = new Storage(testFile);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Sample"));
        storage.save(tasks);

        Storage storage2 = new Storage(testFile);
        ArrayList<Task> loadedTasks = storage2.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("Sample", loadedTasks.get(0).getDescription());

        new File(testFile).delete();
    }

    @Test
    public void saveAndLoad_multipleTasks_correctOrderAndTypes() {
        String testFile = "testData/dummy.txt";
        Storage storage = new Storage(testFile);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("First todo"));
        tasks.add(new Deadline("Second deadline", "2/10/2025 1800"));
        tasks.add(new Event("Third event", "2/10/2025 1800", "2/10/2025 2000"));
        storage.save(tasks);

        Storage storage2 = new Storage(testFile);
        ArrayList<Task> loadedTasks = storage2.load();
        assertEquals(3, loadedTasks.size());
        assertInstanceOf(ToDo.class, loadedTasks.get(0));
        assertInstanceOf(Deadline.class, loadedTasks.get(1));
        assertInstanceOf(Event.class, loadedTasks.get(2));

        new File(testFile).delete();
    }

    @Test
    public void load_nonExistentFile_EmptyList() {
        String testFile = "testData/noSuchFile.txt";
        File file = new File(testFile);
        if (file.exists()) {
            file.delete();
        }

        Storage storage = new Storage(testFile);
        ArrayList<Task> loadedTasks = storage.load();
        assertNotNull(loadedTasks);
        assertTrue(loadedTasks.isEmpty());
    }

    @Test
    public void saveAndLoad_doneStatus_preservesCompletion() {
        String testFile = "testData/dummy.txt";
        Storage storage = new Storage(testFile);
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo testTask = new ToDo("Todo test");
        testTask.markDone();
        tasks.add(testTask);
        storage.save(tasks);

        Storage storage2 = new Storage(testFile);
        ArrayList<Task> loadedTasks = storage2.load();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0).isDone());

        new File(testFile).delete();

    }
}
