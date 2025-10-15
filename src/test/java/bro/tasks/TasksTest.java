package bro.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TasksTest {
    @Test
    public void addTask_validTodo_success() {
        Tasks tasks = new Tasks();
        Todo todo = new Todo("testTodo");
        String msg = tasks.addTask(todo);

        assertTrue(msg.contains("Got it bro! I've added this task:"));
        assertTrue(msg.contains("testTodo"));
        assertEquals(1, tasks.getSize());
        assertTrue(tasks.getEntry(0) instanceof Todo);
        assertEquals("testTodo", tasks.getEntry(0).getDescription());
    }

    @Test
    public void addTask_validDeadline_success() {
        Tasks tasks = new Tasks();
        Deadline deadline = new Deadline("testDeadline", "2/12/2019 0000");
        String msg = tasks.addTask(deadline);

        assertTrue(msg.contains("Got it bro! I've added this task:"));
        assertTrue(msg.contains("testDeadline"));
        assertEquals(1, tasks.getSize());
        assertTrue(tasks.getEntry(0) instanceof Deadline);
        assertEquals("testDeadline", tasks.getEntry(0).getDescription());
    }

    @Test
    public void addTask_validEvent_success() {
        Tasks tasks = new Tasks();
        Event event = new Event("testEvent", "2/12/2019 0000", "2/12/2019 1000");
        String msg = tasks.addTask(event);

        assertTrue(msg.contains("Got it bro! I've added this task:"));
        assertTrue(msg.contains("testEvent"));
        assertEquals(1, tasks.getSize());
        assertTrue(tasks.getEntry(0) instanceof Event);
        assertEquals("testEvent", tasks.getEntry(0).getDescription());
    }

    @Test
    public void deleteTask_validTodo_success() {
        Tasks tasks = new Tasks();
        Todo todo = new Todo("testTodo");
        tasks.addTask(todo);
        String msg = tasks.deleteTask(0);

        assertTrue(msg.contains("Sure bro! I've removed this task:"),
                "Message should contain 'Noted. I've removed this task:'");
        assertTrue(msg.contains("testTodo"));
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void deleteTask_validDeadline_success() {
        Tasks tasks = new Tasks();
        Deadline deadline = new Deadline("testDeadline", "2/12/2019 0000");
        tasks.addTask(deadline);
        String msg = tasks.deleteTask(0);

        assertTrue(msg.contains("Sure bro! I've removed this task:"));
        assertTrue(msg.contains("testDeadline"));
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void deleteTask_validEvent_success() {
        Tasks tasks = new Tasks();
        Event event = new Event("testEvent", "2/12/2019 0000", "2/12/2019 1000");
        tasks.addTask(event);
        String msg = tasks.deleteTask(0);

        assertTrue(msg.contains("Sure bro! I've removed this task:"));
        assertTrue(msg.contains("testEvent"));
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void getSize_emptyTasks_returnsZero() {
        Tasks tasks = new Tasks();
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void getSize_nonEmptyTasks_returnsCorrectSize() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("A"));
        taskList.add(new Deadline("B", "1/1/2020 1200"));
        Tasks tasks = new Tasks(taskList);
        assertEquals(2, tasks.getSize());
    }

    @Test
    public void getEntry_validIndex_returnsTask() {
        Tasks tasks = new Tasks();
        tasks.addTask(new Todo("Test"));
        assertTrue(tasks.getEntry(0) instanceof Todo);
        assertEquals("Test", tasks.getEntry(0).getDescription());
    }

    @Test
    public void addTask_returnsCorrectMessage() {
        Tasks tasks = new Tasks();
        String msg = tasks.addTask(new Todo("TestMsg"));
        assertTrue(msg.contains("Got it bro! I've added this task:"));
        assertTrue(msg.contains("TestMsg"));
    }

    @Test
    public void deleteTask_returnsCorrectMessage() {
        Tasks tasks = new Tasks();
        tasks.addTask(new Todo("DeleteMe"));
        String msg = tasks.deleteTask(0);
        assertTrue(msg.contains("Sure bro! I've removed this task:"));
        assertTrue(msg.contains("DeleteMe"));
    }

    @Test
    public void toString_multipleTasks_correctFormat() {
        Tasks tasks = new Tasks();
        tasks.addTask(new Todo("A"));
        tasks.addTask(new Deadline("B", "1/1/2020 1200"));
        String output = tasks.toString();
        assertTrue(output.startsWith("1. "));
        assertTrue(output.contains("2. "));
        assertTrue(output.contains("A"));
        assertTrue(output.contains("B"));
    }

    @Test
    public void getEntry_invalidIndex_throwsException() {
        Tasks tasks = new Tasks();
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.getEntry(0));
    }

    @Test
    public void deleteTask_invalidIndex_throwsException() {
        Tasks tasks = new Tasks();
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.deleteTask(0));
    }

    @Test
    public void addTask_multipleTypes_success() {
        Tasks tasks = new Tasks();
        tasks.addTask(new Todo("T1"));
        tasks.addTask(new Deadline("D1", "1/1/2020 1200"));
        tasks.addTask(new Event("E1", "1/1/2020 1200", "1/1/2020 1300"));
        assertEquals(3, tasks.getSize());
        assertTrue(tasks.getEntry(0) instanceof Todo);
        assertTrue(tasks.getEntry(1) instanceof Deadline);
        assertTrue(tasks.getEntry(2) instanceof Event);
    }

    @Test
    public void toString_emptyTasks_returnsEmptyString() {
        Tasks tasks = new Tasks();
        assertEquals("", tasks.toString());
    }
}
