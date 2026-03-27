package marvin.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import marvin.task.TaskList;
import marvin.task.Todo;

public class MarkTaskCommandTest {
    @Test
    public void markTask_marksTask_validLocator() {
        // Arrange
        MarkTaskCommand mtc = new MarkTaskCommand("1", true);
        TaskList tl = new TaskList();
        Todo todo = new Todo("Test Todo");
        tl.addToList(todo);

        // Act
        mtc.execute(tl);

        // Assert
        assertTrue(todo.getIsDone());
    }

    @Test
    public void unmarkTask_unmarksTask_validLocator() {
        // Arrange
        MarkTaskCommand mtc = new MarkTaskCommand("1", false);
        TaskList tl = new TaskList();
        Todo todo = new Todo("Test Todo", true);
        tl.addToList(todo);

        // Act
        mtc.execute(tl);

        // Assert
        assertFalse(todo.getIsDone());
    }

    @Test
    public void markTask_marksTask_validSubTaskLocator() {
        // Arrange
        MarkTaskCommand mtc = new MarkTaskCommand("1.1", true);
        TaskList tl = new TaskList();
        Todo todo = new Todo("Test Todo", true);
        Todo subtask = new Todo("dependent", false);
        todo.getDependentTasks().add(subtask);
        tl.addToList(todo);

        // Act
        mtc.execute(tl);

        // Assert
        assertTrue(subtask.getIsDone());
    }

    @Test
    public void unmarkTask_unmarksTask_validSubTaskLocator() {
        // Arrange
        MarkTaskCommand mtc = new MarkTaskCommand("1.1", false);
        TaskList tl = new TaskList();
        Todo todo = new Todo("Test Todo", true);
        Todo subtask = new Todo("dependent", true);
        todo.getDependentTasks().add(subtask);
        tl.addToList(todo);

        // Act
        mtc.execute(tl);

        // Assert
        assertFalse(subtask.getIsDone());
    }

    @Test
    public void markTask_doesNothing_ifParentNotDone() {
        // Arrange
        MarkTaskCommand mtc = new MarkTaskCommand("1.1", true);
        TaskList tl = new TaskList();
        Todo todo = new Todo("Test Todo", false);
        Todo subtask = new Todo("dependent", false);
        tl.addToList(todo);
        tl.addToList(subtask);
        tl.setTaskToDoAfter("1", "2");

        // Act
        mtc.execute(tl);

        // Assert
        assertFalse(subtask.getIsDone());
    }

    @Test
    public void markTask_doesNothing_invalidLocator() {
        // Arrange
        MarkTaskCommand mtc = new MarkTaskCommand("1.1", false);
        TaskList tl = new TaskList();
        Todo todo = new Todo("Test Todo", true);
        tl.addToList(todo);

        // Act
        mtc.execute(tl);

        // Assert
        assertEquals(1, tl.getCount());
        assertTrue(todo.getIsDone());
    }


}
