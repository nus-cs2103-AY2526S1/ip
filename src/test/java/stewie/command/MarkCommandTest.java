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
 * Tests for {@link MarkCommand}.
 */
class MarkCommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = Mockito.mock(TaskList.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    void execute_validIndex_marksTaskAndSaves() throws CommandException {
        when(taskList.markTask(1)).thenReturn("Task marked");

        MarkCommand command = new MarkCommand("1");
        String result = command.execute(taskList, storage);

        assertEquals("Task marked", result);
        verify(taskList).markTask(1);
        verify(storage).saveTasks(taskList);
    }

    @Test
    void execute_outOfBoundsIndex_throwsOutOfRangeException() {
        when(taskList.markTask(99)).thenThrow(new IndexOutOfBoundsException());

        MarkCommand command = new MarkCommand("99");
        assertThrows(OutOfRangeException.class, () -> command.execute(taskList, storage));
    }
}
