package stewie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;

/**
 * Tests for {@link FindCommand}.
 */
class FindCommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = Mockito.mock(TaskList.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    void execute_validKeyword_returnsMatchingTasks() throws CommandException {
        when(taskList.findTaskByDescription("homework")).thenReturn("Found homework task");

        FindCommand command = new FindCommand("homework");
        String result = command.execute(taskList, storage);

        assertEquals("Found homework task", result);
        verify(taskList).findTaskByDescription("homework");
    }

    @Test
    void execute_blankKeyword_throwsInvalidCommandException() {
        FindCommand command = new FindCommand("  ");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList, storage));
    }
}
