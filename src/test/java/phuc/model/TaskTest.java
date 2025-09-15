package phuc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testToDoTaskCreation() {
        ToDoTask task = new ToDoTask("Buy groceries");
        assertEquals("Buy groceries", task.getDescription());
        assertFalse(task.isDone);
        assertEquals(LocalDateTime.MAX, task.getSortDateTime());
    }

    @Test
    void testDeadlineTaskCreation() {
        LocalDateTime deadline = LocalDateTime.of(2024, 12, 25, 23, 59);
        DeadlineTask task = new DeadlineTask("Submit report", deadline);
        assertEquals("Submit report", task.getDescription());
        assertEquals(deadline, task.getSortDateTime());
    }

    @Test
    void testEventTaskCreation() {
        LocalDateTime start = LocalDateTime.of(2024, 12, 25, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 12, 25, 12, 0);
        EventTask task = new EventTask("Team meeting", start, end);
        assertEquals("Team meeting", task.getDescription());
        assertEquals(end, task.getSortDateTime());
    }

    @Test
    void testTaskMarking() {
        ToDoTask task = new ToDoTask("Test task");
        assertFalse(task.isDone);

        task.setDone();
        assertTrue(task.isDone);

        task.setNotDone();
        assertFalse(task.isDone);
    }

    @Test
    void testTaskToString() {
        ToDoTask todo = new ToDoTask("Test todo");
        assertEquals("[T][   ] Test todo", todo.toString());

        todo.setDone();
        assertEquals("[T][X] Test todo", todo.toString());
    }
}
