package stewie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import stewie.exceptions.CommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;

/**
 * Tests for {@link ListCommand}.
 */
class ListCommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = Mockito.mock(TaskList.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    void execute_always_returnsListFromTaskList() throws CommandException {
        when(taskList.listTask()).thenReturn("List of tasks");

        ListCommand command = new ListCommand();
        String result = command.execute(taskList, storage);

        assertEquals("List of tasks", result);
        verify(taskList).listTask();
    }
}
