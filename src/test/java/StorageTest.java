import tarawrr.Storage;
import tarawrr.TarawrrException;
import tarawrr.TaskList;
import tarawrr.ToDos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    public void load_whenNoFile_returnsEmptyList() throws TarawrrException {
        Path file = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(file.toString());

        TaskList tasks = null;
        try {
            tasks = storage.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertTrue(tasks.getTasks().isEmpty());
    }

    @Test
    public void saveThenLoad_roundTrip_ok() throws TarawrrException, IOException {
        // Test that saving and then loading tasks works correctly (round-trip)
        Path file = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(file.toString());
        TaskList tasks = new TaskList();

        // Add a task to the list
        tasks.addToTaskList(new ToDos("do homework"));

        // Save tasks to the file
        storage.save(tasks);

        // Load tasks from the file
        TaskList loadedTasks = storage.load();

        // Assert that the loaded task is the same as the saved task
        assertEquals(1, loadedTasks.getTasks().size());
        assertEquals("[T] [ ] do homework", loadedTasks.getTasks().get(0).toString());
    }
}

