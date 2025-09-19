package jackbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import jackbot.CommandEngine.Response;
import jackbot.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Integration-style tests for {@link CommandEngine} covering
 * add/list/mark/unmark/delete/find/bye and file persistence via {@link Storage}.
 */
public class CommandEngineTest {

    @TempDir
    Path tmp;

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private CommandEngine engine;

    @BeforeEach
    void setUp() throws Exception {
        Path file = tmp.resolve("tasks.txt");
        storage = new Storage(file.toString());
        // Start with whatever the file has (usually empty)
        try {
            List<Task> loaded = storage.load();
            tasks = new TaskList(loaded);
        } catch (JackbotException e) {
            tasks = new TaskList();
        }
        parser = new Parser();
        engine = new CommandEngine(storage, tasks, parser);
    }

    // ---------- helpers ----------

    /** Process one line and return the response. */
    private Response run(String line) {
        return engine.process(line);
    }

    /** Convenience: assert that the last engine message contains a substring. */
    private void assertLastMessageContains(Response r, String needle) {
        List<String> msgs = r.messages;
        assertFalse(msgs.isEmpty(), "engine should return at least one message");
        String last = msgs.get(msgs.size() - 1);
        assertTrue(last.contains(needle), "Expected to contain: " + needle + "\nActual: " + last);
    }

    // ---------- tests ----------

    @Test
    void todo_addsTaskAndPersists() throws Exception {
        Response r1 = run("todo read book");
        assertLastMessageContains(r1, "[T][ ] read book");
        assertLastMessageContains(r1, "Now you have 1 tasks in the list.");

        // Verify file persisted
        File f = new File(storagePath());
        assertTrue(f.exists(), "storage file should exist");
        String content = Files.readString(Path.of(storagePath()));
        assertTrue(content.contains("T|0|read book"), "serialized todo should be present");
    }

    @Test
    void deadline_and_event_add_with_expected_format() {
        Response rd = run("deadline return book /by 2025-12-31 23:59:59");
        assertLastMessageContains(rd, "[D][ ] return book");
        assertTrue(rd.messages.get(rd.messages.size() - 1).contains("2025-12-31 23:59:59"), "by timestamp should be correct format");

        Response re = run("event team sync /from 2025-10-01 09:00:00 /to 2025-10-01 10:00:00");
        assertLastMessageContains(re, "[E][ ] team sync");
        String last = re.messages.get(re.messages.size() - 1);
        assertTrue(last.contains("2025-10-01 09:00"), "from timestamp should be correct format");
        assertTrue(last.contains("2025-10-01 10:00"), "to timestamp should be correct format");
    }

    @Test
    void list_shows_numbered_tasks() {
        run("todo a");
        run("todo b");
        Response r = run("list");
        String last = r.messages.get(r.messages.size() - 1);
        assertTrue(last.contains("1. [T][ ] a"));
        assertTrue(last.contains("2. [T][ ] b"));
    }

    @Test
    void mark_and_unmark_toggle_state() {
        run("todo read");
        Response m = run("mark 1");
        assertLastMessageContains(m, "I've marked this task as done:");
        assertTrue(m.messages.get(m.messages.size() - 1).contains("[T][X] read"));

        Response u = run("unmark 1");
        assertLastMessageContains(u, "I've marked this task as not done:");
        assertTrue(u.messages.get(u.messages.size() - 1).contains("[T][ ] read"));
    }

    @Test
    void delete_removes_and_reports_new_count() {
        run("todo a");
        run("todo b");
        Response d = run("delete 1");
        assertLastMessageContains(d, "Noted. I've removed this task:");
        assertTrue(d.messages.get(d.messages.size() - 1).contains("Now you have 1 tasks in the list."));
        // list to confirm remaining
        Response r = run("list");
        assertTrue(r.messages.get(r.messages.size() - 1).contains("1. [T][ ] b"));
    }

    @Test
    void find_filters_by_keyword_caseInsensitive() {
        run("todo read book");
        run("todo wash car");
        Response f1 = run("find book");
        String last1 = f1.messages.get(f1.messages.size() - 1);
        assertTrue(last1.contains("[T][ ] read book"));
        assertFalse(last1.contains("wash car"));

        Response f2 = run("find BOOK"); // case-insensitive
        String last2 = f2.messages.get(f2.messages.size() - 1);
        assertTrue(last2.contains("[T][ ] read book"));
    }

    @Test
    void bye_sets_exit_flag_and_does_not_crash() {
        Response r = run("bye");
        assertTrue(r.exit, "bye should request exit");
        assertLastMessageContains(r, "Bye. Hope to see you again soon!");
    }

    @Test
    void persistence_roundTrip_loads_existing_tasks() throws Exception {
        // Add a couple, which should save to file
        run("todo first");
        run("deadline d1 /by 2025-12-31 12:00:00");

        // New engine reading same file should "see" them
        TaskList reloaded;
        try {
            reloaded = new TaskList(new Storage(storagePath()).load());
        } catch (JackbotException e) {
            reloaded = new TaskList();
        }
        assertEquals(2, reloaded.size(), "reloaded task list should match saved count");
    }

    @Test
    void mark_outOfRangeIndex_reportsError() {
        run("todo a");
        Response r = run("mark 5");
        String last = r.messages.get(r.messages.size() - 1).toLowerCase();
        assertTrue(last.contains("error"), "Should complain about invalid index");
    }

    @Test
    void delete_zeroOrNegativeIndex_reportsError() {
        run("todo a");
        Response r1 = run("delete 0");
        String last1 = r1.messages.get(r1.messages.size() - 1).toLowerCase();
        assertTrue(last1.contains("error"), "Should complain about invalid index");

        Response r2 = run("delete -1");
        String last2 = r2.messages.get(r2.messages.size() - 1).toLowerCase();
        assertTrue(last2.contains("error"), "Should complain about invalid index");
    }

    // ---------- internals ----------

    private String storagePath() {
        return tmp.resolve("tasks.txt").toString();
    }
}
