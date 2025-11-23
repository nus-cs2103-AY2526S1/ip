package stewie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.Task;
import stewie.task.TaskList;

/**
 * Tests for {@link UpdateCommand}.
 */
class UpdateCommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = Mockito.mock(TaskList.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    void execute_validTodoArgs_updatesTask() throws CommandException {
        when(taskList.updateTask(Mockito.eq(1), Mockito.any(Task.class))).thenReturn("Task updated");

        UpdateCommand command = new UpdateCommand("1 todo Buy milk");
        String result = command.execute(taskList, storage);

        assertEquals("Task updated", result);

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskList).updateTask(Mockito.eq(1), captor.capture());
        Task updatedTask = captor.getValue();
        assertEquals("[T][ ] Buy milk", updatedTask.getDescription());

        verify(storage).saveTasks(taskList);
    }

    @Test
    void execute_validDeadlineArgs_updatesTask() throws CommandException {
        when(taskList.updateTask(Mockito.eq(2), Mockito.any(Task.class))).thenReturn("Deadline updated");

        String args = "2 deadline Submit report /by 20/09/2025 18:00";
        UpdateCommand command = new UpdateCommand(args);
        String result = command.execute(taskList, storage);

        assertEquals("Deadline updated", result);

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskList).updateTask(Mockito.eq(2), captor.capture());
        Task updatedTask = captor.getValue();

        assertEquals(
                "[D][ ] Submit report (by: 20 Sep 2025 18:00)",
                updatedTask.getDescription()
        );
        verify(storage).saveTasks(taskList);
    }

    @Test
    void execute_validEventArgs_updatesTask() throws CommandException {
        when(taskList.updateTask(Mockito.eq(5), Mockito.any(Task.class))).thenReturn("Event updated");

        String args = "5 event Project meeting /from 01/11/2025 10:00 /to 01/11/2025 12:00";
        UpdateCommand command = new UpdateCommand(args);
        String result = command.execute(taskList, storage);

        assertEquals("Event updated", result);

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskList).updateTask(Mockito.eq(5), captor.capture());
        Task updatedTask = captor.getValue();

        assertEquals(
                "[E][ ] Project meeting (from: 01 Nov 2025 10:00 to: 01 Nov 2025 12:00)",
                updatedTask.getDescription()
        );
        verify(storage).saveTasks(taskList);
    }

    @Test
    void execute_missingArgs_throwsInvalidCommandException() {
        UpdateCommand command = new UpdateCommand("1 todo");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList, storage));
    }

    @Test
    void execute_invalidTaskType_throwsInvalidCommandException() {
        UpdateCommand command = new UpdateCommand("1 somethingElse test");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList, storage));
    }

    @Test
    void execute_nonIntegerIndex_throwsInvalidCommandException() {
        UpdateCommand command = new UpdateCommand("abc todo test");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList, storage));
    }
}
