package seedu.mani.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import seedu.mani.task.Task;
import seedu.mani.task.Todo;

/**
 * Test class for Storage component for 2 of its non-trivial methods.
 */
public class StorageTest {
    private static final String TEST = "test.txt";

    /**
     * Sets up a test file before test execution
     *
     * @throws IOException
     */
    @BeforeEach
    public void start() throws IOException {
        Storage.writeToFile(TEST, "");
    }

    /**
     * Cleans up test file after test.
     */
    @AfterEach
    public void end() {
        File f = new File(TEST);
        if (f.exists()) {
            f.delete();
        }
    }

    /**
     * Tests appendToFile functionality by verifying content.
     *
     * @throws IOException
     */
    @Test
    public void appendToFileTest() throws IOException {
        Storage.appendToFile(TEST, "test first line\n");
        Storage.appendToFile(TEST, "test second line\n");

        String content = Files.readString(new File(TEST).toPath());
        assertTrue(content.contains("test first line"));
        assertTrue(content.contains("test second line"));
    }

    /**
     * Tests loadTasks functionality by verifying if content is correctly loaded.
     *
     * @throws IOException
     */
    @Test
    public void loadTaskTest() throws IOException {
        String data = "T | 0 | read book | 0\nT | 1 | return book | 0\n";
        Storage.writeToFile(TEST, data);

        ArrayList<Task> tasks = Storage.loadTasks(TEST);

        assertEquals(2, tasks.size());
        assertEquals("T | 0 | read book | 0", tasks.get(0).getDetails());
        assertEquals("T | 1 | return book | 0", tasks.get(1).getDetails());
        assertFalse(tasks.get(0).isDone());
        assertTrue(tasks.get(1).isDone());
    }

}
