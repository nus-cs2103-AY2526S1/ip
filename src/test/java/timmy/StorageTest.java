package timmy;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Storage}.
 */
public class StorageTest {

    private Path dataDir;
    private Path storageFile;

    @BeforeEach
    void setUp() throws IOException {
        dataDir = Path.of(System.getProperty("user.dir"), "data");
        storageFile = dataDir.resolve("storage.txt");

        // Clean up before tests
        if (Files.exists(dataDir)) {
            Files.walk(dataDir)
                    .map(Path::toFile)
                    .sorted((a, b) -> -a.compareTo(b)) // delete files before dirs
                    .forEach(file -> file.delete());
        }
        Files.createDirectory(dataDir);
    }

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(dataDir)) {
            Files.walk(dataDir)
                    .map(Path::toFile)
                    .sorted((a, b) -> -a.compareTo(b))
                    .forEach(file -> file.delete());
        }
    }

    @Test
    void saveAndLoadStorage_withToDoTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo todo = new ToDo("Write JUnit tests");
        tasks.add(todo);

        Storage.saveStorage(tasks);
        assertTrue(Files.exists(storageFile), "Storage file should be created");

        ArrayList<Task> loaded = Storage.loadStorage();
        assertEquals(1, loaded.size());
        assertEquals("[T][ ] Write JUnit tests", loaded.get(0).toCompleteString());
    }

    @Test
    void saveAndLoadStorage_withDeadlineAndEventTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        Deadline deadline = new Deadline("Submit report", "18/09/2025");
        Event event = new Event("Team meeting", "19/09/2025", "20/09/2025");
        deadline.markAsDone();
        tasks.add(deadline);
        tasks.add(event);

        Storage.saveStorage(tasks);

        ArrayList<Task> loaded = Storage.loadStorage();
        assertEquals(2, loaded.size());

        Task loadedDeadline = loaded.get(0);
        assertEquals("[D][X] Submit report (by: Sep 18 2025)", loadedDeadline.toCompleteString());

        Task loadedEvent = loaded.get(1);
        assertEquals("[E][ ] Team meeting (from: Sep 19 2025 to: Sep 20 2025)", loadedEvent.toCompleteString());
    }

    @Test
    void archiveStorage_createsNewArchiveFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Archive me"));

        String archivePath = Storage.archiveStorage(tasks);

        assertTrue(Files.exists(Path.of(archivePath)), "Archive file should be created");

        List<String> lines = Files.readAllLines(Path.of(archivePath));
        assertTrue(lines.get(0).contains("Archive me"));
    }
}

