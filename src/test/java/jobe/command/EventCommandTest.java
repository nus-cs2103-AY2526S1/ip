package jobe.command;

import static jobe.command.DeadlineCommand.createDeadlineCommand;
import static jobe.command.EventCommand.createEventCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class EventCommandTest {
    
    private EventCommand cmd;
    private TaskListStub taskListStub;
    private UiStub uiStub;
    private StorageStub storageStub;
    
    @BeforeEach
    public void setUp() throws JobeException {
        cmd = new EventCommand("test", "test", "test");
        taskListStub = new TaskListStub();
        uiStub = new UiStub();
        storageStub = new StorageStub();
    }
    
    @Test
    public void testExecute_createsTaskAndSaves() throws JobeException {
        cmd.execute(taskListStub, uiStub, storageStub);
        assertEquals("event test passed", taskListStub.getLastTask().getTaskDescription());
        assertTrue(uiStub.isResponseCalled());
        assertTrue(storageStub.isTaskSaved());
    }
    
    @Test
    public void createDeadlineCommand_validArgs_returnsDeadlineCommand() throws JobeException {
        DeadlineCommand cmd = createDeadlineCommand("Submit report /2025-09-20");
        assertInstanceOf(DeadlineCommand.class, cmd);
    }
    
    @Test
    public void createEventCommand_missingArgs_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> createEventCommand("")
        );
        assertEquals(
                "OOPS!!!! The description of an event task cannot be empty!",
                exception.getMessage()
        );
    }
    
    @Test
    public void createDeadlineCommand_missingStartDate_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> createEventCommand("test")
        );
        assertEquals(
                "OOPS!!!! You forgot to specify the START date/time!",
                exception.getMessage()
        );
    }
    
    @Test
    public void createDeadlineCommand_missingEndDate_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> createEventCommand("test /from test")
        );
        assertEquals(
                "OOPS!!!! You forgot to specify the END date/time!",
                exception.getMessage()
        );
    }
    
    @Test
    public void createDeadlineCommand_tooManyArgs_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> createEventCommand("test /from test /to test /to test")
        );
        assertTrue(exception.getMessage().contains("OOPS!!!! You have entered additional parameters!"));
    }
}
