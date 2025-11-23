package stewie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import stewie.exceptions.CommandException;
import stewie.exceptions.OutOfRangeException;
import stewie.storage.Storage;
import stewie.task.TaskList;



/**
 * Tests for {@link UnmarkCommand}.
 */
class UnmarkCommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = Mockito.mock(TaskList.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    void execute_validIndex_unmarksTaskAndSaves() throws CommandException {
        when(taskList.unmarkTask(1)).thenReturn("Task unmarked");

        UnmarkCommand command = new UnmarkCommand("1");
        String result = command.execute(taskList, storage);

        assertEquals("Task unmarked", result);
        verify(taskList).unmarkTask(1);
        verify(storage).saveTasks(taskList);
    }

    @Test
    void execute_outOfBoundsIndex_throwsOutOfRangeException() {
        when(taskList.unmarkTask(99)).thenThrow(new IndexOutOfBoundsException());

        UnmarkCommand command = new UnmarkCommand("99");
        assertThrows(OutOfRangeException.class, () -> command.execute(taskList, storage));
    }
}
