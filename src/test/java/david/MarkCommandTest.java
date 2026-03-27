package david;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import david.command.MarkCommand;
import david.exception.DavidException;
import david.storage.Storage;
import david.task.TaskList;
import david.task.Todo;
import org.junit.jupiter.api.Test;

/*
Used ChatGPT to generate the testcases
 */
class MarkCommandTest {

    @Test
    void execute_marksTaskAsDone() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        taskList.addTask(new Todo("homework"));

        new MarkCommand("1").execute(taskList, storage);

        assertTrue(taskList.getTask(1).isDone());
    }

    @Test
    void execute_markAlreadyDoneTask_keepsDone() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        taskList.addTask(new Todo("exercise"));
        taskList.markAsDone(1);

        new MarkCommand("1").execute(taskList, storage);

        assertTrue(taskList.getTask(1).isDone()); // stays done
    }

    @Test
    void markCommand_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        assertThrows(Exception.class, () -> new MarkCommand("2").execute(taskList, storage));
    }
}
