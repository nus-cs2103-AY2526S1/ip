package winnie.chatmessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import winnie.task.Todo;
import winnie.tasklist.TaskList;

public class FoundTasksMessageTest {
    private TaskList emptyTaskList;
    private TaskList taskListWithTasks;

    @BeforeEach
    public void setUp() {
        emptyTaskList = new TaskList();
        taskListWithTasks = new TaskList();
        taskListWithTasks.addTask(new Todo("read book"));
        taskListWithTasks.addTask(new Todo("return book"));
    }

    @Test
    public void getMessageContent_emptyTaskList_returnsNoMatchingMessage() {
        FoundTasksMessage message = new FoundTasksMessage(emptyTaskList);
        assertEquals("No matching tasks found.", message.getMessageContent());
    }

    @Test
    public void getMessageContent_taskListWithTasks_returnsFormattedTaskList() {
        FoundTasksMessage message = new FoundTasksMessage(taskListWithTasks);
        String content = message.getMessageContent();
        
        assertTrue(content.contains("Here are the matching tasks in your list:"));
        assertTrue(content.contains("1.[T][ ] read book"));
        assertTrue(content.contains("2.[T][ ] return book"));
    }

    @Test
    public void getMessageContent_singleTask_returnsFormattedSingleTask() {
        TaskList singleTaskList = new TaskList();
        singleTaskList.addTask(new Todo("single task"));
        
        FoundTasksMessage message = new FoundTasksMessage(singleTaskList);
        String content = message.getMessageContent();
        
        assertTrue(content.contains("Here are the matching tasks in your list:"));
        assertTrue(content.contains("1.[T][ ] single task"));
        assertFalse(content.contains("2."));
    }
}