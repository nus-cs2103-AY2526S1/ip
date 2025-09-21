import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import thopz.Deadline;
import thopz.Events;
import thopz.Task;
import thopz.TaskList;
import thopz.Todo;

/**
 * Testing tasklist whether it can be added
 * Testing deletion of tasks also
 * Ensure correct tasks is deleted
 */

class TaskListTest {

    @Test
    void addTest() {
        TaskList ls = new TaskList();
        Task task1 = new Todo("test1");
        Task task2 = new Deadline("test2", "12/12/2025 19:20");
        Task task3 = new Events("test3", "12/12/2024 19:20", "12-12-2025 19:25");
        ls.addTask(task1);
        ls.addTask(task2);
        assertEquals(2, ls.size());
        ls.addTask(task3);
        assertEquals(3, ls.size());
    }

    @Test
    void deleteTest() {
        TaskList ls = new TaskList();
        Task task1 = new Todo("test1");
        Task task2 = new Deadline("test2", "12/12/2025 19:20");
        Task task3 = new Events("test3", "12/12/2024 19:20", "12-12-2025 19:25");
        ls.addTask(task1);
        ls.addTask(task2);
        ls.addTask(task3);
        assertEquals(3, ls.size());
        ls.removeTask(2);
        assertEquals(2, ls.size());
        assertEquals("test1", ls.getTask(1).getTasks());
        assertEquals("test3", ls.getTask(2).getTasks());
    }


    

}
