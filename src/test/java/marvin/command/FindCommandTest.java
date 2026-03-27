package marvin.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import marvin.task.TaskList;
import marvin.task.Todo;

public class FindCommandTest {
    @Test
    public void findCommand_outputsOneTask_withQuery() {
        // Arrange
        FindCommand fc = new FindCommand("Work");
        TaskList tl = new TaskList();
        tl.addToList(new Todo("Test Task"));
        tl.addToList(new Todo("Test Task 2"));
        tl.addToList(new Todo("Test Task 3"));
        tl.addToList(new Todo("work"));
        tl.addToList(new Todo("Work"));

        // Act
        String output = fc.execute(tl).getMessage();

        // Assert
        assertTrue(output.contains("5. [T][ ] Work"));
    }

    @Test
    public void findCommand_outputsTwoTasks_withQuery() {
        // Arrange
        FindCommand fc = new FindCommand("ork");
        TaskList tl = new TaskList();
        tl.addToList(new Todo("Test Task"));
        tl.addToList(new Todo("Test Task 2"));
        tl.addToList(new Todo("Test Task 3"));
        tl.addToList(new Todo("work"));
        tl.addToList(new Todo("Work"));

        // Act
        String output = fc.execute(tl).getMessage();

        // Assert
        String expected = "4. [T][ ] work\n5. [T][ ] Work";
        assertTrue(output.contains(expected));
    }
}
