package yuan.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import yuan.task.Task;
import yuan.task.Todo;

/**
 * AI-assisted
 */
class TaskListTest {

    @Test
    void addTask_validTask_increasesSize() {
        TaskList tasks = new TaskList();
        Task todo = new Todo("read book", false);
        tasks.addTask(todo);

        assertEquals(1, tasks.getSize(), "TaskList size should increase after adding a task");
        assertEquals("read book", tasks.get(0).getDescription());
    }

    @Test
    void removeTask_validIndex_returnsRemovedTask() {
        TaskList tasks = new TaskList();
        Task todo = new Todo("do homework", false);
        tasks.addTask(todo);

        Task removed = tasks.removeTask(0);

        assertEquals(todo, removed, "Removed task should match the added task");
        assertEquals(0, tasks.getSize(), "TaskList should be empty after removal");
    }

    @Test
    void findTaskWithKeyword_matchingTask_returnsCorrectList() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read book", false));
        tasks.addTask(new Todo("write essay", false));

        TaskList result = tasks.findTaskWithKeyword("read");

        assertEquals(1, result.getSize(), "Should only find one matching task");
        assertEquals("read book", result.get(0).getDescription());
    }
}
