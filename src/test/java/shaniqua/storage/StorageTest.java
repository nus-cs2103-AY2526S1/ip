package shaniqua.storage;

import org.junit.jupiter.api.Test;
import shaniqua.taskcore.TaskList;
import shaniqua.taskcore.tasks.Todo;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void testSaveLoad() throws Exception {
        Storage store = new Storage("testdata");
        TaskList original = new TaskList();
        for (int i = 0; i < 1; i++) {
            original.addTask(new Todo(String.format("%d", i)));
        }
        store.saveToFile(original);
        TaskList temp = new TaskList();
        store.loadTasks(temp);
        assert(original.equals(temp));
    }
}