package nailongbot.utils;

import nailongbot.task.Task;
import nailongbot.task.Todo;
import nailongbot.task.Deadline;
import nailongbot.task.Event;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Tester for Storage Class
class StorageTest {

    private Storage storage;
    private File tempFile;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = File.createTempFile("jkbot", ".txt");
        tempFile.deleteOnExit();

        // Override file path in Storage using reflection if needed, else copy Storage code to allow filePath injection
        storage = new Storage() {
            private final String FILE_PATH_OVERRIDE = tempFile.getAbsolutePath();

            @Override
            public ArrayList<Task> loadTasks() {
                try {
                    ArrayList<Task> memory = new ArrayList<>();
                    if (!tempFile.exists()) return memory;
                    for (String line : Files.readAllLines(tempFile.toPath())) {
                        Task t = storage.parseTask(line);
                        if (t != null) memory.add(t);
                    }
                    return memory;
                } catch (Exception e) {
                    return new ArrayList<>();
                }
            }

            @Override
            public void saveTasks(ArrayList<Task> memory) {
                try {
                    FileWriter writer = new FileWriter(tempFile);
                    for (Task task : memory) {
                        writer.write(task.toFileFormat() + "\n");
                    }
                    writer.close();
                } catch (Exception ignored) {}
            }
        };
    }

    @Test
    void saveAndLoadSingleTodo() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test todo"));
        storage.saveTasks(tasks);

        ArrayList<Task> loaded = storage.loadTasks();
        assertEquals(1, loaded.size());
        assertEquals("Test todo", loaded.get(0).getDescription());
    }

    @Test
    void saveAndLoadMultipleTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Todo1"));
        tasks.add(new Deadline("Deadline1", "28/8/2025 2359"));
        tasks.add(new Event("Event1", "28/8/2025 1000", "28/8/2025 1200"));
        storage.saveTasks(tasks);

        ArrayList<Task> loaded = storage.loadTasks();
        assertEquals(3, loaded.size());
        assertEquals("Todo1", loaded.get(0).getDescription());
        assertEquals("Deadline1", loaded.get(1).getDescription());
        assertEquals("Event1", loaded.get(2).getDescription());
    }

    @Test
    void loadWithCorruptedLine() throws Exception {
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("T | 0 | Valid Todo\n");
            writer.write("Corrupted Line Without Proper Format\n");
        }

        ArrayList<Task> loaded = storage.loadTasks();
        assertEquals(1, loaded.size());
        assertEquals("Valid Todo", loaded.get(0).getDescription());
    }

    @Test
    void loadEmptyFileReturnsEmptyList() throws Exception {
        ArrayList<Task> loaded = storage.loadTasks();
        assertTrue(loaded.isEmpty());
    }
}
