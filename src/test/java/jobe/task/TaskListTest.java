package jobe.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class TaskListTest {
    
    private TaskList taskList;
    
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }
    
    @Test
    public void testAddTodoTask() throws JobeException {
        taskList.createTodoTask("Read book");
        assertEquals(1, taskList.size());
    }
    
    @Test
    public void testAddDeadlineTask() throws JobeException {
        taskList.createDeadlineTask("Submit report", "by 24/08/2024 2222");
        assertEquals(1, taskList.size());
    }
    
    @Test
    public void testAddEventTask() throws JobeException {
        taskList.createEventTask("Meeting",
                "at 24/08/2024 1000",
                "at 24/08/2024 1200"
        );
        assertEquals(1, taskList.size());
    }
    
    @Test
    public void testMarkTask() throws JobeException {
        taskList.createTodoTask("Read book");
        Task marked = taskList.markTask(0);
        assertTrue(marked.getStatus());
    }
    
    @Test
    public void testUnmarkTask() throws JobeException {
        taskList.createTodoTask("Read book");
        taskList.markTask(0);
        Task unmarked = taskList.unmarkTask(0);
        assertFalse(unmarked.getStatus());
    }
    
    @Test
    public void testMarkTask_invalidIndex_throwsException() {
        JobeException e = assertThrows(JobeException.class, () -> taskList.markTask(100));
        assertTrue(e.getMessage().contains("OOPS!!!! There are tasks which match the input index in your list!"));
    }
    
    @Test
    public void testUnmarkTask_invalidIndex_throwsException() {
        JobeException e = assertThrows(JobeException.class, () -> taskList.unmarkTask(100));
        assertTrue(e.getMessage().contains("OOPS!!!! There are tasks which match the input index in your list!"));
    }
    
    @Test
    public void testDeleteTask() throws JobeException {
        taskList.createTodoTask("Read book");
        taskList.deleteTask(0);
        assertEquals(0, taskList.size());
    }
    
    @Test
    public void testDeleteTask_invalidIndex_throwsException() {
        JobeException e = assertThrows(JobeException.class, () -> taskList.deleteTask(100));
        assertTrue(e.getMessage().contains("OOPS!!!! There are tasks which match the input index in your list!"));
    }
    
    @Test
    public void testFindTask() throws JobeException {
        taskList.createTodoTask("Read book");
        taskList.createTodoTask("Write code");
        List<Task> found = taskList.findTask("Read");
        assertEquals(1, found.size());
    }
    
    @Test
    public void testFindTask_invalidKeyword_throwsException() throws JobeException {
        taskList.createTodoTask("Read book");
        taskList.createTodoTask("Write code");
        JobeException e = assertThrows(JobeException.class, () -> taskList.findTask("hello"));
        assertTrue(e.getMessage().contains("OOPS!!!! There are no matching tasks in your list!"));
    }
    
    @Test
    public void testConvertToFileFormat() throws JobeException {
        taskList.createTodoTask("Read book");
        taskList.createTodoTask("Write code");
        String fileFormat = taskList.convertToFileFormat();
        assertTrue(fileFormat.contains("Read book"));
        assertTrue(fileFormat.contains("Write code"));
    }
}
