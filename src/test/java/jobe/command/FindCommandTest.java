package jobe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class FindCommandTest {
    
    private TaskListStub taskListStub;
    private UiStub uiStub;
    private StorageStub storageStub;
    
    @BeforeEach
    public void setUp() {
        taskListStub = new TaskListStub();
        uiStub = new UiStub();
        storageStub = new StorageStub();
    }
    
    @Test
    public void testExecute_findsTaskAndSaves() throws JobeException {
        FindCommand cmd = new FindCommand("book");
        
        cmd.execute(taskListStub, uiStub, storageStub);
        assertEquals("find test passed", taskListStub.getLastTask().getTaskDescription());
        assertTrue(uiStub.isResponseCalled());
    }
    
    @Test
    public void testFindCommand_missingInput_throwsException() {
        JobeException exception = assertThrows(JobeException.class, () -> new FindCommand(""));
        assertEquals("OOPS!!!! You forgot to enter your keyword!", exception.getMessage());
    }
}
