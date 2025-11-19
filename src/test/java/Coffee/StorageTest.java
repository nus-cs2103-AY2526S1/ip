package Coffee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @TempDir
    Path tmp;

    @Test
    void load_whenFileDoesNotExist_returnsEmptyList() {
        Path nonExistent = tmp.resolve("data").resolve("tasks.txt");
        Storage storage = new Storage(nonExistent.toString());

        ArrayList<Task> tasks = storage.load();

        assertNotNull(tasks);
        assertTrue(tasks.isEmpty(), "Expected empty list when file does not exist");
    }

    @Test
    void save_writesTasks_andCreatesParentDirectories() throws IOException {
        Path nested = tmp.resolve("nested").resolve("deeper").resolve("tasks.txt");
        Storage storage = new Storage(nested.toString());

        // Use ToDo only so we don't depend on other concrete Task types' constructors
        List<Task> toSave = Arrays.asList(
                new ToDo("read book"),
                new ToDo("buy milk", true)
        );

        storage.save(toSave);

        assertTrue(Files.exists(nested.getParent()), "Parent directories should be created");
        assertTrue(Files.exists(nested), "File should be created by save()");

        List<String> lines = Files.readAllLines(nested, UTF_8);
        assertEquals(2, lines.size());
        assertEquals("T |   | read book", lines.get(0));
        assertEquals("T | X | buy milk", lines.get(1));
    }

    @Test
    void load_parsesCanonicalLines_andKeepsOrder() throws IOException {
        Path file = tmp.resolve("tasks.txt");
        List<String> canonical = Arrays.asList(
                "T |   | Buy coffee",
                "D | X | Submit report | 2024-06-30 2359",
                "E |   | Coffee chat | 2024-06-10 1800 2024-06-10 1900"
        );
        Files.write(file, canonical, UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        Storage storage = new Storage(file.toString());
        ArrayList<Task> loaded = storage.load();

        assertNotNull(loaded);
        assertEquals(3, loaded.size(), "Should load exactly 3 tasks");

        // Verify the round-trippable serialization using toFileString()
        assertEquals(canonical.get(0), loaded.get(0).toFileString());
        assertEquals(canonical.get(1), loaded.get(1).toFileString());
        assertEquals(canonical.get(2), loaded.get(2).toFileString());
    }
}
