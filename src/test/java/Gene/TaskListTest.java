package gene;

import gene.tasks.TodoTask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testToString_emptyTasks() {
        TaskList taskList = new TaskList(new Storage("/data/gene.txt"));
        String expected = Ui.SPACING + "You have no tasks in your list.";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testToString_withTasks() {
        TaskList taskList = new TaskList(new Storage("/data/gene.txt"));
        TodoTask task1 = new TodoTask("Task 1", false);
        TodoTask task2 = new TodoTask("Task 2", false);
        taskList.addTask(task1);
        taskList.addTask(task2);
        String expected = Ui.SPACING + "Here are the tasks in your list:\n"
                + Ui.SPACING + " 1. " + task1 + "\n"
                + Ui.SPACING + " 2. " + task2;

        assertEquals(expected, taskList.toString());
    }
}
