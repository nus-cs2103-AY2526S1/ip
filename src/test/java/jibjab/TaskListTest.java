package jibjab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testAdd() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("task1"));
        taskList.addTask(new Deadline("task2", "01/01/2077 18:00"));
        taskList.addTask(new Event("task3", "01/01/2077 18:00",
                "01/02/2077 19:00"));

        assertEquals("[T][ ] task1\n[D][ ] task2 (by: Jan 01 2077 18:00)\n"
                + "[E][ ] task3 (from: Jan 01 2077 18:00 to: Feb 01 2077 19:00)", taskList.toString());
    }

    @Test
    public void testRemove() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("task1"));
        taskList.addTask(new Deadline("task2", "01/01/2077 18:00"));
        taskList.addTask(new Event("task3", "01/01/2077 18:00",
                "01/02/2077 19:00"));
        taskList.deleteTask(2);
        assertEquals("[T][ ] task1\n[D][ ] task2 (by: Jan 01 2077 18:00)", taskList.toString());

    }
}
