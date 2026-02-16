package bestie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ParserTest {
    @TempDir
    Path tempDir;

    private Storage storage;
    private Parser parser;
    private Ui ui;

    @BeforeEach
    void setup() {
        Path dataFile = tempDir.resolve("tasks.txt");
        storage = new Storage(dataFile);
        parser = new Parser();
        ui = new Ui();
    }

    @Test
    void parse_todoCommand_addsTaskAndPersistsToDisk() throws Exception {
        TaskList tasks = new TaskList();

        boolean shouldExit = parser.parse("todo finish writing tests", tasks, ui, storage);

        assertFalse(shouldExit, "todo command should not exit the app");
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertTrue(task instanceof Todo);
        assertEquals("finish writing tests", task.getDescription());
        assertEquals(" ", task.getStatusIcon());

        List<String> lines = Files.readAllLines(tempDir.resolve("tasks.txt"), StandardCharsets.UTF_8);
        assertEquals(List.of("T | 0 | finish writing tests"), lines);
    }

    @Test
    void parse_todoMissingDescription_throwsBestieException() throws Exception {
        TaskList tasks = new TaskList();

        BestieException ex = assertThrows(BestieException.class,
                () -> parser.parse("todo   ", tasks, ui, storage));
        assertTrue(ex.getMessage().contains("cannot be empty"));
        assertEquals(0, tasks.size(), "No task should be added when parsing fails");
        assertTrue(Files.notExists(tempDir.resolve("tasks.txt")));
    }

    @Test
    void parse_findCommand_returnsMatchingTasksWithoutSaving() throws Exception {
        TaskList tasks = new TaskList();
        Task readBook = new Todo("read book");
        Task returnBook = new Deadline("return book", "2023-11-01 1800");
        Task groceries = new Event("buy groceries", "2023-11-02 1000", "2023-11-02 1200");
        tasks.add(readBook);
        tasks.add(returnBook);
        tasks.add(groceries);

        RecordingUi recordingUi = new RecordingUi();

        boolean shouldExit = parser.parse("find book", tasks, recordingUi, storage);

        assertFalse(shouldExit, "find should not terminate the app");
        assertEquals(List.of(readBook, returnBook), recordingUi.getMatches());
        assertTrue(Files.notExists(tempDir.resolve("tasks.txt")),
                "find should not persist tasks when nothing changes");
    }

    @Test
    void parse_tagCommand_addsTagsAndPersists() throws Exception {
        TaskList tasks = new TaskList();
        Task todo = new Todo("read book");
        tasks.add(todo);

        boolean shouldExit = parser.parse("tag 1 fun #Chill", tasks, ui, storage);

        assertFalse(shouldExit, "tag command should not exit the app");
        assertEquals(List.of("fun", "chill"), todo.getTags());

        List<String> lines = Files.readAllLines(tempDir.resolve("tasks.txt"), StandardCharsets.UTF_8);
        assertEquals(List.of("T | 0 | read book | fun,chill"), lines);
    }

    private static class RecordingUi extends Ui {
        private List<Task> matches;

        @Override
        public void showFindResults(List<Task> matches) {
            this.matches = matches;
        }

        List<Task> getMatches() {
            return matches;
        }
    }
}