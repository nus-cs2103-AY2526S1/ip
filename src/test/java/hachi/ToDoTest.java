package hachi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void createTodo_taskCreatedWithCorrectDescription() {
        ToDo todo = new ToDo("read book");
        assertEquals("read book", todo.description);
        assertFalse(todo.isCompleted); // default should be not done
    }

    @Test
    public void markTodo_taskMarkedAsDone() {
        ToDo todo = new ToDo("do laundry");
        todo.mark();
        assertTrue(todo.isCompleted);
    }

    @Test
    public void unmarkTodo_taskMarkedAsNotDone() {
        ToDo todo = new ToDo("do laundry");
        todo.mark();
        todo.unmark();
        assertFalse(todo.isCompleted);
    }

    @Test
    public void toString_notDone_correctFormat() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void toString_done_correctFormat() {
        ToDo todo = new ToDo("read book");
        todo.mark();
        assertEquals("[T][X] read book", todo.toString());
    }
}
