package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import amogus.AmogusException;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setup() {
        taskList = new TaskList();
    }

    @Test
    void addTask_increasesSize() throws AmogusException {
        Task t = new ToDo("buy milk");
        taskList.add(t);
        assertEquals(1, taskList.getSize());
    }

    @Test
    void deleteTask_removesCorrectTask() throws AmogusException {
        Task t1 = new ToDo("laundry");
        Task t2 = new ToDo("dishes");
        taskList.add(t1);
        taskList.add(t2);

        taskList.delete(0); // remove first task
        assertEquals(1, taskList.getSize());
        assertEquals("T | 0 | dishes", taskList.getTaskDesc(0));
    }

    @Test
    void markTask_setsDoneStatus() throws AmogusException {
        Task t = new ToDo("homework");
        taskList.add(t);

        taskList.mark(0);
        assertEquals("T | 1 | homework", taskList.getTaskDesc(0));
    }

    @Test
    void unmarkTask_setsUndoneStatus() throws AmogusException {
        Task t = new ToDo("reading");
        taskList.add(t);
        taskList.mark(0);

        taskList.unmark(0);
        assertEquals("T | 0 | reading", taskList.getTaskDesc(0));
    }

    @Test
    void getTaskDesc_returnsCorrectFormat() throws AmogusException {
        Task t = new ToDo("walk dog");
        taskList.add(t);

        String desc = taskList.getTaskDesc(0);
        assertEquals("T | 0 | walk dog", desc);
    }

    @Test
    void outOfBoundsIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.mark(5); // invalid index
        });
    }
}

