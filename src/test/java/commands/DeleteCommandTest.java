package commands;

import hope.commands.DeleteCommand;
import hope.storage.TaskStorage;
import hope.storage.ToDoList;
import hope.tasks.Task;

import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

public class DeleteCommandTest {

    private ToDoList toDoList;
    private TaskStorage taskStorage;
    private DeleteCommand command;

    @BeforeEach
    void setUp() {
        // Create mock objects for ToDoList and TaskStorage
        toDoList = mock(ToDoList.class);
        taskStorage = mock(TaskStorage.class);
        command = new DeleteCommand(toDoList, taskStorage);
    }

    @Test
    void testExecute_NonNumericInput_ReturnsErrorMessage() {
        // Arrange
        String input = "abc";

        // Act
        String result = command.execute(input);

        // Assert
        String expected = """
                        Pray, employ the noble digits as thy guiding input!
                        (Please use numerics as input only)
                        """;
        assertEquals(expected, result);
        verify(toDoList, never()).remove(anyInt());
        verify(taskStorage, never()).update(any(ToDoList.class));
    }

    @Test
    void testExecute_InputExceedsListSize_ReturnsErrorMessage() {
        // Arrange
        String input = "5";
        when(toDoList.size()).thenReturn(3); // List has 3 tasks

        // Act
        String result = command.execute(input);

        // Assert
        String expected = """
                        Thy request doth stray beyond the hallowed limits.
                        (The number you have input is greater than the number of tasks you currently have)
                        """;
        assertEquals(expected, result);
        verify(toDoList, never()).remove(anyInt());
        verify(taskStorage, never()).update(any(ToDoList.class));
    }

    @Test
    void testExecute_ZeroOrNegativeInput_ReturnsErrorMessage() {
        // Arrange
        String input = "0";

        // Act
        String result = command.execute(input);

        // Assert
        String expected = """
                        Doth this be a jest, good sir?
                        (0 and negative numbers are not accepted as input)
                        """;
        assertEquals(expected, result);
        verify(toDoList, never()).remove(anyInt());
        verify(taskStorage, never()).update(any(ToDoList.class));
    }

    @Test
    void testExecute_ValidInput_DeletesTaskAndReturnsSuccessMessage() {
        // Arrange
        String input = "2";
        Task mockTask = mock(Task.class);
        when(toDoList.size()).thenReturn(3).thenReturn(2); // Size before and after deletion
        when(toDoList.get(1)).thenReturn(mockTask); // Index 1 (input "2" is 1-based)
        when(mockTask.toString()).thenReturn("[T][ ] Sample Task");

        // Act
        String result = command.execute(input);

        // Assert
        String expected = """
                        Heed this decree! This noble quest hath been cast aside.

                        [T][ ] Sample Task

                        Lo! Thou art now bestowed with 2 noble quests upon thy parchment of duties.
                        (You now have 2 tasks in the to do list)
                        """;
        assertEquals(expected, result);
        verify(toDoList, times(1)).remove(1); // Verify remove called with index 1
        verify(taskStorage, times(1)).update(toDoList);
    }

    @Test
    void testExecute_NegativeInput_ReturnsErrorMessage() {
        // Arrange
        String input = "-1";

        // Act
        String result = command.execute(input);

        // Assert
        String expected = """
                        Doth this be a jest, good sir?
                        (0 and negative numbers are not accepted as input)
                        """;
        assertEquals(expected, result);
        verify(toDoList, never()).remove(anyInt());
        verify(taskStorage, never()).update(any(ToDoList.class));
    }
}
