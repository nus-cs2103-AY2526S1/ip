package talkgpt.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import talkgpt.task.Task;
import talkgpt.task.ToDo;

public class TaskListTest {

    @Test
    public void testMarkTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("read book", "reading");
        taskList.add(task);
        taskList.markTask(0);

        task.mark();
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testUnmarkTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("read book", true, "reading");
        taskList.add(task);
        taskList.unmarkTask(0);

        task.unmark();
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("read book", "reading");
        Task task2 = new ToDo("return book", "reading");
        Task task3 = new ToDo("walk in the park", "leisure");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        taskList.deleteTask(1);

        TaskList expected = new TaskList();
        expected.add(task1);
        expected.add(task3);

        assertEquals(expected, taskList);
    }

    @Test
    public void testAddTask() {
        TaskList output = new TaskList();
        Task task = new ToDo("read book", "reading");
        output.addTask(task);

        assertEquals(1, output.size());
        assertEquals(task, output.get(0));
    }

    @Test
    public void testToString() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("read book", "reading");
        Task task2 = new ToDo("return book", true, "reading");
        Task task3 = new ToDo("walk in the park", "leisure");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        String expected = "1. [T][ ] read book\n"
                + "2. [T][X] return book\n"
                + "3. [T][ ] walk in the park\n";

        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testFindTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("read book", "reading");
        Task task2 = new ToDo("return book", true, "reading");
        Task task3 = new ToDo("walk in the park", "leisure");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        TaskList found = taskList.findTask("book");

        TaskList expected = new TaskList();
        expected.add(task1);
        expected.add(task2);

        assertEquals(expected, found);
    }

    @Test
    public void testGetTasksByTag() {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("read book", false, "reading");
        Task task2 = new ToDo("return book", true, "reading");
        Task task3 = new ToDo("walk in the park", false, "leisure");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        TaskList found = taskList.getTasksByTag("reading");

        TaskList expected = new TaskList();
        expected.add(task1);
        expected.add(task2);

        assertEquals(expected, found);
    }
}
