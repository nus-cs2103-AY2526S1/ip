package Mithrandir;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import Mithrandir.MithrandirExceptions.InvalidArgumentException;
import Mithrandir.MithrandirExceptions.MithrandirException;
import Mithrandir.storage.FileStorage;
import Mithrandir.task.Todo;
import Mithrandir.ui.Ui;

public class CommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Ui ui;
    private TaskList taskList;
    private FileStorage fileStorage;

    @BeforeEach
    void setUp(@TempDir Path tempDir) {
        System.setOut(new PrintStream(outContent));
        ui = new Ui();
        taskList = new TaskList();
        Path tempFile = tempDir.resolve("test_save.txt");
        fileStorage = new FileStorage(tempFile.toString());
    }

    @Test
    void Command_execute_BYE_noArguments_printsGoodbyeMessage() throws MithrandirException, IOException {
        Command.BYE.execute(ui, taskList, "", fileStorage);
        assertTrue(outContent.toString().contains("Farewell. My work is now finished."));
    }

    @Test
    void Command_execute_BYE_withArguments_throwsException() {
        assertThrows(InvalidArgumentException.class,
                () -> Command.BYE.execute(ui, taskList, "extra argument", fileStorage));
    }

    @Test
    void Command_execute_LIST_emptyList_printsEmptyMessage() throws MithrandirException, IOException {
        Command.LIST.execute(ui, taskList, "", fileStorage);
        assertTrue(outContent.toString().contains("No tasks in the list."));
    }

    @Test
    void Command_execute_LIST_withTasks_printsTaskList() throws MithrandirException, IOException {
        taskList.addTask(new Todo("Test task"));
        outContent.reset();
        Command.LIST.execute(ui, taskList, "", fileStorage);
        assertTrue(outContent.toString().contains("1. [T][ ] Test task"));
    }

    @Test
    void Command_execute_LIST_withArguments_throwsException() {
        assertThrows(InvalidArgumentException.class,
                () -> Command.LIST.execute(ui, taskList, "extra argument", fileStorage));
    }

    @Test
    void Command_execute_MARK_validIndex_marksTask() throws MithrandirException, IOException {
        taskList.addTask(new Todo("Test task"));
        Command.MARK.execute(ui, taskList, "1", fileStorage);
        assertTrue(taskList.getTask(0).isMarked());
    }

    @Test
    void Command_execute_MARK_invalidIndex_throwsException() {
        taskList.addTask(new Todo("Test task"));
        assertThrows(IndexOutOfBoundsException.class,
                () -> Command.MARK.execute(ui, taskList, "2", fileStorage));
    }

    @Test
    void Command_execute_MARK_nonNumericInput_throwsException() {
        taskList.addTask(new Todo("Test task"));
        assertThrows(InvalidArgumentException.class,
                () -> Command.MARK.execute(ui, taskList, "not a number", fileStorage));
    }

    @Test
    void Command_execute_UNMARK_validIndex_unmarksTask() throws MithrandirException, IOException {
        Todo todo = new Todo("Test task");
        todo.markDone();
        taskList.addTask(todo);
        Command.UNMARK.execute(ui, taskList, "1", fileStorage);
        assertFalse(taskList.getTask(0).isMarked());
    }

    @Test
    void Command_execute_TODO_validDescription_addsTask() throws MithrandirException, IOException {
        Command.TODO.execute(ui, taskList, "Buy groceries", fileStorage);
        assertEquals(1, taskList.getSize());
        assertEquals("Buy groceries", taskList.getTask(0).getDescription());
    }

    @Test
    void Command_execute_TODO_emptyDescription_throwsException() {
        assertThrows(InvalidArgumentException.class,
                () -> Command.TODO.execute(ui, taskList, "", fileStorage));
    }

    @Test
    void Command_execute_DEADLINE_validInput_createsDeadline() throws MithrandirException, IOException {
        Command.DEADLINE.execute(ui, taskList,
                "Submit report /by 31/12/2023 23:59", fileStorage);
        assertEquals(1, taskList.getSize());
        assertTrue(taskList.getTask(0).toString().contains("[D][ ] Submit report (by: 31/12/2023 23:59)"));
    }

    @Test
    void Command_execute_DEADLINE_missingBy_throwsException() {
        assertThrows(InvalidArgumentException.class,
                () -> Command.DEADLINE.execute(ui, taskList, "Submit report", fileStorage));
    }

    @Test
    void Command_execute_EVENT_validInput_createsEvent() throws MithrandirException, IOException {
        Command.EVENT.execute(ui, taskList,
                "Team meeting /from 01/01/2024 14:00 /to 01/01/2024 15:00", fileStorage);
        assertEquals(1, taskList.getSize());
        assertTrue(taskList.getTask(0).toString().contains(
                "[E][ ] Team meeting (from: 01/01/2024 14:00 to: 01/01/2024 15:00)"));
    }

    @Test
    void Command_execute_EVENT_missingTo_throwsException() {
        assertThrows(InvalidArgumentException.class,
                () -> Command.EVENT.execute(ui, taskList,
                        "Team meeting /from 01/01/2024 14:00", fileStorage));
    }

    @Test
    void Command_execute_DELETE_validIndex_removesTask() throws MithrandirException, IOException {
        taskList.addTask(new Todo("Task to delete"));
        Command.DELETE.execute(ui, taskList, "1", fileStorage);
        assertEquals(0, taskList.getSize());
    }

    @Test
    void Command_execute_FIND_matchingKeyword_returnsMatchingTasks() throws MithrandirException, IOException {
        taskList.addTask(new Todo("Buy groceries"));
        taskList.addTask(new Todo("Buy new shoes"));
        taskList.addTask(new Todo("Do laundry"));

        Command.FIND.execute(ui, taskList, "buy", fileStorage);
        String output = outContent.toString();
        assertTrue(output.contains("Buy groceries"));
        assertTrue(output.contains("Buy new shoes"));
        assertFalse(output.contains("Do laundry"));
    }

    @Test
    void Command_execute_integration_saveAndLoadTasks() throws Exception {
        // Add tasks and save to file
        Command.TODO.execute(ui, taskList, "Test storage", fileStorage);

        // Create new task list and load from file
        TaskList newTaskList = new TaskList();
        fileStorage.loadTaskList().getTasks().forEach(newTaskList::addTask);

        assertEquals(1, newTaskList.getSize());
        assertEquals("Test storage", newTaskList.getTask(0).getDescription());
    }
}