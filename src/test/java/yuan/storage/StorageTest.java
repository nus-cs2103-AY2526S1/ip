package yuan.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yuan.task.Todo;
import yuan.tasklist.TaskList;

public class StorageTest {
    private Path tempDir;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        // create a temporary folder and file for each test
        tempDir = Files.createTempDirectory("yuanTest");
        Path tempFile = tempDir.resolve("tasks.txt");
        storage = new Storage(tempFile.toString());
    }

    @AfterEach
    public void tearDown() throws IOException {
        // delete temp folder after each test
        Files.walk(tempDir)
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void load_noFile_createsEmptyTaskList() {
        TaskList tasks = storage.load();
        assertTrue(tasks.getTasks().isEmpty(), "New storage should load empty task list");
    }

    @Test
    public void saveAndLoad_taskList_storesTasks() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("go sch", false));

        storage.save(tasks);

        TaskList loadList = storage.load();
        assertEquals("go sch", loadList.get(0).getDescription());
    }
}
