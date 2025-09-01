package aurora.storage;

import aurora.task.Deadline;
import aurora.task.TaskList;
import aurora.task.Todo;
import aurora.util.DateUtil;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private File tempFile;
    private Storage storage;

    @BeforeEach
    void before() throws IOException {
        tempFile = File.createTempFile("aurora-test", ".txt");
        tempFile.delete();
        storage = new Storage(tempFile.getAbsolutePath());
    }

    @AfterEach
    void after() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void saveAndLoad_roundTripTasks() {
        TaskList list = new TaskList();
        list.add(new Todo("basketball", true));
        list.add(new Deadline("math quiz", false, DateUtil.readDate("2025-09-29")));

        storage.save(list);
        TaskList tasks = storage.load();

        assertEquals(2, tasks.size());
        assertEquals("basketball", tasks.get(0).getDescription());
        assertEquals("math quiz", tasks.get(1).getDescription());
        assertTrue(tasks.get(0).isDone());
        assertFalse(tasks.get(1).isDone());
    }

    @Test
    void save_emptyList_createsEmptyFile() {
        TaskList list = new TaskList();
        storage.save(list);

        TaskList tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    void save_createsParentDirectoryIfMissing() {
        File dir = new File("test-dir");
        File nestedFile = new File(dir, "test.txt");
        Storage s = new Storage(nestedFile.getPath());

        TaskList list = new TaskList();
        list.add(new Todo("basketball", false));
        s.save(list);

        assertTrue(nestedFile.exists());
        nestedFile.delete();
        dir.delete();
    }
}
