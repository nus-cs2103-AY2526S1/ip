import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import floydai.FloydException;
import floydai.task.Deadline;
import floydai.task.Task;
import floydai.task.TaskList;
import floydai.task.Todo;

class TaskListTest {

    @Test
    void testAddAndGetTask() throws FloydException {
        TaskList tasks = new TaskList();
        Task t1 = new Todo("read book");
        tasks.add(t1);

        assertEquals(1, tasks.size());
        assertEquals("read book", tasks.get(0).getDescription());
        assertFalse(tasks.get(0).isDone());
    }

    @Test
    void testDeleteTask() throws FloydException {
        TaskList tasks = new TaskList();
        Task t1 = new Todo("read book");
        Task t2 = new Deadline("return book", "2025-08-26");
        tasks.add(t1);
        tasks.add(t2);

        Task removed = tasks.remove(0);
        assertEquals("read book", removed.getDescription());
        assertEquals(1, tasks.size());
        assertEquals("return book", tasks.get(0).getDescription());
    }
}
