package pickle.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import pickle.task.Deadline;
import pickle.task.TaskList;
import pickle.task.ToDos;

class StorageTest {

    @TempDir Path tmp;

    @Test
    void saveThenLoadRestoresTasks() throws Exception {
        Storage storage = new Storage(tmp.resolve("tasks.txt").toString());

        TaskList list = new TaskList();
        list.add(new ToDos("read"));
        list.add(new Deadline("return book", "2019-12-04 1800"));


        storage.save(list.all());
        TaskList loaded = new TaskList(storage.load());

        assertEquals(list.size(), loaded.size());
        assertTrue(loaded.get(1).toString().contains("read"));
        assertEquals(list.get(2).toString(), loaded.get(2).toString()); //compare add and loaded after save is same

    }

    @Test
    void loadFileMissing() throws Exception {
        Storage storage = new Storage(tmp.resolve("empty.txt").toString());
        TaskList loaded = new TaskList(storage.load());
        assertEquals(0, loaded.size());
    }
}
