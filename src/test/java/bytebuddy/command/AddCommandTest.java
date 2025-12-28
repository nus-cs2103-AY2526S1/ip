package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.task.Todo;
import bytebuddy.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage; // not used but required


    @Test
    void execute_addsTaskSuccessfully() {
        tasks = new TaskList();
        ui = new Ui();
        Storage storage = new Storage("src/test/data/tasks.txt");

        Task todo = new Todo("read book");
        AddCommand command = new AddCommand(todo);

        String result = command.execute(storage, tasks, ui);

        assertEquals(1, tasks.getSize(), "TaskList should contain one task after AddCommand execution");
        assertEquals("T | 0 | read book", tasks.getTask(0).toString());
        String expectedMessage = ui.getAddedMessage(tasks, todo);
        assertEquals(expectedMessage, result, "UI message should confirm task was added");
    }

    @Test
    void constructor_nullTask_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new AddCommand(null),
                "Creating AddCommand with null task should throw AssertionError");
    }
}
