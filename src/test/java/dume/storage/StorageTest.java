package dume.task;

import dume.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @TempDir
    Path temp;

    @Test
    void save_then_load_preserves_tasks() {
        String file = temp.resolve("data/dume.txt").toString();
        Storage storage = new Storage(file);

        List<Task> initial = new java.util.ArrayList<>();
        initial.add(new Todo("eat cow"));
        initial.add(new Deadline("return book", "1/2/2023 1800"));
        initial.add(new Event("meeting", "1/2/2023 1200", "1/2/2023 1300"));

        storage.save(initial);

        List<Task> loaded = storage.load();
        assertEquals(3, loaded.size());
        assertEquals(initial.get(0).toFileFormat(), loaded.get(0).toFileFormat());
        assertEquals(initial.get(1).toFileFormat(), loaded.get(1).toFileFormat());
        assertEquals(initial.get(2).toFileFormat(), loaded.get(2).toFileFormat());
    }
}