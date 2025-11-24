package dino;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {

    @Test
    public void testMarkAsDoneTodo() {
        Todo todo = new Todo("Homework");
        assertFalse(todo.isDone());
        todo.markAsDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void testMarkAsDoneDeadline() {
        Deadline deadline = new Deadline("Quiz", "2025-09-09");
        assertFalse(deadline.isDone());
        deadline.markAsDone();
        assertTrue(deadline.isDone());
    }

    @Test
    public void testMarkAsDoneEvent() {
        Event event = new Event("Zoom Meeting", "3pm", "4pm");
        assertFalse(event.isDone());
        event.markAsDone();
        assertTrue(event.isDone());
    }
}
