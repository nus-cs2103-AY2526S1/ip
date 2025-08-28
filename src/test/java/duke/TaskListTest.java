package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void constructor_emptyList_sizeIsZero(){
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void add_oneTask_sizeIsOne(){
        TaskList taskList = new TaskList();
        Task task = new Todo("test task");
        taskList.add(task);
        assertEquals(1, taskList.size());
    }
}