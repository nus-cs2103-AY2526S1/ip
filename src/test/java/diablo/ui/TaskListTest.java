package diablo.ui;

import diablo.exception.DiabloException;
import diablo.task.Deadline;
import diablo.task.Event;
import diablo.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests methods of the TaskList class
 */
public class TaskListTest {

    /**
     * Tests whether deleting a task from a TaskList containing a single Task results in the correct string output.
     */
    @Test
    public void delete_generalTask_taskDeleted() {
        TaskList taskList = new TaskList();
        Task task1 = new Deadline("do homework", "2025-08-29");
        taskList.addTask(task1);
        String result = "There are currently no items in the list!";
        try {
            taskList.delete(1);
        } catch (DiabloException e) {}

        String output = taskList.formatForWindow();

        assertEquals(result, output);

    }

    /**
     * Tests whether trying to mark a task at an invalid index throws the correct exception.
     */
    @Test
    public void mark_wrongIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        Task task1 = new Deadline("do homework", "2025-08-29");
        Task task2 = new Event("do chores", "2025-08-24", "2025-08-25");
        taskList.addTask(task1);
        taskList.addTask(task2);

        try {
            taskList.mark(3);
            Assertions.fail();
        } catch (DiabloException e) {
            assertEquals("Invalid task number! Check your tasks again.", e.getMessage());
        }


    }

}
