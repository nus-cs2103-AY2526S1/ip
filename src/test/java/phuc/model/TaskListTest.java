package phuc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddAndGetTask() {
        ToDoTask task = new ToDoTask("Test task");
        taskList.add(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    void testRemoveTask() {
        ToDoTask task1 = new ToDoTask("Task 1");
        ToDoTask task2 = new ToDoTask("Task 2");

        taskList.add(task1);
        taskList.add(task2);

        Task removed = taskList.remove(0);
        assertEquals(task1, removed);
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

    @Test
    void testFindTasks() {
        taskList.add(new ToDoTask("Buy milk"));
        taskList.add(new ToDoTask("Buy bread"));
        taskList.add(new ToDoTask("Clean house"));

        ArrayList<Task> results = taskList.find("buy");
        assertEquals(2, results.size());

        results = taskList.find("house");
        assertEquals(1, results.size());
        assertEquals("Clean house", results.get(0).getDescription());
    }

    @Test
    void testSortTasks() {
        LocalDateTime early = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime late = LocalDateTime.of(2024, 12, 31, 23, 59);

        taskList.add(new DeadlineTask("Late task", late));
        taskList.add(new DeadlineTask("Early task", early));
        taskList.add(new ToDoTask("No date task"));

        ArrayList<Task> ascending = taskList.getSortedTasks(true);
        assertEquals("Early task", ascending.get(0).getDescription());
        assertEquals("Late task", ascending.get(1).getDescription());
        assertEquals("No date task", ascending.get(2).getDescription());

        ArrayList<Task> descending = taskList.getSortedTasks(false);
        assertEquals("No date task", descending.get(0).getDescription());
        assertEquals("Late task", descending.get(1).getDescription());
        assertEquals("Early task", descending.get(2).getDescription());
    }
}
