package marvin.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import marvin.task.TaskList;
import marvin.task.Todo;

public class DeleteTaskCommandTest {
    @Test
    public void deleteTask_deletesTask_withValidLocator() {
        // Arrange
        DeleteTaskCommand dtc = new DeleteTaskCommand("1");
        TaskList tl = new TaskList();
        tl.addToList(new Todo("Test Todo"));

        // Act
        dtc.execute(tl);

        // Assert
        assertEquals("", tl.toString());
        assertEquals(0, tl.getCount());
    }

    @Test
    public void deleteTask_doesNothing_withInvalidLocator() {
        // Arrange
        DeleteTaskCommand dtc = new DeleteTaskCommand("2");
        TaskList tl = new TaskList();
        tl.addToList(new Todo("Test Todo"));

        // Act
        dtc.execute(tl);

        // Assert
        assertEquals(1, tl.getCount());
        assertEquals("1. [T][ ] Test Todo\n", tl.toString());
    }

    @Test
    public void deleteTask_doesNothing_ifTaskHasSubTask() {
        // Arrange
        DeleteTaskCommand dtc = new DeleteTaskCommand("1");
        TaskList tl = new TaskList();
        Todo first = new Todo("Test Todo");
        Todo second = new Todo("Sub Task");
        first.setChildTask(second);
        tl.addToList(first);

        // Act
        dtc.execute(tl);

        // Assert
        assertEquals(1, tl.getCount());
        assertFalse(first.getDependentTasks().isEmpty());
    }

    @Test
    public void deleteTask_deletesTask_withValidSubtaskLocator() {
        // Arrange - create subtask
        TaskList tl = new TaskList();
        Todo first = new Todo("first");
        Todo second = new Todo("second");
        new AddTaskCommand(first).execute(tl);
        new AddTaskCommand(second).execute(tl);
        new DoAfterCommand("1", "2").execute(tl);

        DeleteTaskCommand dtc = new DeleteTaskCommand("1.1");

        // Act
        dtc.execute(tl);

        // Assert
        assertEquals(1, tl.getCount());
        assertTrue(first.getDependentTasks().isEmpty());
        assertTrue(second.getDependentTasks().isEmpty());
    }
}
