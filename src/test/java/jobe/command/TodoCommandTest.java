package jobe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class TodoCommandTest {
    
    private TodoCommand cmd;
    private TaskListStub taskListStub;
    private UiStub uiStub;
    private StorageStub storageStub;
    
    @BeforeEach
    public void setUp() throws JobeException {
        cmd = new TodoCommand("test");
        taskListStub = new TaskListStub();
        uiStub = new UiStub();
        storageStub = new StorageStub();
    }
    
    @Test
    public void testExecute_createsTaskAndSaves() throws JobeException {
        cmd.execute(taskListStub, uiStub, storageStub);
        assertEquals("todo test passed", taskListStub.getLastTask().getTaskDescription());
        assertTrue(uiStub.isResponseCalled());
        assertTrue(storageStub.isTaskSaved());
    }
    
    @Test
    public void createTodoCommand_missingArgs_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> new TodoCommand("")
        );
        assertEquals(
                "OOPS!!!! The description of a todo task cannot be empty!",
                exception.getMessage()
        );
    }
}
