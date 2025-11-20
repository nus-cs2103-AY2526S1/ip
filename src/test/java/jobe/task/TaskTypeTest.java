package jobe.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class TaskTypeTest {
    
    @Test
    public void stringToCommand_validCommand_returnsCommandType() throws JobeException {
        TaskType taskType = TaskType.stringToCommand("T");
        assertEquals(TaskType.T, taskType);
    }
    
    @Test
    public void stringToCommand_invalidCommand_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> TaskType.stringToCommand("H")
        );
        assertEquals("OOPS!!!! There is an error with the TaskType of this input", exception.getMessage());
    }
}

