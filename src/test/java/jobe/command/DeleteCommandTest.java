package jobe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class DeleteCommandTest {
    
    private DeleteCommand cmd;
    private TaskListStub taskListStub;
    private UiStub uiStub;
    private StorageStub storageStub;
    
    @BeforeEach
    public void setUp() throws JobeException {
        cmd = new DeleteCommand("1");
        taskListStub = new TaskListStub();
        uiStub = new UiStub();
        storageStub = new StorageStub();
    }
    
    @Test
    public void testExecute_deletesTaskAndSaves() throws JobeException {
        cmd.execute(taskListStub, uiStub, storageStub);
        assertEquals("delete test passed", taskListStub.getLastTask().getTaskDescription());
        assertTrue(uiStub.isResponseCalled());
        assertTrue(storageStub.isTaskSaved());
    }
}
