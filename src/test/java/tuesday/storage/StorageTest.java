package tuesday.storage;
import org.junit.jupiter.api.Test;
import tuesday.task.DeadlineTask;
import tuesday.task.TaskList;
import tuesday.task.TodoTask;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void saveAndLoadDataTest() throws Exception {
        File tempFile = File.createTempFile("testing", ".txt");
        Storage storage = new Storage(tempFile.getAbsolutePath());

        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask("read book"));
        tasks.addTask(new DeadlineTask("submit homework", "02-03-2025 1615"));

        storage.saveData(tasks.getTasks());

        TaskList loaded = new TaskList(storage.loadData());
        assertEquals(2, loaded.size());
        assertEquals("[T][ ] read book", loaded.getTask(0).toString());
    }

}
