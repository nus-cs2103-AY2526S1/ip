package stewie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.DeadlineTask;
import stewie.task.TaskList;




/**
 * Tests for {@link DeadlineCommand}.
 */
class DeadlineCommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = Mockito.mock(TaskList.class);
        storage = Mockito.mock(Storage.class);
    }

    @Test
    void execute_validArgs_taskAddedAndSaved() throws CommandException {
        String args = "Submit report /by 20/09/2025 18:00";
        DeadlineCommand command = new DeadlineCommand(args);

        DeadlineTask expectedTask = new DeadlineTask("Submit report",
                LocalDateTime.of(2025, 9, 20, 18, 0));
        when(taskList.addTask(Mockito.any(DeadlineTask.class))).thenReturn("Task added");

        String result = command.execute(taskList, storage);

        assertEquals("Task added", result);

        ArgumentCaptor<DeadlineTask> captor = ArgumentCaptor.forClass(DeadlineTask.class);
        verify(taskList).addTask(captor.capture());
        assertEquals(expectedTask.getDescription(), captor.getValue().getDescription());
        verify(storage).saveTasks(taskList);
    }

    @Test
    void parseArgsToDeadlineTask_missingParts_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () ->
                DeadlineCommand.parseArgsToDeadlineTask("Submit report"));
    }

    @Test
    void parseArgsToDeadlineTask_invalidDate_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () ->
                DeadlineCommand.parseArgsToDeadlineTask("Submit report /by 32/13/2025 99:00"));
    }
}
