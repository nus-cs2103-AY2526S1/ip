package benn.storage;

import benn.tasks.Deadline;
import benn.tasks.Event;
import benn.tasks.Todo;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskStorageTest {

    private static final Path FILE = Paths.get("temporary_datastore", "tasks.txt");

    @BeforeEach
    void cleanBefore() throws Exception {
        if (Files.exists(FILE)) Files.delete(FILE);
        if (Files.exists(FILE.getParent())) Files.delete(FILE.getParent());
    }

    @AfterEach
    void cleanAfter() throws Exception {
        if (Files.exists(FILE)) Files.delete(FILE);
        if (Files.exists(FILE.getParent())) Files.delete(FILE.getParent());
    }

    @Test
    void start_and_roundtrip_fixedPath() throws Exception {
        TaskStorage storage = TaskStorage.start();
        storage.add(new Todo("read book", true));
        storage.add(new Deadline("return book", "12/12/2069 14:50", false));
        storage.add(new Event("meeting", "12/08/2002 12:23", "12/08/2002 12:50", false));

        TaskStorage reopened = TaskStorage.start();
        assertEquals(3, reopened.getTaskCount());
        assertEquals("read book", reopened.getTaskLocatedAt(0).getDescription());
    }
}
