package botchat.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void add_getTaskTest(){
        TaskList taskList = new TaskList();
        Task t = new Todo("borrow book");
        taskList.addTask(t);
        assertEquals(taskList.get(0),t);
    }

    @Test
    public void add_removeTaskTest(){
        TaskList taskList = new TaskList();
        Task t1 = new Todo("1");
        Task t2 = new Todo("2");
        taskList.addTask(t1);
        taskList.addTask(t2);
        Task deleted = taskList.deleteTask(0);
        assertEquals(t1,deleted);
    }

    @Test
    public void sizeTest(){
        TaskList taskList = new TaskList();
        Task t1 = new Todo("1");
        Task t2 = new Todo("2");
        taskList.addTask(t1);
        taskList.addTask(t2);
        assertEquals(2,taskList.size());
    }

    @Test
    public void getTasksTest(){
        TaskList taskList = new TaskList();
        Task t1 = new Todo("1");
        Task t2 = new Todo("2");
        taskList.addTask(t1);
        taskList.addTask(t2);
        assertEquals(new ArrayList<>(java.util.List.of(t1, t2)),taskList.getTasks());

    }


}
