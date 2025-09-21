package hachiware;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;


public class TaskListTest {

    @Test
    void addAndGetTasks() {
        TaskList taskList = new TaskList();
        ToDo todo = new ToDo("Do homework");
        Deadline deadline = new Deadline("Submit report", "2025-09-01");

        taskList.add(todo);
        taskList.add(deadline);

        List<Task> tasks = taskList.getAll();
        assertEquals(2, tasks.size(), "TaskList should contain two tasks");
        assertEquals(todo, tasks.get(0), "First task should be the ToDo added");
        assertEquals(deadline, tasks.get(1), "Second task should be the Deadline added");
    }

    @Test
    void deleteTaskReducesSize() throws HachiwareException {
        TaskList taskList = new TaskList();
        ToDo todo = new ToDo("Clean room");
        taskList.add(todo);

        Task removed = taskList.delete(0);
        assertEquals(todo, removed, "Deleted task should be the one added");
        assertEquals(0, taskList.size(), "TaskList should be empty after deletion");
    }

    @Test
    void sizeReflectsNumberOfTasks() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size(), "Empty TaskList should have size 0");

        taskList.add(new ToDo("Task 1"));
        taskList.add(new Deadline("Task 2", "2025-09-01"));
        assertEquals(2, taskList.size(), "TaskList size should match number of added tasks");
    }

    @Test
    void getTaskByIndex() throws HachiwareException {
        TaskList taskList = new TaskList();
        ToDo todo = new ToDo("Task 1");
        taskList.add(todo);

        Task retrieved = taskList.get(0);
        assertEquals(todo, retrieved, "get() should return the correct task by index");
    }
}
