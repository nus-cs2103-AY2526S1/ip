package focus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TaskStorageTest {

    @TempDir
    Path tempDir;

    @Test
    //Save and load produces identical tasks
    void saveThenLoad_valid() throws IOException {

        DateTimeFormatter inputFormat =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // User input and storage format for events and deadline

        Path saveFile = tempDir.resolve("duke.txt");
        TaskStorage storage = new TaskStorage(saveFile.toString());

        // Build list
        TaskList list = new TaskList();
        list.addTask(new ToDo("read book"), false);
        list.addTask(new Deadline("submit report", LocalDateTime.parse("2025-09-10 1159", inputFormat)), false);
        list.addTask(new Event("project meeting",
                LocalDateTime.parse("2025-09-10 1159", inputFormat), LocalDateTime.parse("2025-09-11 1159",
                inputFormat)), false);

        storage.saveTasks(list);

        TaskList loaded = storage.loadTasks();

        assertEquals(list.size(), loaded.size());

        // Compare item by item (type + fields + storage encoding)
        for (int i = 0; i < list.size(); i++) {
            Task a = list.get(i);
            Task b = loaded.get(i);

            assertEquals(a.getClass(), b.getClass(), "Type mismatch at index " + i);
            assertEquals(a.getDescription(), b.getDescription(), "Description mismatch at " + i);
            assertEquals(a.isDone(), b.isDone(), "Done flag mismatch at " + i);
            assertEquals(a.toStorageString(), b.toStorageString(), "Encoding mismatch at " + i);

        }

    }

    @Test
    // Loading from a missing file returns an empty list without error
    void load_missingFile_returnsEmpty() throws IOException {
        Path saveFile = tempDir.resolve("does-not-exist.txt");
        TaskStorage storage = new TaskStorage(saveFile.toString());

        TaskList loaded = storage.loadTasks();
        assertNotNull(loaded);
        assertEquals(0, loaded.size());
    }

}
