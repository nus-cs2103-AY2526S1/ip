package LunarBot;

import LunarBot.Tasks.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void generalTaskListTest() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());

        taskList.add(new Task("test", false));

        assertEquals(1, taskList.size());
        assertEquals("X,false,test", taskList.get(0).getAsCsv());

        taskList.delete(0);

        assertEquals(0, taskList.size());
    }

}
