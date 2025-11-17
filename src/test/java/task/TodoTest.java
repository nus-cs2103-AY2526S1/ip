package task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void createTodo() {
        assertDoesNotThrow(() -> {
            Todo todo = new Todo("Test task");
        });
    }

    @Test
    public void loadTodo() {
        assertDoesNotThrow(() -> {
            Todo todo = new Todo("Test task", true, new ArrayList<String>());
            Todo todo2 = new Todo("Test task", false, new ArrayList<String>());
        });
    }

    @Test
    public void toStringTodo_unmarkedTodo() {
        Todo todo = new Todo("Test task", false, new ArrayList<String>());
        assertEquals("[T][ ] Test task ", todo.toString());
    }

    @Test
    public void toStringTodo_markedTodo() {
        Todo todo = new Todo("Test task", true, new ArrayList<String>());
        assertEquals("[T][X] Test task ", todo.toString());
    }


}
