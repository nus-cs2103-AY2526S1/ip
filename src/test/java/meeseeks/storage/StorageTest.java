package meeseeks.storage;

import meeseeks.task.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;

class StorageTest {

    @Test
    void testSaveAndLoad() throws Exception {
        String testFilePath = "data/test_meeseeks.txt";
        Storage storage = new Storage(testFilePath);

        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test task"));
        tasks.add(new Deadline("submit report", LocalDateTime.of(2025, 9, 3, 18, 0)));

        storage.save(tasks);

        ArrayList<Task> loaded = storage.load();
        assertEquals(2, loaded.size());
        assertEquals("test task", loaded.get(0).getName());

        // cleanup
        Files.deleteIfExists(new File(testFilePath).toPath());
    }
}