import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import instruction.AddInstruction;
import instruction.DeleteInstruction;
import instruction.ExitInstruction;
import instruction.FindInstruction;
import instruction.Instruction;
import instruction.ListInstruction;
import instruction.MarkInstruction;
import instruction.OnDateInstruction;
import instruction.SortInstruction;
import parser.Parser;
import shrek.Shrek;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;
import util.ShrekException;

/**
 * Test class for Shrek application functionality.
 * Contains unit tests for parsing commands and integration tests for user interactions.
 */
public class ShrekTest {

    private static final String TEST_FILE_PATH = "./data/test_shrek.txt";
    private File testFile;

    @BeforeEach
    public void setUp() {
        testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
        testFile.getParentFile().mkdirs();
    }

    @AfterEach
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    /**
     * Normalizes line endings in a string by converting Windows line endings (\r\n) to Unix line endings (\n)
     * and trims whitespace.
     *
     * @param s the string to normalize
     * @return the normalized string with consistent line endings
     */
    private String normalize(String s) {
        return s.replace("\r\n", "\n").trim();
    }

    private TaskList createTestTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test task 1"));
        tasks.add(new Todo("test task 2"));
        return new TaskList(tasks);
    }

    // === PARSER TESTS ===

    @Test
    public void testParseTodoCommand() throws ShrekException {
        Instruction instr = Parser.parse("todo borrow book");
        assertInstanceOf(AddInstruction.class, instr);
    }

    @Test
    public void testParseTodoCommand_emptyDescription_throwsException() {
        assertThrows(ShrekException.class, () -> Parser.parse("todo"));
        assertThrows(ShrekException.class, () -> Parser.parse("todo   "));
    }

    @Test
    public void testParseTodoCommand_stringMatching() throws ShrekException {
        Instruction instr = Parser.parse("todo borrow book");
        assertInstanceOf(AddInstruction.class, instr);

        AddInstruction addInstr = (AddInstruction) instr;
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/test_dummy.txt");

        // execute the instruction on a fresh list
        addInstr.execute(list, ui, storage);

        assertEquals(1, list.size());
        assertEquals("[T][ ] borrow book", list.get(0).toString());
    }

    @Test
    public void testParseDeadlineCommand() throws ShrekException {
        Instruction instr = Parser.parse("deadline return book /by 2025-01-01 14:00");
        assertInstanceOf(AddInstruction.class, instr);
    }

    @Test
    public void testParseDeadlineCommand_missingBy_throwsException() {
        assertThrows(ShrekException.class, () -> Parser.parse("deadline return book"));
        assertThrows(ShrekException.class, () -> Parser.parse("deadline return book /by"));
    }

    @Test
    public void testParseDeadlineCommand_stringMatching() throws ShrekException {
        Instruction instr = Parser.parse("deadline return book /by 2025-01-01 14:00");
        assertInstanceOf(AddInstruction.class, instr);

        AddInstruction addInstr = (AddInstruction) instr;
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/test_dummy.txt");

        addInstr.execute(list, ui, storage);

        assertEquals(1, list.size());
        assertEquals("[D][ ] return book (by: Jan 1 2025, 2:00PM)", list.get(0).toString());
    }

    @Test
    public void testParseEventCommand() throws ShrekException {
        Instruction instr = Parser.parse("event meeting /from 2025-12-05 14:00 /to 2025-12-05 16:00");
        assertInstanceOf(AddInstruction.class, instr);
    }

    @Test
    public void testParseEventCommand_reverseOrder() throws ShrekException {
        Instruction instr = Parser.parse("event meeting /to 2025-12-05 16:00 /from 2025-12-05 14:00");
        assertInstanceOf(AddInstruction.class, instr);
    }

    @Test
    public void testParseEventCommand_missingTimes_throwsException() {
        assertThrows(ShrekException.class, () -> Parser.parse("event meeting"));
        assertThrows(ShrekException.class, () -> Parser.parse("event meeting /from 2025-12-05 14:00"));
    }

    @Test
    public void testParseMarkCommand() throws ShrekException {
        Instruction instr = Parser.parse("mark 1");
        assertInstanceOf(MarkInstruction.class, instr);
    }

    @Test
    public void testParseUnmarkCommand() throws ShrekException {
        Instruction instr = Parser.parse("unmark 1");
        assertInstanceOf(MarkInstruction.class, instr);
    }

    @Test
    public void testParseMarkCommand_invalidNumber_throwsException() {
        assertThrows(ShrekException.class, () -> Parser.parse("mark"));
        assertThrows(ShrekException.class, () -> Parser.parse("mark abc"));
    }

    @Test
    public void testParseDeleteCommand() throws ShrekException {
        Instruction instr = Parser.parse("delete 1");
        assertInstanceOf(DeleteInstruction.class, instr);
    }

    @Test
    public void testParseListCommand() throws ShrekException {
        Instruction instr = Parser.parse("list");
        assertInstanceOf(ListInstruction.class, instr);
    }

    @Test
    public void testParseFindCommand() throws ShrekException {
        Instruction instr = Parser.parse("find book");
        assertInstanceOf(FindInstruction.class, instr);
    }

    @Test
    public void testParseFindCommand_emptyQuery_throwsException() {
        assertThrows(ShrekException.class, () -> Parser.parse("find"));
        assertThrows(ShrekException.class, () -> Parser.parse("find   "));
    }

    @Test
    public void testParseOnDateCommand() throws ShrekException {
        Instruction instr = Parser.parse("ondate 2025-01-01");
        assertInstanceOf(OnDateInstruction.class, instr);
    }

    @Test
    public void testParseSortCommand() throws ShrekException {
        Instruction instr = Parser.parse("sort description");
        assertInstanceOf(SortInstruction.class, instr);

        instr = Parser.parse("sort date");
        assertInstanceOf(SortInstruction.class, instr);

        instr = Parser.parse("sort type");
        assertInstanceOf(SortInstruction.class, instr);
    }

    @Test
    public void testParseByeCommand() throws ShrekException {
        Instruction instr = Parser.parse("bye");
        assertInstanceOf(ExitInstruction.class, instr);
    }

    @Test
    public void testParseInvalidCommand_throwsException() {
        assertThrows(ShrekException.class, () -> Parser.parse("invalid command"));
        assertThrows(ShrekException.class, () -> Parser.parse(""));
    }

    // === TASK EXECUTION TESTS ===

    @Test
    public void testAddTodoExecution() throws ShrekException {
        AddInstruction instr = (AddInstruction) Parser.parse("todo test todo");
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);

        String result = instr.execute(list, ui, storage);
        assertEquals(1, list.size());
        assertTrue(result.contains("test todo"));
        assertTrue(result.contains("1 tasks"));
    }

    @Test
    public void testAddDeadlineExecution() throws ShrekException {
        AddInstruction instr = (AddInstruction) Parser.parse("deadline test deadline /by 2025-01-01 14:00");
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);

        String result = instr.execute(list, ui, storage);
        assertEquals(1, list.size());
        assertTrue(result.contains("test deadline"));
        assertTrue(result.contains("2:00PM"));
    }

    @Test
    public void testAddEventExecution() throws ShrekException {
        AddInstruction instr = (AddInstruction) Parser.parse("event test event "
                + "/from 2025-01-01 14:00 /to 2025-01-01 16:00");
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);

        String result = instr.execute(list, ui, storage);
        assertEquals(1, list.size());
        assertTrue(result.contains("test event"));
        assertTrue(result.contains("2:00PM"));
        assertTrue(result.contains("4:00PM"));
    }

    @Test
    public void testMarkExecution() throws ShrekException {
        TaskList list = createTestTaskList();
        MarkInstruction instr = new MarkInstruction(0, true);
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);

        String result = instr.execute(list, ui, storage);
        assertTrue(list.get(0).getStatusIcon().equals("X"));
        assertTrue(result.contains("marked done"));
    }

    @Test
    public void testDeleteExecution() throws ShrekException {
        TaskList list = createTestTaskList();
        int initialSize = list.size();
        DeleteInstruction instr = new DeleteInstruction(0);
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);

        String result = instr.execute(list, ui, storage);
        assertEquals(initialSize - 1, list.size());
        assertTrue(result.contains("YEETED"));
    }

    @Test
    public void testFindExecution() throws ShrekException {
        TaskList list = createTestTaskList();
        FindInstruction instr = new FindInstruction("test");
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);

        String result = instr.execute(list, ui, storage);
        assertTrue(result.contains("Matching onions"));
        assertTrue(result.contains("test task"));
    }

    @Test
    public void testOnDateExecution() throws ShrekException {
        TaskList list = createTestTaskList();
        OnDateInstruction instr = new OnDateInstruction(LocalDate.now());
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);

        String result = instr.execute(list, ui, storage);
        assertTrue(result.contains("No onions") || result.contains("Tasks on"));
    }

    // === DUPLICATE DETECTION TESTS ===

    @Test
    public void testDuplicateTodoDetection() throws ShrekException {
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);

        // Add first todo
        AddInstruction instr1 = (AddInstruction) Parser.parse("todo read book");
        instr1.execute(list, ui, storage);

        // Try to add duplicate
        AddInstruction instr2 = (AddInstruction) Parser.parse("todo read book");
        ShrekException exception = assertThrows(ShrekException.class, (
        ) -> instr2.execute(list, ui, storage));

        assertTrue(exception.getMessage().contains("Duplicate"));
        assertEquals(1, list.size()); // Should still only have 1 task
    }

    @Test
    public void testDifferentTodosAllowed() throws ShrekException {
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);

        AddInstruction instr1 = (AddInstruction) Parser.parse("todo read book");
        AddInstruction instr2 = (AddInstruction) Parser.parse("todo write book");

        instr1.execute(list, ui, storage);
        instr2.execute(list, ui, storage);

        assertEquals(2, list.size()); // Both should be added
    }

    // === INTEGRATION TESTS ===

    @Test
    public void testTodoAndMark() {
        String input = "todo borrow book\nmark 1\nbye\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        new Shrek(TEST_FILE_PATH).runTextMode();

        String actualOutput = out.toString();
        String expected = "Hello I'm Shrek!\n"
                + "Welcome to my Swamp!\n"
                + "What can I do for you?\n"
                + "Okies, onion (task) added:\n"
                + "  [T][ ] borrow book\n"
                + "Now you have 1 tasks in the list.\n"
                + "Awesome! One layer of onion(task) has been removed\n"
                + "(marked done)\n"
                + "  [T][X] borrow book\n"
                + "Bye! I'm going to find Princess Fiona :)\n"
                + "See ya later\n";

        assertEquals(normalize(expected), normalize(actualOutput));
    }

    @Test
    public void testMultipleCommandsIntegration() {
        String input = "todo task 1\n"
                + "todo task 2\n"
                + "list\n"
                + "delete 1\n"
                + "list\n"
                + "bye\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        new Shrek(TEST_FILE_PATH).runTextMode();

        String actualOutput = out.toString();
        assertTrue(actualOutput.contains("task 1"));
        assertTrue(actualOutput.contains("task 2"));
        assertTrue(actualOutput.contains("YEETED"));
        assertTrue(actualOutput.contains("1 tasks"));
    }

    // === EDGE CASE TESTS ===

    @Test
    public void testEventTimeValidation() {
        // Start time after end time should throw exception
        assertThrows(ShrekException.class, (
        ) -> new Event("test", "2025-01-02 14:00", "2025-01-01 14:00"));
    }

    @Test
    public void testDeadlineTimeValidation() {
        // Valid deadline should work
        assertThrows(ShrekException.class, (
        ) -> new Deadline("test", "invalid-date"));
    }

    @Test
    public void testTaskListBounds() {
        TaskList list = new TaskList();
        assertThrows(ShrekException.class, () -> list.get(0));
        assertThrows(ShrekException.class, () -> list.remove(0));
    }

    // === STORAGE TESTS ===

    @Test
    public void testStorageSaveAndLoad() throws ShrekException {
        Storage storage = new Storage(TEST_FILE_PATH);
        ArrayList<Task> originalTasks = new ArrayList<>();
        originalTasks.add(new Todo("storage test"));

        storage.save(originalTasks);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(1, loadedTasks.size());
        assertEquals("storage test", loadedTasks.get(0).getDescription());
    }

    @Test
    public void testStorageEmptyFile() {
        Storage storage = new Storage(TEST_FILE_PATH);
        ArrayList<Task> tasks = storage.load();

        assertEquals(0, tasks.size()); // Should return empty list for new file
    }
}
