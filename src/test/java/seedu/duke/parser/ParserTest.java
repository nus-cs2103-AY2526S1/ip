package seedu.duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.FakeStorage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.FakeUi;

/**
 * Comprehensive tests for {@link Parser}.
 * <p>
 * Where appropriate, the returned {@link Command} is executed against a {@link TaskList} using
 * {@link FakeUi} and {@link FakeStorage} to verify end-to-end behavior.
 * </p>
 */
public class ParserTest {

    // ----------------------- helpers -----------------------

    /**
     * Executes a command against the provided collaborators and returns the last UI message.
     *
     * @param cmd    The command to execute.
     * @param tasks  The task list used in execution.
     * @param ui     The UI stub that captures output.
     * @param store  The storage stub used by commands.
     * @return The last string shown by the UI.
     * @throws Exception If command execution throws.
     */
    private static String execAndGetUi(Command cmd, TaskList tasks, FakeUi ui, FakeStorage store)
            throws Exception {
        cmd.execute(tasks, ui, store);
        return ui.getLastShown();
    }

    /**
     * Creates a new, empty {@link TaskList}.
     *
     * @return A fresh task list.
     */
    private static TaskList newTasks() {
        return new TaskList();
    }

    // ----------------------- general errors -----------------------

    /**
     * Parses {@code null} and expects the "Please enter a command." error.
     */
    @Test
    public void parse_nullInput_throwsEmptyMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse(null));
        assertEquals("Please enter a command.", ex.getMessage());
    }

    /**
     * Parses a blank string and expects the "Please enter a command." error.
     */
    @Test
    public void parse_blankInput_throwsEmptyMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse("   "));
        assertEquals("Please enter a command.", ex.getMessage());
    }

    /**
     * Parses an unknown keyword and expects the "Unknown command." error.
     */
    @Test
    public void parse_unknownCommand_throwsUnknownMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse("abracadabra"));
        assertEquals("Unknown command.", ex.getMessage());
    }

    // ----------------------- bye / list -----------------------

    /**
     * Parses "bye" and verifies an {@link ExitCommand} is returned and {@code isExit()} is true.
     */
    @Test
    public void parse_bye_returnsExitCommandTrue() throws Exception {
        Command cmd = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, cmd);
        assertTrue(cmd.isExit(), "Exit command should signal exit.");
    }

    /**
     * Parses "list", executes it, and verifies the UI shows some output.
     */
    @Test
    public void parse_list_executesAndShowsList() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();

        Command cmd = Parser.parse("list");
        String out = execAndGetUi(cmd, tasks, ui, storage);

        assertTrue(out != null && !out.isEmpty(), "List command should display something.");
    }

    // ----------------------- todo -----------------------

    /**
     * Parses "todo" without a description and expects the exact empty description error.
     */
    @Test
    public void parse_todoNoDescription_throwsExactMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse("todo"));
        assertEquals("Todo description cannot be empty!", ex.getMessage());
    }

    /**
     * Parses "todo" with whitespace only and expects the exact empty description error.
     */
    @Test
    public void parse_todoWhitespaceOnly_throwsExactMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse("todo    "));
        assertEquals("Todo description cannot be empty!", ex.getMessage());
    }

    /**
     * Parses a valid "todo" command, executes it, and verifies the task is added and UI confirms.
     */
    @Test
    public void parse_todoValid_addsTaskAndShowsAddedMessage() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();

        Command cmd = Parser.parse("todo read book");
        String out = execAndGetUi(cmd, tasks, ui, storage);

        assertEquals(1, tasks.size(), "Todo should be added.");
        Task t = tasks.get(0);
        assertTrue(t.toString().contains("read book"), "Task toString should include the description.");
        assertTrue(out.toLowerCase().contains("added"), "UI should confirm addition.");
    }

    // ----------------------- deadline -----------------------

    /**
     * Parses "deadline" without "/by" and expects the exact format error message.
     */
    @Test
    public void parse_deadlineMissingBy_throwsFormatMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse("deadline submit report"));
        assertEquals("Invalid deadline format! Use: deadline <task> /by <time>.", ex.getMessage());
    }

    /**
     * Parses "deadline" with empty name or empty date and expects the format error.
     */
    @Test
    public void parse_deadlineEmptyParts_throwsFormatMessage() {
        DukeException ex1 = assertThrows(DukeException.class, () -> Parser.parse("deadline   /by 2025-01-01"));
        assertEquals("Invalid deadline format! Use: deadline <task> /by <time>.", ex1.getMessage());

        DukeException ex2 = assertThrows(DukeException.class, () -> Parser.parse("deadline name /by   "));
        assertEquals("Invalid deadline format! Use: deadline <task> /by <time>.", ex2.getMessage());
    }

    /**
     * Parses a valid "deadline" command, executes it, and verifies addition and UI confirmation.
     */
    @Test
    public void parse_deadlineValid_addsDeadlineAndShowsAddedMessage() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();

        Command cmd = Parser.parse("deadline submit report /by 2025-08-30 2359");
        String out = execAndGetUi(cmd, tasks, ui, storage);

        assertEquals(1, tasks.size(), "Deadline should be added.");
        String s = tasks.get(0).toString();
        assertTrue(s.startsWith("[D]"), "Deadline string should start with [D].");
        assertTrue(s.toLowerCase().contains("submit report"), "Description should be present.");
        assertTrue(out.toLowerCase().contains("added"), "UI should confirm addition.");
    }

    // ----------------------- event -----------------------

    /**
     * Parses "event" missing required delimiters and expects the exact format error.
     */
    @Test
    public void parse_eventMissingDelimiters_throwsFormatMessage() {
        DukeException ex1 = assertThrows(DukeException.class, () -> Parser.parse("event party"));
        assertEquals("Invalid event format! Use: event <task> /from <start> /to <end>.", ex1.getMessage());

        DukeException ex2 = assertThrows(DukeException.class, () -> Parser.parse("event party /from 2025-01-01"));
        assertEquals("Invalid event format! Use: event <task> /from <start> /to <end>.", ex2.getMessage());
    }

    /**
     * Parses "event" with empty name/from/to parts and expects the exact format error.
     */
    @Test
    public void parse_eventEmptyParts_throwsFormatMessage() {
        DukeException ex1 = assertThrows(DukeException.class, () -> Parser.parse("event   /from a /to b"));
        assertEquals("Invalid event format! Use: event <task> /from <start> /to <end>.", ex1.getMessage());

        DukeException ex2 = assertThrows(DukeException.class, () -> Parser.parse("event name /from   /to b"));
        assertEquals("Invalid event format! Use: event <task> /from <start> /to <end>.", ex2.getMessage());

        DukeException ex3 = assertThrows(DukeException.class, () -> Parser.parse("event name /from a /to   "));
        assertEquals("Invalid event format! Use: event <task> /from <start> /to <end>.", ex3.getMessage());
    }

    /**
     * Parses a valid "event" command, executes it, and verifies addition and UI confirmation.
     */
    @Test
    public void parse_eventValid_addsEventAndShowsAddedMessage() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();

        Command cmd = Parser.parse("event team sync /from 2025-08-29 1400 /to 2025-08-29 1600");
        String out = execAndGetUi(cmd, tasks, ui, storage);

        assertEquals(1, tasks.size(), "Event should be added.");
        String s = tasks.get(0).toString();
        assertTrue(s.startsWith("[E]"), "Event string should start with [E].");
        assertTrue(s.toLowerCase().contains("team sync"), "Description should be present.");
        assertTrue(out.toLowerCase().contains("added"), "UI should confirm addition.");
    }

    // ----------------------- mark / unmark / delete -----------------------

    /**
     * Parses "mark" with a non-numeric index and expects the specific error message.
     */
    @Test
    public void parse_markInvalidIndex_throwsSpecificMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse("mark abc"));
        assertEquals("Please provide a valid number after 'mark'.", ex.getMessage());
    }

    /**
     * Parses "unmark" with a non-numeric index and expects the specific error message.
     */
    @Test
    public void parse_unmarkInvalidIndex_throwsSpecificMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse("unmark X"));
        assertEquals("Please provide a valid number after 'unmark'.", ex.getMessage());
    }

    /**
     * Parses "delete" with a non-numeric index and expects the specific error message.
     */
    @Test
    public void parse_deleteInvalidIndex_throwsSpecificMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse("delete -"));
        assertEquals("Please provide a valid number after 'delete'.", ex.getMessage());
    }

    /**
     * Parses "mark 1", executes it, and verifies the first task becomes done.
     */
    @Test
    public void parse_markValid_executesAndMarks() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();
        execAndGetUi(Parser.parse("todo read"), tasks, ui, storage);

        Command cmd = Parser.parse("mark 1");
        String out = execAndGetUi(cmd, tasks, ui, storage);

        assertTrue(tasks.get(0).isDone(), "Task should be marked done.");
        assertTrue(out.toLowerCase().contains("marked"), "UI should mention marking.");
    }

    /**
     * Parses "unmark 1", executes it, and verifies the first task becomes not done.
     */
    @Test
    public void parse_unmarkValid_executesAndUnmarks() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();
        execAndGetUi(Parser.parse("todo read"), tasks, ui, storage);
        execAndGetUi(Parser.parse("mark 1"), tasks, ui, storage);

        Command cmd = Parser.parse("unmark 1");
        String out = execAndGetUi(cmd, tasks, ui, storage);

        assertFalse(tasks.get(0).isDone(), "Task should be unmarked.");
        assertTrue(out.toLowerCase().contains("not done"), "UI should mention unmarking.");
    }

    /**
     * Parses "delete 1", executes it, and verifies a task is removed and UI indicates deletion.
     */
    @Test
    public void parse_deleteValid_executesAndDeletes() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();
        execAndGetUi(Parser.parse("todo a"), tasks, ui, storage);
        execAndGetUi(Parser.parse("todo b"), tasks, ui, storage);

        Command cmd = Parser.parse("delete 1");
        String out = execAndGetUi(cmd, tasks, ui, storage);

        assertEquals(1, tasks.size(), "One task should remain after deletion.");
        String low = out.toLowerCase();
        assertTrue(low.contains("remove") || low.contains("delete"), "UI should indicate deletion.");
    }

    // ----------------------- find -----------------------

    /**
     * Parses "find" with an empty keyword and expects the exact error message.
     */
    @Test
    public void parse_findEmpty_throwsExactMessage() {
        DukeException ex = assertThrows(DukeException.class, () -> Parser.parse("find   "));
        assertEquals("Keyword cannot be empty. Usage: find <keyword>.", ex.getMessage());
    }

    /**
     * Parses a valid "find" and verifies only matching tasks are shown.
     */
    @Test
    public void parse_findValid_executesAndShowsMatches() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();
        execAndGetUi(Parser.parse("todo read book"), tasks, ui, storage);
        execAndGetUi(Parser.parse("todo write code"), tasks, ui, storage);

        Command cmd = Parser.parse("find read");
        String out = execAndGetUi(cmd, tasks, ui, storage);

        assertTrue(out.toLowerCase().contains("matching tasks"), "Find should describe results.");
        assertTrue(out.contains("read book") && !out.contains("write code"),
                "Find should include only matches.");
    }

    // ----------------------- robustness -----------------------

    /**
     * Parses a command keyword with mixed case and verifies case-insensitive parsing.
     */
    @Test
    public void parse_isCaseInsensitiveForCommandKeywords() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();

        Command cmd = Parser.parse("ToDo mixed Caps");
        execAndGetUi(cmd, tasks, ui, storage);

        assertEquals(1, tasks.size(), "Command keyword should be case-insensitive.");
        assertTrue(tasks.get(0).toString().contains("mixed Caps"));
    }


    /**
     * Parses an event with extra spaces around delimiters and verifies success.
     */
    @Test
    public void parse_event_allowsExtraSpacesAroundDelimiters() throws Exception {
        TaskList tasks = newTasks();
        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();

        Command cmd = Parser.parse("event  conf  /from  2025-02-01 1000  /to   2025-02-01 1200");
        execAndGetUi(cmd, tasks, ui, storage);

        assertEquals(1, tasks.size(), "Event should be added.");
        assertTrue(tasks.get(0).toString().startsWith("[E]"));
    }
}
