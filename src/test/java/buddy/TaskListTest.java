package buddy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList taskList;
    private Task todo;
    private Task deadline;
    
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        todo = new Todo("read book");
        deadline = new Deadline("return book", "2024-12-25");
    }
    
    @Test
    void addTask_singleTask_success() {
        taskList.addTask(todo);
        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.getTaskArray()[0]);
    }
    
    @Test
    void deleteTask_validIndex_success() throws BuddyException {
        taskList.addTask(todo);
        taskList.addTask(deadline);
        
        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
        assertEquals(deadline, taskList.getTaskArray()[0]);
    }
    
    @Test
    void deleteTask_invalidIndex_throwsException() {
        taskList.addTask(todo);
        
        assertThrows(BuddyException.class, () -> taskList.deleteTask(1));
        assertThrows(BuddyException.class, () -> taskList.deleteTask(-1));
    }
    
    @Test
    void markTask_validIndex_success() throws BuddyException {
        taskList.addTask(todo);
        assertFalse(todo.isDone());
        
        taskList.markTask(0);
        assertTrue(todo.isDone());
    }
}