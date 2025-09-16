package tasks;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.RomidasException;

/*
 *  AI was able to assist in generating test cases for DeadlineTask using by submitting my previously completed 
 * TodoTaskTest.java file, information about the deadline task like the different methods and the fields as reference
 * 
 */

public class DeadlineTaskTest {
    private DeadlineTask deadlineTask;
    private DeadlineTask sameDeadlineTask;
    private DeadlineTask differentDeadlineTask;

    @BeforeEach
    public void setUp() {
        deadlineTask = new DeadlineTask("Submit assignment", "2023-12-25");
        sameDeadlineTask = new DeadlineTask("Submit assignment", "2023-12-25");
        differentDeadlineTask = new DeadlineTask("Submit assignment", "2023-12-26");
    }

    @Test
    public void testDeadlineTaskCreation() {
        assertEquals("Submit assignment", deadlineTask.getDescription());
        assertFalse(deadlineTask.isDone());
        assertEquals(LocalDate.parse("2023-12-25"), deadlineTask.getDeadline());
        assertEquals("[D]", deadlineTask.getStatus());
        assertEquals("[ ]", deadlineTask.getStatusIcon());
    }

    @Test
    public void testGetDeadline() {
        LocalDate expectedDeadline = LocalDate.parse("2023-12-25");
        assertEquals(expectedDeadline, deadlineTask.getDeadline());
    }

    @Test
    public void testToText_NotDone() {
        String expected = "D | 0 | Submit assignment (by: 2023-12-25)";
        assertEquals(expected, deadlineTask.toText());
    }

    @Test
    public void testToText_Done() {
        deadlineTask.setIsDone(true);
        String expected = "D | 1 | Submit assignment (by: 2023-12-25)";
        assertEquals(expected, deadlineTask.toText());
    }

    @Test
    public void testToString_NotDone() {
        String expected = "[D][ ] Submit assignment (by: 2023-12-25)";
        assertEquals(expected, deadlineTask.toString());
    }

    @Test
    public void testToString_Done() {
        deadlineTask.setIsDone(true);
        String expected = "[D][X] Submit assignment (by: 2023-12-25)";
        assertEquals(expected, deadlineTask.toString());
    }

    @Test
    public void testSetIsDone() {
        assertFalse(deadlineTask.isDone());
        deadlineTask.setIsDone(true);
        assertTrue(deadlineTask.isDone());
        assertEquals("[X]", deadlineTask.getStatusIcon());
    }

    @Test
    public void testToTask_ValidInput_NotDone() throws RomidasException {
        String[] parts = {"D", "0", "Complete project (by: 2023-11-15)"};
        Task task = DeadlineTask.toTask(parts);
        
        assertEquals("Complete project", task.getDescription());
        assertFalse(task.isDone());
        assertEquals("[D]", task.getStatus());
        assertTrue(task instanceof DeadlineTask);
        assertEquals(LocalDate.parse("2023-11-15"), ((DeadlineTask) task).getDeadline());
    }

    @Test
    public void testToTask_ValidInput_Done() throws RomidasException {
        String[] parts = {"D", "1", "Write report (by: 2023-10-30)"};
        Task task = DeadlineTask.toTask(parts);
        
        assertEquals("Write report", task.getDescription());
        assertTrue(task.isDone());
        assertEquals("[D]", task.getStatus());
        assertTrue(task instanceof DeadlineTask);
        assertEquals(LocalDate.parse("2023-10-30"), ((DeadlineTask) task).getDeadline());
    }

    @Test
    public void testToTask_InvalidNumberOfArguments_TooFew() {
        String[] parts = {"D", "0"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            DeadlineTask.toTask(parts);
        });
        
        assertEquals("Invalid number of arguments. Expected 3 but got 2", exception.getMessage());
    }

    @Test
    public void testToTask_InvalidNumberOfArguments_TooMany() {
        String[] parts = {"D", "0", "Task (by: 2023-12-25)", "Extra"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            DeadlineTask.toTask(parts);
        });
        
        assertEquals("Invalid number of arguments. Expected 3 but got 4", exception.getMessage());
    }

    @Test
    public void testToTask_InvalidDeadlineFormat_MissingByPrefix() {
        String[] parts = {"D", "0", "Task without deadline format"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            DeadlineTask.toTask(parts);
        });
        
        assertEquals("Invalid deadline format. Expected '(by: date)' but got: Task without deadline format", 
                exception.getMessage());
    }

    @Test
    public void testToTask_InvalidDeadlineFormat_MissingClosingParenthesis() {
        String[] parts = {"D", "0", "Task (by: 2023-12-25"};
        
        RomidasException exception = assertThrows(RomidasException.class, () -> {
            DeadlineTask.toTask(parts);
        });
        
        assertEquals("Invalid deadline format. Expected closing ')' after '(by: date)' in: Task (by: 2023-12-25", 
                exception.getMessage());
    }

    @Test
    public void testEquals_SameDescriptionAndDeadline() {
        assertTrue(deadlineTask.equals(sameDeadlineTask));
        assertTrue(sameDeadlineTask.equals(deadlineTask));
    }

    @Test
    public void testEquals_DifferentDeadline() {
        assertFalse(deadlineTask.equals(differentDeadlineTask));
        assertFalse(differentDeadlineTask.equals(deadlineTask));
    }

    @Test
    public void testEquals_DifferentType() {
        TodoTask todoTask = new TodoTask("Submit assignment");
        assertFalse(deadlineTask.equals(todoTask));
        assertFalse(todoTask.equals(deadlineTask));
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(deadlineTask.equals(deadlineTask));
    }

    @Test
    public void testEquals_NullObject() {
        assertFalse(deadlineTask.equals(null));
    }

    @Test
    public void testEquals_DifferentCompletionStatus() {
        deadlineTask.setIsDone(true);
        sameDeadlineTask.setIsDone(false);
        // Completion status should not affect equality
        assertTrue(deadlineTask.equals(sameDeadlineTask));
    }

    @Test
    public void testHashCode_SameDescriptionAndDeadline() {
        assertEquals(deadlineTask.hashCode(), sameDeadlineTask.hashCode());
    }

    @Test
    public void testHashCode_DifferentDeadline() {
        assertNotEquals(deadlineTask.hashCode(), differentDeadlineTask.hashCode());
    }

    @Test
    public void testToTask_EmptyDescription() throws RomidasException {
        String[] parts = {"D", "0", " (by: 2023-12-25)"};
        Task task = DeadlineTask.toTask(parts);
        
        assertEquals("", task.getDescription());
        assertFalse(task.isDone());
        assertEquals(LocalDate.parse("2023-12-25"), ((DeadlineTask) task).getDeadline());
    }

    @Test
    public void testToTask_DescriptionWithSpaces() throws RomidasException {
        String[] parts = {"D", "1", "Submit final project assignment (by: 2023-12-25)"};
        Task task = DeadlineTask.toTask(parts);
        
        assertEquals("Submit final project assignment", task.getDescription());
        assertTrue(task.isDone());
        assertEquals(LocalDate.parse("2023-12-25"), ((DeadlineTask) task).getDeadline());
    }
}

