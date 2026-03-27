package marvin.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import marvin.task.Deadline;
import marvin.task.Event;
import marvin.task.TaskList;
import marvin.task.Todo;

public class ListTaskCommandTest {
    @Test
    public void listCommand_outputs_todoCommand() {
        // Arrange
        ListTaskCommand ltc = new ListTaskCommand();
        TaskList tl = new TaskList();
        tl.addToList(new Todo("Test Task"));

        // Act
        String output = ltc.execute(tl).getMessage();

        // Assert
        assertTrue(output.contains("1. [T][ ] Test Task"));
    }

    @Test
    public void listCommand_outputs_deadlineCommand() {
        // Arrange
        ListTaskCommand ltc = new ListTaskCommand();
        TaskList tl = new TaskList();
        tl.addToList(new Deadline("Test Task",
                LocalDateTime.of(2025, 12, 1, 18, 0))
        );

        // Act
        String output = ltc.execute(tl).getMessage();

        // Assert
        assertTrue(output.contains("1. [D][ ] Test Task (by: 01-12-2025, 6PM)"));
    }

    @Test
    public void listCommand_outputs_eventCommand() {
        // Arrange
        ListTaskCommand ltc = new ListTaskCommand();
        TaskList tl = new TaskList();
        tl.addToList(new Event("Test Task",
                LocalDateTime.of(2025, 12, 1, 18, 0),
                LocalDateTime.of(2025, 12, 2, 9, 0)
        ));

        // Act
        String output = ltc.execute(tl).getMessage();

        // Assert
        assertTrue(output.contains("1. [E][ ] Test Task (from: 01-12-2025, 6PM to: 02-12-2025, 9AM)"));
    }

    @Test
    public void listCommand_outputs_doAfterTasks() {
        // Arrange
        ListTaskCommand ltc = new ListTaskCommand();
        TaskList tl = new TaskList();
        Todo parent = new Todo("Parent Task");
        Todo after = new Todo("After Task");
        tl.addToList(parent);
        parent.setChildTask(after);

        // Act
        String output = ltc.execute(tl).getMessage();

        // Assert
        assertTrue(output.contains("1. [T][ ] Parent Task\n  ---->1.1 [T][ ] After Task\n"));
    }
}
