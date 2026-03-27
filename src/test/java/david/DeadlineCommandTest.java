package david;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import david.command.DeadlineCommand;
import david.exception.DavidException;
import david.exception.DeadlineException;
import david.storage.Storage;
import david.task.TaskList;
import david.task.Task;
import david.task.Deadline;
import org.junit.jupiter.api.Test;

/*
Used ChatGPT to generate the testcases
 */
class DeadlineCommandTest {

    @Test
    void execute_addsDeadline_correctly() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        DeadlineCommand cmd = new DeadlineCommand("return book /by 2019-12-02 2359");

        cmd.execute(taskList, storage);

        Task added = taskList.getTask(1);
        assertTrue(added instanceof Deadline);
        assertTrue(added.toString().contains("return book"));
    }

    @Test
    void deadlineCommand_missingBy_throwsException() {
        assertThrows(DeadlineException.class, () -> new DeadlineCommand("return book"));
    }

    @Test
    void execute_multipleDeadlines_added() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        new DeadlineCommand("task 1 /by 2025-09-16 2359").execute(taskList, storage);
        new DeadlineCommand("task 2 /by 2025-09-23 2359").execute(taskList, storage);

        assertEquals(2, taskList.size());
    }
}
