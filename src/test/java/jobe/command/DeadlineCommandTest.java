package jobe.command;

import static jobe.command.DeadlineCommand.createDeadlineCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class DeadlineCommandTest {
    
    private DeadlineCommand cmd;
    private TaskListStub taskListStub;
    private UiStub uiStub;
    private StorageStub storageStub;
    
    @BeforeEach
    public void setUp() throws JobeException {
        cmd = new DeadlineCommand("test", "test");
        taskListStub = new TaskListStub();
        uiStub = new UiStub();
        storageStub = new StorageStub();
    }
    
    @Test
    public void testExecute_createsTaskAndSaves() throws JobeException {
        cmd.execute(taskListStub, uiStub, storageStub);
        assertEquals("deadline test passed", taskListStub.getLastTask().getTaskDescription());
        assertTrue(uiStub.isResponseCalled());
        assertTrue(storageStub.isTaskSaved());
    }
    
    @Test
    public void createDeadlineCommand_validArgs_returnsDeadlineCommand() throws JobeException {
        DeadlineCommand cmd = createDeadlineCommand("Submit report /2025-09-20");
        assertInstanceOf(DeadlineCommand.class, cmd);
    }
    
    @Test
    public void createDeadlineCommand_missingArgs_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> createDeadlineCommand("")
        );
        assertEquals(
                "OOPS!!!! The description of a deadline task cannot be empty!",
                exception.getMessage()
        );
    }
    
    @Test
    public void createDeadlineCommand_missingDeadline_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> createDeadlineCommand("test")
        );
        assertEquals(
                "OOPS!!!! You forgot to specify the deadline!",
                exception.getMessage()
        );
    }
    
    @Test
    public void createDeadlineCommand_tooManyArgs_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> createDeadlineCommand("test /by test /by test /by test")
        );
        assertTrue(exception.getMessage().contains("OOPS!!!! You have entered additional parameters!"));
    }
}
