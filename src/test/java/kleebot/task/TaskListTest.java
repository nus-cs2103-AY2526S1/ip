package kleebot.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTask() {
        Task todo = new ToDo("Buy eggs");
        taskList.addToList(todo);
        assertEquals(1, taskList.getSize());
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    void testDeleteTask() {
        Task todo = new ToDo("Do laundry");
        taskList.addToList(todo);

        int input = 1;
        taskList.delete(input);
        assertEquals(0, taskList.getSize());
    }

    @Test
    void testMarkAndUnmarkItem() {
        Task deadline = new Deadline("Submit report", "Friday");
        taskList.addToList(deadline);

        String[] input = {"mark", "1"};
        taskList.markItem(input);
        assertTrue(taskList.getTask(0).getStatus());

        String[] input2 = {"unmark", "1"};
        taskList.unmarkItem(input2);
        assertFalse(taskList.getTask(0).getStatus());
    }

    @Test
    void testGetTasks() {
        Task todo = new ToDo("Play guitar");
        Task event = new Event("Concert", "7pm", "10pm");

        taskList.addToList(todo);
        taskList.addToList(event);

        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(todo));
        assertTrue(tasks.contains(event));
    }

}