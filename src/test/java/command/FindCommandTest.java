package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class FindCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tasks = new TaskList();
        ui = new Ui();
        tempFile = Files.createTempFile("tasks", ".txt");
        storage = new Storage(tempFile.toString());

        tasks.addTask(new Todo("read book"));      // index 1
        tasks.addTask(new Todo("book flight"));    // index 2
        tasks.addTask(new Todo("write report"));   // index 3
    }

    @Test
    public void execute_findKeyword_multipleMatchesListed() {
        FindCommand cmd = new FindCommand("book");
        String result = cmd.execute(tasks, ui, storage);

        String expectedHeader = "Looking for task including book...\n";
        assertTrue(result.startsWith(expectedHeader), "Should start with looking header");
        assertTrue(result.contains("  1. [T][ ] read book\n"), "Should include first matching task with correct numbering");
        assertTrue(result.contains("  2. [T][ ] book flight\n"), "Should include second matching task with correct numbering");
        // Should not include non-matching task
        assertTrue(!result.contains("write report"), "Should not include non-matching task");
    }

    @Test
    public void execute_findIsCaseInsensitive() {
        FindCommand cmd = new FindCommand("BOOK");
        String result = cmd.execute(tasks, ui, storage);

        assertTrue(result.contains("  1. [T][ ] read book\n"));
        assertTrue(result.contains("  2. [T][ ] book flight\n"));
    }

    @Test
    public void execute_noMatch_returnsNotFoundMessage() {
        FindCommand cmd = new FindCommand("gym");
        String result = cmd.execute(tasks, ui, storage);

        // Note: Ui.printFind intentionally has no space before 'not' in this message
        assertEquals("Task including gymnot found.", result);
    }

    @Test
    public void execute_emptyKeyword_listsAllTasks() {
        FindCommand cmd = new FindCommand("");
        String result = cmd.execute(tasks, ui, storage);

        String expected = new StringBuilder()
                .append("Looking for task including ...\n")
                .append("  1. [T][ ] read book\n")
                .append("  2. [T][ ] book flight\n")
                .append("  3. [T][ ] write report\n")
                .toString();
        assertEquals(expected, result);
    }

    @Test
    public void execute_singleMatch_middleTask() {
        FindCommand cmd = new FindCommand("write");
        String result = cmd.execute(tasks, ui, storage);

        String expected = new StringBuilder()
                .append("Looking for task including write...\n")
                .append("  3. [T][ ] write report\n")
                .toString();
        assertEquals(expected, result);
    }
}
