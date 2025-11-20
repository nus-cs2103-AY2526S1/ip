package storage;

import tasklists.TaskList;
import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.ToDos;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    void saveAndLoad_persistsMultipleTaskTypesAndStatuses() throws Exception {
        Path tempFile = Files.createTempFile("storageTest", ".txt");
        tempFile.toFile().deleteOnExit();
        Storage storage = new Storage(tempFile.toString());

        TaskList original = new TaskList();
        ToDos todo = new ToDos("buy milk");
        Deadline deadline = new Deadline("submit report", "2025-12-31");
        // Use ISO date strings for Events (yyyy-MM-dd) to match Events constructor
        Events event = new Events("meeting", "2025-03-01", "2025-03-02");

        // mark deadline as done
        deadline.markAsDone();

        original.addTask(todo);
        original.addTask(deadline);
        original.addTask(event);

        // save
        storage.saveTasks(original);

        // load into fresh TaskList
        TaskList loaded = new TaskList();
        storage.loadTasks(loaded);

        List<Task> tasks = loaded.getTasks();
        assertEquals(3, tasks.size(), "Should load three tasks");

        // Verify types and descriptions and done status preserved
        assertTrue(tasks.get(0) instanceof ToDos);
        assertEquals("buy milk", tasks.get(0).getDescription());
        assertFalse(tasks.get(0).isDone());

        assertTrue(tasks.get(1) instanceof Deadline);
        assertEquals("submit report", tasks.get(1).getDescription());
        assertTrue(tasks.get(1).isDone(), "Deadline was marked done and should be persisted as done");

        assertTrue(tasks.get(2) instanceof Events);
        assertEquals("meeting", tasks.get(2).getDescription());
        assertFalse(tasks.get(2).isDone());
    }

    @Test
    void load_skipsMalformedLines_andSaveWritesExpectedFormat() throws Exception {
        Path tempFile = Files.createTempFile("storageTest2", ".txt");
        tempFile.toFile().deleteOnExit();
        Storage storage = new Storage(tempFile.toString());

        // create a TaskList and save one todo to ensure file exists and is in known format
        TaskList list = new TaskList();
        list.addTask(new ToDos("alpha"));
        storage.saveTasks(list);

        // verify file contains expected formatted line for ToDos
        String content = Files.readString(tempFile);
        assertTrue(content.contains("T | 0 | alpha"), "Saved file should contain a todo line in the expected format");

        // Now overwrite with a mixture of malformed and well-formed lines
        String mixed = "bad line without separators\n" +
                "T | 1 | goodtodo\n" +
                "D | 0 | desc only missing date\n" +
                "D | 0 | bydate | 2025-01-01\n" +
                // Provide a well-formed event line using ISO dates so Events can be parsed
                "E | 0 | ev | 2025-01-01 to 2025-01-02\n";
        Files.writeString(tempFile, mixed);

        TaskList loaded = new TaskList();
        storage.loadTasks(loaded);

        // Should load only the well-formed entries (goodtodo, bydate deadline, ev event)
        assertEquals(3, loaded.getTasks().size(), "Should skip malformed lines and load only valid entries");

        assertTrue(loaded.getTasks().stream().anyMatch(t -> t instanceof ToDos && t.getDescription().equals("goodtodo")));
        assertTrue(loaded.getTasks().stream().anyMatch(t -> t instanceof Deadline && t.getDescription().equals("bydate")));
        assertTrue(loaded.getTasks().stream().anyMatch(t -> t instanceof Events && t.getDescription().equals("ev")));
    }
}
