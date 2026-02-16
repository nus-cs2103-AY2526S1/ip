package jooh;

import org.junit.jupiter.api.Test;
import jooh.task.TaskList;
import jooh.task.Todo;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void taskList_addTask_increasesSize() {
        TaskList list = new TaskList();
        list.addTask(new Todo("read book", false));
        assertEquals(1, list.getSize());
    }
}