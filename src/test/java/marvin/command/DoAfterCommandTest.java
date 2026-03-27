package marvin.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import marvin.task.TaskList;
import marvin.task.Todo;

public class DoAfterCommandTest {
    @Test
    public void doAfter_setsSubTask_validLocator() {
        // Arrange
        DoAfterCommand dac = new DoAfterCommand("1", "2");
        TaskList tl = new TaskList();
        Todo first = new Todo("Do first");
        Todo second = new Todo("Do after");
        tl.addToList(first);
        tl.addToList(second);

        // Act
        dac.execute(tl);

        // Assert
        assertEquals(2, tl.getCount());
        assertTrue(first.getDependentTasks().contains(second));
    }

    @Test
    public void doAfter_doesNothing_invalidLocator() {
        // Arrange
        DoAfterCommand dac = new DoAfterCommand("1.1.1.3", "2.1.1.1.1.1");
        TaskList tl = new TaskList();

        // Act
        dac.execute(tl);

        // Assert
        assertEquals(0, tl.getCount());
    }

    @Test
    public void doAfter_setsSubSubTask_validLocator() {
        // Arrange
        TaskList tl = new TaskList();
        Todo first = new Todo("Do first");
        Todo second = new Todo("Do after");
        Todo third = new Todo("Sub Sub");
        tl.addToList(first);
        tl.addToList(second);
        tl.addToList(third);

        // Act
        new DoAfterCommand("1", "2").execute(tl);
        new DoAfterCommand("1.1", "2").execute(tl);

        // Assert
        assertEquals(3, tl.getCount());
        assertTrue(first.getDependentTasks().contains(second));
        assertTrue(second.getDependentTasks().contains(third));
    }

    @Test
    public void doAfter_doesNothing_ifCircularDependency() {
        // Arrange
        TaskList tl = new TaskList();
        Todo first = new Todo("Do first");
        Todo second = new Todo("Do after");
        tl.addToList(first);
        tl.addToList(second);

        // Act
        new DoAfterCommand("1", "2").execute(tl);
        new DoAfterCommand("1.1", "1").execute(tl);

        // Assert
        assertEquals(2, tl.getCount());
        assertTrue(first.getDependentTasks().contains(second));
        assertFalse(second.getDependentTasks().contains(first));
    }

    @Test
    public void doAfter_doesNothing_ifTaskAlreadyHasParentDependency() {
        // Arrange
        TaskList tl = new TaskList();

        Todo first = new Todo("Do first");
        Todo second = new Todo("Do after");
        first.setChildTask(second);
        tl.addToList(first);

        Todo third = new Todo("Unrelated");
        tl.addToList(third);

        // Act
        new DoAfterCommand("2", "1.1").execute(tl);

        // Assert
        assertTrue(first.getDependentTasks().contains(second));
        assertTrue(third.getDependentTasks().isEmpty());
    }
}
