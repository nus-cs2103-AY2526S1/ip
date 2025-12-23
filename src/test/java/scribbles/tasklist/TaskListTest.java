package scribbles.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void taskListSize_emptyTaskList_zeroReturned() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }
}
