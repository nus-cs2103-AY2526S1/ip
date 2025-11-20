package jobe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;
import jobe.task.Task;
import jobe.task.TaskList;

public class ListCommandTest {
    
    private ListCommand cmd;
    private TaskList taskListStub;
    private UiStub uiStub;
    private StorageStub storageStub;
    
    @BeforeEach
    public void setUp() {
        cmd = new ListCommand();
        taskListStub = new TaskListStub();
        uiStub = new UiStub();
        storageStub = new StorageStub();
    }
    
    @Test
    public void testExecute_nonEmptyTaskList() throws JobeException {
        taskListStub.addTask(new Task("read book"));
        cmd.execute(taskListStub, uiStub, storageStub);
        assertTrue(uiStub.isResponseCalled());
    }
    
    @Test
    public void testExecute_emptyTaskList_throwsException() {
        JobeException exception =
                assertThrows(JobeException.class, () -> cmd.execute(taskListStub, uiStub, storageStub));
        assertEquals("OOPS!!!! You have nothing in your list!", exception.getMessage());
        assertFalse(uiStub.isResponseCalled());
    }
}
