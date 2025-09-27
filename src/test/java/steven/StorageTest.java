package steven;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import steven.storage.Storage;
import steven.task.Task;

public class StorageTest {

    @Test
    public void fetchTasks_testTasksInputFile_success() {
        ArrayList<Task> tasks = new Storage("src/test/java/steven/test_tasks.txt").fetchTasks();
        assertEquals(3, tasks.size());
    }

    @Test
    void fetchAndSaveTasks_validInput_success() {
        Storage storage = new Storage("src/test/java/steven/test_file.txt");
        ArrayList<Task> tasks = storage.fetchTasks();
        assertEquals(1, tasks.size());
        tasks.clear();
        storage.saveToLocal(tasks);
        assertEquals(0, storage.fetchTasks().size());
        storage.loadTask("[T][ ] task", tasks);
        storage.loadTask("[E][ ] event (from: 20-10-2093 1532 to: 28-09-2039 1234)", tasks);
        storage.saveToLocal(tasks);
        assertEquals(2, tasks.size());
        tasks.clear();
        storage.saveToLocal(tasks);
        assertEquals(0, storage.fetchTasks().size());
        storage.loadTask("[D][X] deadline (by: 20-09-2023 1231)", tasks);
        storage.saveToLocal(tasks);
    }
}
