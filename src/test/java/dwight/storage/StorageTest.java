package dwight.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.task.Task;
import dwight.task.TaskList;
import dwight.task.ToDo;
import dwight.task.UnknownTaskTypeException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Unit tests for {@link Storage}.
 *
 * <p>Covers parseFromLine(), load(), and save() behaviors including malformed lines, unknown types,
 * file creation, and serialization integrity.
 */
class StorageTest {

    @TempDir Path tempDir;

    /** parseFromLine() parses a valid unmarked ToDo line. */
    @Test
    void parseFromLineParsesUnmarkedTodo() throws Exception {
        Storage storage = new Storage(this.tempDir.resolve("dummy.txt").toString());
        Task task = storage.parseFromLine("T | 0 | Buy milk");
        assertEquals("[T][ ] Buy milk", task.toString(), "Should parse an unmarked ToDo");
        assertFalse(task.isMarked(), "Marked flag 0 should result in unmarked task");
    }

    /** parseFromLine() parses a valid marked ToDo line. */
    @Test
    void parseFromLineParsesMarkedTodo() throws Exception {
        Storage storage = new Storage(this.tempDir.resolve("dummy.txt").toString());
        Task task = storage.parseFromLine("T | 1 | Buy milk");
        assertEquals("[T][X] Buy milk", task.toString(), "Should parse a marked ToDo");
        assertTrue(task.isMarked(), "Marked flag 1 should result in marked task");
    }

    /** parseFromLine() trims spaces around fields. */
    @Test
    void parseFromLineTrimsFields() throws Exception {
        Storage storage = new Storage(this.tempDir.resolve("dummy.txt").toString());
        Task task = storage.parseFromLine("  T   |   1   |   Buy milk   ");
        assertEquals("[T][X] Buy milk", task.toString(), "Fields should be trimmed");
    }

    /** Malformed line (< 3 parts) throws CorruptSaveException. */
    @Test
    void parseFromLineMalformedThrowsCorruptSaveException() {
        Storage storage = new Storage(this.tempDir.resolve("dummy.txt").toString());
        assertThrows(
                CorruptSaveException.class,
                () -> storage.parseFromLine("T | 1"),
                "Fewer than 3 parts should be considered corrupt");
    }

    /** Unknown type throws UnknownTaskTypeException. */
    @Test
    void parseFromLineUnknownTypeThrows() {
        Storage storage = new Storage(this.tempDir.resolve("dummy.txt").toString());
        assertThrows(
                UnknownTaskTypeException.class,
                () -> storage.parseFromLine("X | 0 | Mystery"),
                "Unknown task type should throw UnknownTaskTypeException");
    }

    /** load() returns empty TaskList when file does not exist. */
    @Test
    void loadReturnsEmptyWhenFileMissing() throws Exception {
        Path nonExistent = this.tempDir.resolve("does-not-exist/tasks.txt");
        Storage storage = new Storage(nonExistent.toString());
        TaskList list = storage.load();
        assertEquals(0, list.size(), "Missing file should load as empty list");
        assertFalse(Files.exists(nonExistent), "load() should not create the file");
    }

    /** load() reads valid lines and skips invalid ones. */
    @Test
    void loadReadsValidAndSkipsInvalid() throws Exception {
        Path file = this.tempDir.resolve("tasks.txt");
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file.toFile()))) {
            w.write("T | 1 | Buy milk\n");
            w.write("T | 0\n");
            w.write("X | 0 | Unknown type\n");
            w.write("T | 0 | Read book\n");
        }

        Storage storage = new Storage(file.toString());
        TaskList list = storage.load();

        assertEquals(2, list.size(), "Should load only the two valid lines");
        String rendered = list.toString();
        assertTrue(rendered.contains("Buy milk"), "Loaded tasks should include 'Buy milk'");
        assertTrue(rendered.contains("Read book"), "Loaded tasks should include 'Read book'");
    }

    /** save() creates missing parent directories and writes serialized content. */
    @Test
    void saveCreatesParentsAndWritesContent() throws Exception {
        TaskList list = new TaskList();
        list.add(new ToDo("Task A"));
        list.add(new ToDo("Task B").mark());

        Path nestedDir = this.tempDir.resolve(Path.of("level1", "level2"));
        Path file = nestedDir.resolve("tasks.txt");

        Storage storage = new Storage(file.toString());

        storage.save(list);

        assertTrue(Files.exists(file), "save() should create the file");
        String content = Files.readString(file);
        assertEquals(list.serialize(), content, "File content should match serialize()");
    }

    /**
     * save() swallows IOExceptions during write (prints error), so no exception is propagated.
     *
     * <p>This is a behavioral check: we simulate an unwritable path by pointing to a directory as
     * the file.
     */
    @Test
    void saveDoesNotPropagateIOExceptionOnWrite() throws Exception {
        Path dirAsFile = this.tempDir.resolve("not-a-file");
        Files.createDirectories(dirAsFile);

        Storage storage = new Storage(dirAsFile.toString());
        TaskList list = new TaskList();
        list.add(new ToDo("Will fail to write"));

        storage.save(list);

        assertTrue(
                Files.isDirectory(dirAsFile), "Path remains a directory; write was not possible");
    }
}
