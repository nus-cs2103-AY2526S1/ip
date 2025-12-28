package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.task.Todo;
import bytebuddy.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    public static String LINE = "__________________________________";

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        Storage storage = new Storage("src/test/data/tasks.txt");
    }

    @Test
    void execute_validIndex_deletesTaskSuccessfully() {
        Task todo = new Todo("read book");
        tasks.addTask(todo);
        DeleteCommand command = new DeleteCommand("1");
        String result = command.execute(storage, tasks, ui);

        assertEquals(0, tasks.getSize(), "TaskList should be empty after deletion");
        String expectedMessage = ui.getDeletedMessage(tasks, todo);
        assertEquals(expectedMessage, result, "UI should return correct deletion message");
    }

    @Test
    void execute_emptyBody_returnsErrorMessage() {
        DeleteCommand command = new DeleteCommand("");
        String result = command.execute(storage, tasks, ui);
        assertEquals(String.format("%s\nError: Empty body.\n%s", LINE, LINE), result,
                "UI should return error message for empty body");
    }

    @Test
    void execute_nonNumericBody_returnsErrorMessage() {
        DeleteCommand command = new DeleteCommand("abc");

        String result = command.execute(storage, tasks, ui);

        assertEquals(String.format("%s\nError: Please provide a valid task number to delete.\n%s", LINE, LINE), result,
                "UI should return error for non-numeric task index");
    }

    @Test
    void execute_outOfRangeIndex_returnsErrorMessage() {
        Task todo = new Todo("read book");
        tasks.addTask(todo);
        DeleteCommand command = new DeleteCommand("5"); // only 1 task exists

        String result = command.execute(storage, tasks, ui);

        assertEquals(String.format("%s\nError: Invalid task index.\n%s", LINE, LINE), result,
                "UI should return error for index out of range");
    }
}
