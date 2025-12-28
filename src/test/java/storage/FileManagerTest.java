package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Task;
import models.ToDo;

class FileManagerTest {

    private static final String DATA_FILE = "./data/yapper_data.json";

    @BeforeEach
    void cleanUp() throws Exception {
        Files.deleteIfExists(Paths.get(DATA_FILE));
    }

    @Test
    void saveTasks_noTasks_emptyList_createsFile() {
        List<Task> tasks = new ArrayList<>();
        FileManager.saveTasks(tasks);

        File file = new File(DATA_FILE);
        assertTrue(file.exists(), "Data file should be created");
        assertTrue(file.length() > 0, "Data file should not be empty");
    }

    @Test
    void saveTasks_and_loadTasks_roundTrip() {
        List<Task> tasks = new ArrayList<>();
        Task t = new ToDo("Test Task");
        tasks.add(t);

        FileManager.saveTasks(tasks);
        ArrayList<Task> loaded = FileManager.loadTasks();

        assertEquals(1, loaded.size());
        assertEquals("Test Task", loaded.get(0).getName());
    }

    @Test
    void loadTasks_noFile_returnsEmptyList() throws Exception {
        Files.deleteIfExists(Paths.get(DATA_FILE));
        ArrayList<Task> loaded = FileManager.loadTasks();
        assertTrue(loaded.isEmpty());
    }

    @Test
    void loadTasks_corruptedFile_returnsEmptyList() throws Exception {
        Files.createDirectories(Paths.get("./data"));
        Files.write(Paths.get(DATA_FILE), "not a json".getBytes());
        ArrayList<Task> loaded = FileManager.loadTasks();
        assertTrue(loaded.isEmpty());
    }
}
