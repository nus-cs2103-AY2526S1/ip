package echo.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import echo.task.Task;
import echo.task.Todo;

public class TaskListTest {
    @Test
    public void testDeleteTaskValidIndices() {
        TaskList list = new TaskList(List.of(
                new Todo("t1"), new Todo("t2"), new Todo("t3")));

        Task removed = list.deleteTask(1);
        assertEquals("t1", removed.getDescription());
        assertEquals(2, list.getSize());

        removed = list.deleteTask(2);
        assertEquals("t3", removed.getDescription());
        assertEquals(1, list.getSize());
    }

    @Test
    public void testDeleteTaskInvalidIndices() {
        TaskList list = new TaskList(List.of(new Todo("t1")));

        assertThrows(IndexOutOfBoundsException.class, () -> list.deleteTask(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.deleteTask(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.deleteTask(5));
    }

    @Test
    public void testGetTasksWithKeywordPartialMatches() {
        TaskList list = new TaskList(List.of(
                new Todo("write code"),
                new Todo("review code"),
                new Todo("write tests")
        ));

        TaskList result = list.getTasksWithKeyword("write");
        assertEquals(2, result.getSize());
        assertEquals("write code", result.getTask(1).getDescription());
        assertEquals("write tests", result.getTask(2).getDescription());
    }

    @Test
    public void testGetTasksWithKeywordNoMatchesAndEmptyKeyword() {
        TaskList list = new TaskList(List.of(new Todo("task1"), new Todo("task2")));

        TaskList noMatch = list.getTasksWithKeyword("nonexistent");
        assertEquals(0, noMatch.getSize());

        TaskList emptyKeyword = list.getTasksWithKeyword("");
        assertEquals(2, emptyKeyword.getSize());
        assertEquals("task1", emptyKeyword.getTask(1).getDescription());
        assertEquals("task2", emptyKeyword.getTask(2).getDescription());
    }


}
