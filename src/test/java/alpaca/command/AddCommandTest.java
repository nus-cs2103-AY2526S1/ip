package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;
import alpaca.task.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple JUnit tests for AddCommand - focusing on basic functionality.
 */
class AddCommandTest {

    @Test
    void testAddValidTodo() {
        // Setup
        TaskList tasks = new TaskList();
        Ui ui = new Ui() {
            @Override
            public void showMessage(String message) {
                // Do nothing
            }
        };
        Storage storage = new Storage("test.txt") {
            public void save(java.util.List<alpaca.task.Task> taskList) {
                // Do nothing
            }
        };

        // Test
        AddCommand command = new AddCommand("todo", "read book");
        String result = command.execute(tasks, ui, storage);

        // Verify
        assertEquals(1, tasks.size());
        assertTrue(result.contains("Got it. I've added this task"));
        assertTrue(result.contains("Now you have 1 tasks"));
    }

    @Test
    void testAddTodoEmptyDescription() {
        // Setup
        TaskList tasks = new TaskList();
        Ui ui = new Ui() {
            @Override
            public void showMessage(String message) {
                // Do nothing
            }
        };
        Storage storage = new Storage("test.txt") {
            public void save(java.util.List<alpaca.task.Task> taskList) {
                // Do nothing
            }
        };

        // Test
        AddCommand command = new AddCommand("todo", "");
        String result = command.execute(tasks, ui, storage);

        // Verify
        assertEquals(0, tasks.size());
        assertTrue(result.contains("Invalid input"));
        assertTrue(result.contains("Todo description cannot be empty"));
    }

    @Test
    void testAddDeadlineValid() {
        // Setup
        TaskList tasks = new TaskList();
        Ui ui = new Ui() {
            @Override
            public void showMessage(String message) {
                // Do nothing
            }
        };
        Storage storage = new Storage("test.txt") {
            public void save(java.util.List<alpaca.task.Task> taskList) {
                // Do nothing
            }
        };

        // Test
        AddCommand command = new AddCommand("deadline", "assignment /by 2025-10-15");
        String result = command.execute(tasks, ui, storage);

        // Verify
        assertEquals(1, tasks.size());
        assertTrue(result.contains("Got it. I've added this task"));
    }

    @Test
    void testAddDeadlineInvalidFormat() {
        // Setup
        TaskList tasks = new TaskList();
        Ui ui = new Ui() {
            @Override
            public void showMessage(String message) {
                // Do nothing
            }
        };
        Storage storage = new Storage("test.txt") {
            public void save(java.util.List<alpaca.task.Task> taskList) {
                // Do nothing
            }
        };

        // Test
        AddCommand command = new AddCommand("deadline", "assignment without by");
        String result = command.execute(tasks, ui, storage);

        // Verify
        assertEquals(0, tasks.size());
        assertTrue(result.contains("Invalid input"));
        assertTrue(result.contains("Deadline format"));
    }

    @Test
    void testUnknownTaskType() {
        // Setup
        TaskList tasks = new TaskList();
        Ui ui = new Ui() {
            @Override
            public void showMessage(String message) {
                // Do nothing
            }
        };
        Storage storage = new Storage("test.txt") {
            public void save(java.util.List<alpaca.task.Task> taskList) {
                // Do nothing
            }
        };

        // Test
        AddCommand command = new AddCommand("unknown", "some task");
        String result = command.execute(tasks, ui, storage);

        // Verify
        assertEquals(0, tasks.size());
        assertTrue(result.contains("Invalid input"));
        assertTrue(result.contains("Unknown task type"));
    }
}