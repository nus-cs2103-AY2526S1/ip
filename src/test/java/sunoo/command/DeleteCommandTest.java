package sunoo.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sunoo.exception.SunooException;
import sunoo.task.Deadline;
import sunoo.task.TaskList;
import sunoo.task.ToDo;

public class DeleteCommandTest {

    @Test
    public void execute_zeroIndex_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new ToDo(false, "homework"));
            new DeleteCommand(0).execute(taskList);
            fail();
        } catch (SunooException e) {
            assertEquals("Sorry ENGENE, that's not a valid task index!", e.getMessage());
        }
    }

    @Test
    public void execute_negativeIndex_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new ToDo(false, "study"));
            new DeleteCommand(-5).execute(taskList);
            fail();
        } catch (SunooException e) {
            assertEquals("Sorry ENGENE, that's not a valid task index!", e.getMessage());
        }
    }

    @Test
    public void execute_validIndex_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo(true, "eat dinner"));
        taskList.addTask(new Deadline(false, "assignment", LocalDateTime.of(2020, 10, 10, 10, 10)));
        taskList.addTask(new ToDo(false, "return books"));
        new DeleteCommand(3).execute(taskList);
        assertEquals(new ToDo(true, "eat dinner"), taskList.getTask(1));
        assertEquals(new Deadline(false, "assignment", LocalDateTime.of(2020, 10, 10, 10, 10)), taskList.getTask(2));
        assertEquals(2, taskList.getNumTasks());
    }
}
