package jackbot;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * End-to-end tests for Jackbot.run(), aligned with:
 *  - Deadline: "deadline <desc> /by yyyy-MM-dd HH:mm:ss"
 *  - Event:    "event <desc> /from yyyy-MM-dd HH:mm:ss /to yyyy-MM-dd HH:mm:ss"
 */
class JackbotTest {

    @TempDir
    Path tmp;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private InputStream oldIn;
    private PrintStream oldOut;

    @BeforeEach
    void setupStreams() throws UnsupportedEncodingException {
        oldIn = System.in;
        oldOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8.name()));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(oldIn);
        System.setOut(oldOut);
    }

    /** Lowercase + collapse whitespace + strip CR to make assertions robust. */
    private static String norm(String s) {
        return s.replace("\r", "")
                .toLowerCase()
                .replaceAll("\\s+", " ");
    }

    @Test
    void add_list_bye_flow() {
        String input = String.join(System.lineSeparator(),
                "todo read book",
                "list",
                "bye"
        ) + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        String filePath = tmp.resolve("tasks.txt").toString();
        new Jackbot(filePath).run();

        String s = norm(out.toString());
        assertTrue(s.contains("hello! i'm jackbot"), "welcome missing");
        assertTrue(s.contains("i've added this task"), "add confirmation missing");
        assertTrue(s.contains("[t][ ] read book"), "todo not shown in list");
        assertTrue(s.contains("your previous entries:"), "list heading missing");
        assertTrue(s.contains("bye. hope to see you again soon!"), "bye missing");
    }

    @Test
    void mark_unmark_delete_with_errors_and_success() {
        String input = String.join(System.lineSeparator(),
                "todo alpha",      // 1
                "todo beta",       // 2
                "mark 1",          // ok
                "mark 1",          // error: already marked
                "unmark 2",        // error: already unmarked
                "unmark 1",        // ok
                "delete 3",        // error: out of range
                "delete 2",        // ok -> remove beta
                "list",
                "bye"
        ) + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        String filePath = tmp.resolve("tasks2.txt").toString();
        new Jackbot(filePath).run();

        String s = norm(out.toString());

        // happy paths present
        assertTrue(s.contains("marked this task as done"));
        assertTrue(s.contains("marked this task as not done"));
        assertTrue(s.contains("removed this task"));

        // error paths present
        assertTrue(s.contains("already marked"));
        assertTrue(s.contains("already unmarked"));
        assertTrue(s.contains("task not found"));

        // order sanity: mark -> unmark -> delete -> list
        int iMark = s.indexOf("marked this task as done");
        int iUnmark = s.indexOf("marked this task as not done");
        int iDelete = s.indexOf("removed this task");
        int iList = s.lastIndexOf("your previous entries:");
        assertTrue(iMark >= 0 && iUnmark >= 0 && iDelete >= 0 && iList >= 0, "key markers missing");
        assertTrue(iMark < iUnmark && iUnmark < iDelete && iDelete < iList, "messages in wrong order");

        // only alpha remains on the final list
        String finalList = s.substring(iList);
        assertTrue(finalList.contains("1. [t][ ] alpha"), "final list should contain only alpha");
        assertFalse(finalList.contains("beta"), "beta should be removed");
    }

    @Test
    void deadline_and_event_valid_formats_are_accepted_and_rendered() {
        // Your Deadline/Event expect strict formats with spaces and full datetime
        String input = String.join(System.lineSeparator(),
                "deadline submit report /by 2025-12-31 23:59:59",
                "event conference /from 2025-12-30 09:00:00 /to 2025-12-30 17:00:00",
                "list",
                "bye"
        ) + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        String filePath = tmp.resolve("tasks3.txt").toString();
        new Jackbot(filePath).run();

        String s = norm(out.toString());

        // Both tasks added
        assertTrue(s.contains("i've added this task"), "add confirmations missing");

        // Deadline and Event should print with their datetime values (toString or formatted)
        // We accept either ISO or formatter output by searching for date fragments.
        assertTrue(s.contains("[d][ ] submit report") && s.contains("2025-12-31t23:59:59"), "deadline not shown");
        assertTrue(s.contains("[e][ ] conference") && s.contains("2025-12-30t09:00") && s.contains("2025-12-30t17:00"),
                "event time bounds not shown");
    }

    @Test
    void malformed_deadline_and_event_trigger_errors_but_app_continues() {
        // Missing '/by', missing '/from'/'/to', wrong indices, unknown command
        String input = String.join(System.lineSeparator(),
                "deadline oops-no-by",
                "event nope-no-from-to",
                "delete 99",
                "mark notanint",
                "abracadabra",
                "list",
                "bye"
        ) + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        String filePath = tmp.resolve("tasks4.txt").toString();
        new Jackbot(filePath).run();

        String s = norm(out.toString());

        // Expect error messages (exact phrasing may differ; look for keywords)
        assertTrue(s.contains("error"), "should show errors for malformed commands");
        assertTrue(s.contains("command doesn't exist") || s.contains("unknown"), "unknown command not flagged");
        assertTrue(s.contains("failed to parse task index number") || s.contains("parse task index"), "bad index not flagged");
        assertTrue(s.contains("task not found") || s.contains("not found"), "out-of-range delete not flagged");

        // App should still list and bye at the end
        assertTrue(s.contains("your previous entries:"), "should still print list");
        assertTrue(s.contains("bye. hope to see you again soon!"), "bye missing");
    }
}
