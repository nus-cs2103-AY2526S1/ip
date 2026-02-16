package storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.Parser;
import tasks.TaskList;
import tasks.ToDoTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class storageTest {

    private static final String TEST_STORAGE_PATH = "./tasks/Nicholas.txt";
    private Storage storage;

    @BeforeEach
    public void setup() {
        this.storage = new Storage();

        File file = new File(TEST_STORAGE_PATH);

        if (file.exists()) {
            file.delete();
        }
        File parentDir = file.getParentFile();
        if (parentDir.exists()) {
            for (File f : parentDir.listFiles()) {
                f.delete();
            }
            parentDir.delete();
        }
    }

    @Test
    public void testFileSetup_createsFileAndDirectory() {
        storage.fileSetup();

        File file = new File(TEST_STORAGE_PATH);

        assertEquals(file.exists(), true);
        assertEquals(file.getParentFile().exists(), true);
    }

    @Test
    public void testSaveToFile_writesTasks() throws IOException {
        TaskList tasks = new TaskList();
        tasks.addItem(new ToDoTask("return book"));

        storage.fileSetup();  // Ensure file exists
        storage.saveToFile(tasks);

        String content = Files.readString(Paths.get(TEST_STORAGE_PATH));

        assertEquals(content, "[T][ ]return book\n");
    }

    @Test
    public void testSaveToFile_overwritesFile() throws IOException {
        TaskList tasks = new TaskList();
        tasks.addItem(new ToDoTask("read book"));

        storage.fileSetup();
        storage.saveToFile(tasks);

        // overwrite with a different task
        TaskList newTasks = new TaskList();
        newTasks.addItem(new ToDoTask("return book"));
        storage.saveToFile(newTasks);

        String content = Files.readString(Paths.get(TEST_STORAGE_PATH));

        assertNotEquals(content, "[T][ ]read book\n");
        assertEquals(content, "[T][ ]return book\n");
    }

}
