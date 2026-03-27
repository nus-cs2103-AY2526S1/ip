package david.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import david.exception.DavidException;

class TaskListTest {

    @Test
    void addTask_increasesSize() {
        TaskList tl = new TaskList();
        assertEquals(0, tl.size());

        Task t = new Todo("Read book");
        tl.addTask(t);

        assertEquals(1, tl.size());
        assertEquals(t, tl.getTask(1));
    }

    @Test
    void deleteTask_removesCorrectTask() throws DavidException {
        TaskList tl = new TaskList();
        Task t1 = new Todo("Task 1");
        Task t2 = new Todo("Task 2");

        tl.addTask(t1);
        tl.addTask(t2);

        Task deleted = tl.deleteTask(1);
        assertEquals(t1, deleted);
        assertEquals(1, tl.size());
        assertEquals(t2, tl.getTask(1));
    }

    @Test
    void markAsDone_updatesStatus() throws DavidException {
        TaskList tl = new TaskList();
        Task t = new Todo("Finish homework");
        tl.addTask(t);

        tl.markAsDone(1);

        assertTrue(t.isDone(), "Task should be marked as done");
    }
}

