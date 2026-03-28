package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lenny.logic.storage.Storage;
import lenny.logic.task.Deadline;
import lenny.logic.task.Task;
import lenny.logic.task.TaskList;
import lenny.logic.task.Todo;





class StorageTest {

    @Test
    void saveAndLoad_roundTrip_preservesTasks() throws Exception {
        Path tempFile = Files.createTempFile("tasks", ".txt");
        Storage storage = new Storage(tempFile.toString());
        storage.ensureFile();

        TaskList tasks = new TaskList();
        tasks.add(new Todo("test1", false));
        tasks.add(new Deadline("report", "2025-09-01", true));

        storage.save(tasks);
        ArrayList<Task> reloaded = storage.load();

        assertEquals(2, reloaded.size());
        assertEquals("test1", reloaded.get(0).getTaskName());
        assertTrue(reloaded.get(1).getIsDone());
    }
}
