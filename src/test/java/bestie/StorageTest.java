package bestie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    void load_validAndInvalidLines_parsesOnlyWellFormedTasks() throws Exception {
        Path dataFile = tempDir.resolve("tasks.txt");
        Files.write(dataFile, List.of(
                "T | 1 | read book",
                "D | 0 | submit report | 2023-11-01 1800",
                "E | 1 | project meeting | 2023-11-05 0900 | 2023-11-05 1100",
                "T | 0 | plan trip | travel,fun",
                "not a valid task line"
        ), StandardCharsets.UTF_8);

        Storage storage = new Storage(dataFile);

        List<Task> tasks = storage.load();

        assertEquals(4, tasks.size(), "Only valid task lines should be loaded");

        Task todo = tasks.get(0);
        assertTrue(todo instanceof Todo);
        assertEquals("X", todo.getStatusIcon());
        assertEquals("[T][X] read book", todo.toString());

        Task deadline = tasks.get(1);
        assertTrue(deadline instanceof Deadline);
        assertEquals(" ", deadline.getStatusIcon());
        assertEquals("D | 0 | submit report | 2023-11-01 1800", deadline.toDataString());

        Task event = tasks.get(2);
        assertTrue(event instanceof Event);
        assertEquals("X", event.getStatusIcon());
        assertEquals("E | 1 | project meeting | 2023-11-05 0900 | 2023-11-05 1100", event.toDataString());

        Task tagged = tasks.get(3);
        assertTrue(tagged instanceof Todo);
        assertEquals(List.of("travel", "fun"), tagged.getTags());
        assertEquals("[T][ ] plan trip #travel #fun", tagged.toString());
        assertEquals("T | 0 | plan trip | travel,fun", tagged.toDataString());
    }

    @Test
    void save_taskWithTags_persistsSerializedTags() throws Exception {
        Path dataFile = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(dataFile);

        Todo todo = new Todo("plan trip");
        todo.addTags(List.of("travel", "#Fun"));

        storage.save(List.of(todo));

        List<String> lines = Files.readAllLines(dataFile, StandardCharsets.UTF_8);
        assertEquals(List.of("T | 0 | plan trip | travel,fun"), lines);

        List<Task> loaded = storage.load();
        assertEquals(1, loaded.size());
        assertEquals(List.of("travel", "fun"), loaded.get(0).getTags());
    }
}