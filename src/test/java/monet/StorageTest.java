package monet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StorageTest {

    // JUnit will create a temporary directory for this test and clean it up afterward.
    @TempDir
    Path tempDir;

    @Test
    public void saveAndLoad_tasks_dataPreserved() throws MonetException, IOException {
        // 1. Setup: Create a list of various tasks.
        ArrayList<Task> originalTasks = new ArrayList<>();
        originalTasks.add(new Todo("read book", Priority.LOW));
        originalTasks.add(new Deadline("submit report", "2025-10-15 1800", Priority.HIGH));

        // 2. Execute Save: Save the tasks to a file in the temporary directory.
        Path filePath = tempDir.resolve("monet.txt");
        Storage storage = new Storage(filePath.toString());
        storage.save(originalTasks);

        // 3. Execute Load: Load the tasks back from the same file.
        ArrayList<Task> loadedTasks = storage.load();

        // 4. Assert: Check if the loaded data is identical to the original.
        assertEquals(originalTasks.size(), loadedTasks.size());
        assertEquals(originalTasks.get(0).toFileString(), loadedTasks.get(0).toFileString());
        assertEquals(originalTasks.get(1).toFileString(), loadedTasks.get(1).toFileString());
    }
}

