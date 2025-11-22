package test;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import command.FindCommand;
import exception.GenieweenieException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class FindCommandTest {

    @Test
    public void testFindCommandExecution() {
        // Initialize test objects
        TaskList tasks = new TaskList(); // TaskList with internal ArrayList
        Ui ui = new Ui();
        Storage storage = null; // Pass null or a mock if you don't need actual storage

        // Add some tasks
        tasks.addTask(new Todo("read book"));
        tasks.addTask(new Todo("return book"));
        tasks.addTask(new Todo("write essay"));

        // Test finding a task
        FindCommand findCommand = new FindCommand("book");

        try {
            findCommand.execute(tasks, ui, storage);
            // Optionally, assert expected output here
        } catch (GenieweenieException e) {
            fail("Execution threw an unexpected GenieweenieException: " + e.getMessage());
        }

        // Test finding a task that does not exist
        FindCommand findNoneCommand = new FindCommand("math");

        try {
            findNoneCommand.execute(tasks, ui, storage);
            // Optionally, assert expected output here
        } catch (GenieweenieException e) {
            fail("Execution threw an unexpected GenieweenieException: " + e.getMessage());
        }
    }
}
