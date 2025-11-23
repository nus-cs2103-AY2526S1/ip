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
import stewie.task.TaskList;
import stewie.task.ToDoTask;


/**
 * Tests for {@link ToDoCommand}.
 */
class ToDoCommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = Mockito.mock(TaskList.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    void execute_validArgs_taskAddedAndSaved() throws CommandException {
        String args = "Buy milk";
        ToDoCommand command = new ToDoCommand(args);

        when(taskList.addTask(Mockito.any(ToDoTask.class))).thenReturn("Task added");

        String result = command.execute(taskList, storage);
        assertEquals("Task added", result);

        ArgumentCaptor<ToDoTask> captor = ArgumentCaptor.forClass(ToDoTask.class);
        verify(taskList).addTask(captor.capture());
        assertEquals("[T][ ] Buy milk", captor.getValue().getDescription());
        verify(storage).saveTasks(taskList);
    }

    @Test
    void parseArgsToToDoTask_blankArgs_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () ->
                ToDoCommand.parseArgsToToDoTask("   "));
    }
}
