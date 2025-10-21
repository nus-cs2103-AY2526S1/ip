package geegar.task;

import geegar.command.*;
import geegar.exception.*;
import geegar.parser.Parser;
import geegar.task.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;
    private Task todo1;
    private Task deadline1;
    private Task event1;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        todo1 = new Todo("read book");
        deadline1 = new Deadline("return book",
                LocalDateTime.of(2025, 8, 8, 18, 0));
        event1 = new Event("project meeting",
                LocalDateTime.of(2025, 8, 8, 18, 0),
                LocalDateTime.of(2025, 8, 8, 19, 0));
    }

    @Test
    public void add_singleTask_sizeIncreased() {
        assertEquals(0, taskList.size());
        taskList.add(todo1);
        assertEquals(1, taskList.size());
    }

    @Test
    public void add_multiplTasks_sizeIncreasedCorrectly() {
        taskList.add(todo1);
        taskList.add(deadline1);
        taskList.add(event1);
        assertEquals(3, taskList.size());
    }

    @Test
    public void get_validIndex_correctTaskReturned() {
        taskList.add(todo1);
        taskList.add(deadline1);

        assertEquals(todo1, taskList.get(0));
        assertEquals(deadline1, taskList.get(1));
    }

    @Test
    public void delete_validIndex_taskRemovedAndReturned() throws InvalidTaskNumberException {
        taskList.add(todo1);
        taskList.add(deadline1);

        Task deleted = taskList.delete(0);
        assertEquals(todo1, deleted);
        assertEquals(1, taskList.size());
        assertEquals(deadline1, taskList.get(0));
    }

    @Test
    public void delete_invalidIndex_invalidTaskNumberExceptionThrown() {
        taskList.add(todo1);

        assertThrows(InvalidTaskNumberException.class, () -> {
            taskList.delete(-1);
        });
    }

    @Test
    public void markTask_validIndex_taskMarkedAsDone() throws InvalidTaskNumberException {
        taskList.add(todo1);
        assertFalse(todo1.isDone);

        taskList.markTask(0);
        assertTrue(todo1.isDone);
    }

    @Test
    public void markTask_invalidIndex_exceptionThrown() {
        taskList.add(todo1);
        assertThrows(InvalidTaskNumberException.class, () -> {
            taskList.markTask(-1);
        });
    }

}