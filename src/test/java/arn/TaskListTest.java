package arn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void testAddAndRemoveTask() throws ArnException {
        TaskList taskList = new TaskList(new ArrayList<>());

        Todo todo = new Todo("gym");
        taskList.add(todo);

        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.get(0));

        Task removed = taskList.remove(0);
        assertEquals(todo, removed);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testRemoveFromEmptyList() {
        TaskList taskList = new TaskList(new ArrayList<>());

        assertThrows(ArnException.class, () -> {taskList.remove(0);});
    }
}

