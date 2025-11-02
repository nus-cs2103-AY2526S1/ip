package jimbot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jimbot.exception.TaskLimitException;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;


/**
 * Unit tests for the Storage class, which is responsible for persisting
 * and loading TaskList objects to and from disk.
 * Note: AI assistance was used to aid in the creation of this test class.
 *
 * @author limjimin-nus
 */
class StorageTest {
    private File tempFile;
    private Storage storage;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = File.createTempFile("jimbot-test", ".dat");
        tempFile.delete();
        storage = new Storage(tempFile.getAbsolutePath());
    }

    @AfterEach
    void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testLoadWhenFileDoesNotExistReturnsEmptyTaskList() {
        TaskList tasks = storage.load();
        assertNotNull(tasks);
        assertTrue(tasks.getTaskList().isEmpty());
    }

    @Test
    void testUpdateAndLoadPersistsTasks() throws TaskLimitException {
        TaskList taskList = new TaskList();
        taskList.addToList(new Task("Read book"));
        taskList.addToList(new Task("Write report"));

        storage.update(taskList);

        TaskList loaded = storage.load();
        List<Task> loadedTasks = loaded.getTaskList();

        assertEquals(2, loadedTasks.size());
        assertEquals("Read book", loadedTasks.get(0).getDescription());
        assertEquals("Write report", loadedTasks.get(1).getDescription());
    }

    @Test
    void testLoadCorruptedFileReturnsEmptyTaskList() throws Exception {
        java.nio.file.Files.writeString(tempFile.toPath(), "not-a-valid-object");

        TaskList loaded = storage.load();
        assertNotNull(loaded);
        assertTrue(loaded.getTaskList().isEmpty());
    }
}

