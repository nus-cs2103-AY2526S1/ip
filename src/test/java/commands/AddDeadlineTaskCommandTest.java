package commands;

import hope.commands.AddDeadlineTaskCommand;
import hope.storage.TaskStorage;
import hope.storage.ToDoList;
import hope.tasks.DeadlineTask;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddDeadlineTaskCommandTest {

    private ToDoList toDoList;
    private TaskStorage taskStorage;
    private AddDeadlineTaskCommand command;

    @BeforeEach
    void setUp() {
        // Create mock objects for ToDoList and TaskStorage
        toDoList = mock(ToDoList.class);
        taskStorage = mock(TaskStorage.class);
        command = new AddDeadlineTaskCommand(toDoList, taskStorage);
    }

    @Test
    void testExecute_EmptyInput_ReturnsErrorMessage() {
        // Arrange
        String input = "";

        // Act
        String result = command.execute(input);

        // Assert
        String expected = "Thou hast overlooked the noble task of bestowing a worthy description upon this "
                + "endeavor!(Looks like you forgot to input a description and a deadline! Try again)\n";
        assertEquals(expected, result);
        verify(toDoList, never()).add(any(DeadlineTask.class));
        verify(taskStorage, never()).append(any(DeadlineTask.class));
    }

    @Test
    void testExecute_InvalidInputNoDescription_ReturnsErrorMessage() {
        // Arrange
        String input = "/by tomorrow";

        // Act
        String result = command.execute(input);

        // Assert
        String expected = "Verily, thou hast erred in thy response; endeavor once more, brave soul!"
                + "(Incorrect input, please try again)\n";
        assertEquals(expected, result);
        verify(toDoList, never()).add(any(DeadlineTask.class));
        verify(taskStorage, never()).append(any(DeadlineTask.class));
    }

    @Test
    void testExecute_InvalidInputNoDeadline_ReturnsErrorMessage() {
        // Arrange
        String input = "task description /by";

        // Act
        String result = command.execute(input);

        // Assert
        String expected = "Verily, thou hast erred in thy response; endeavor once more, brave soul!"
                + "(Incorrect input, please try again)\n";
        assertEquals(expected, result);
        verify(toDoList, never()).add(any(DeadlineTask.class));
        verify(taskStorage, never()).append(any(DeadlineTask.class));
    }

    @Test
    void testExecute_ValidInput_AddsTaskAndReturnsSuccessMessage() {
        // Arrange
        String input = "Complete homework /by tomorrow";
        when(toDoList.size()).thenReturn(1); // Mock size to return 1 after adding task
        DeadlineTask mockTask = mock(DeadlineTask.class);
        when(mockTask.toString()).thenReturn("[D][ ] Complete homework (by: tomorrow)");

        // Create a real DeadlineTask for testing
        try {
            DeadlineTask realTask = new DeadlineTask("Complete homework", "tomorrow");
            // If constructor doesn't throw, proceed with mocking
            when(toDoList.size()).thenReturn(1);
        } catch (Exception e) {
            fail("DeadlineTask constructor threw an unexpected exception: " + e.getMessage());
        }

        // Act
        String result = command.execute(input);

        // Assert
        String expected = "Behold, this quest hath been entrusted!\n\n"
                + "[D][ ] Complete homework (by: tomorrow)\n\n"
                + "Lo! Thou art now bestowed with 1 noble quests upon thy parchment of duties.\n"
                + "(You now have 1 tasks in the list)\n";
        assertTrue(result.contains("Behold, this quest hath been entrusted!"));
        assertTrue(result.contains("(You now have 1 tasks in the list)"));
        verify(toDoList, times(1)).add(any(DeadlineTask.class));
        verify(taskStorage, times(1)).append(any(DeadlineTask.class));
    }
}
