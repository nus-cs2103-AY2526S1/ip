package cattis.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import cattis.CattisInterface;
import cattis.CattisStub;
import cattis.exception.CattisException;
import cattis.task.Task;
import cattis.task.TaskList;

public class AddTodoTaskCommandTest {

    @Test
    public void addTodoTask_markAndUnmarkTask() {
        try {
            AddTaskCommand cmd = new AddTodoTaskCommand("task name with space");
            CattisInterface cattis = new CattisStub();
            cmd.execute(cattis);
            TaskList taskList = cattis.getTaskList();
            Task lastTask = taskList.get(taskList.count());
            assertEquals("[ ]", lastTask.getStatusIcon());
            lastTask.mark();
            assertEquals("[X]", lastTask.getStatusIcon());
            lastTask.unmark();
            assertEquals("[ ]", lastTask.getStatusIcon());
        } catch (CattisException err) {
            fail();
        }
    }

    @Test
    public void addTodoTask_emptyTaskName_exceptionThrown() {
        try {
            AddTaskCommand cmd = new AddTodoTaskCommand("");
            CattisInterface cattis = new CattisStub();
            cmd.execute(cattis);
            fail();
        } catch (CattisException err) {
            assertEquals(CattisException.EMPTY_FIELD, err.getMessage());
        }
    }
}
