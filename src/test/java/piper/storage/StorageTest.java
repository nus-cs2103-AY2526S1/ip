package piper.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import piper.PiperException;
import piper.task.Deadline;
import piper.task.Event;
import piper.task.TaskList;
import piper.task.Todo;

class StorageTest {

    @Test
    void saveAndLoad_success(@TempDir Path tempDir) throws PiperException {
        // file under tempDir
        Path saveFile = tempDir.resolve("piper-test.txt");
        Storage storage = new Storage(tempDir.toString(), "piper-test.txt");

        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read book"));
        tasks.addTask(new Deadline("submit report", "2025-09-01"));
        tasks.addTask(new Event("trip", "2025-09-05", "2025-09-10"));

        // save then load
        storage.saveAll(tasks);
        TaskList loaded = storage.load();

        // array length matches
        assertEquals(tasks.getSize(), loaded.getSize());
        // first task matches
        assertTrue(loaded.getTask(0).toSerializedLine().contains("read book"));
        // second task is a deadline with correct by
        assertTrue(loaded.getTask(1).toSerializedLine().contains("2025-09-01"));
        // third task is an event with correct from/to
        assertTrue(loaded.getTask(2).toSerializedLine().contains("2025-09-05"));
        assertTrue(loaded.getTask(2).toSerializedLine().contains("2025-09-10"));
    }
}
