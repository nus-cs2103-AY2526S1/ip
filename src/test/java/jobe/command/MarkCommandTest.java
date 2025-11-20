package jobe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class MarkCommandTest {
    
    private MarkCommand cmd;
    private TaskListStub taskListStub;
    private UiStub uiStub;
    private StorageStub storageStub;
    
    @BeforeEach
    public void setUp() throws JobeException {
        cmd = new MarkCommand("1");
        taskListStub = new TaskListStub();
        uiStub = new UiStub();
        storageStub = new StorageStub();
    }
    
    @Test
    public void testExecute_marksTaskAndSaves() throws JobeException {
        cmd.execute(taskListStub, uiStub, storageStub);
        assertEquals("mark test passed", taskListStub.getLastTask().getTaskDescription());
        assertTrue(uiStub.isResponseCalled());
        assertTrue(storageStub.isTaskSaved());
    }
}
