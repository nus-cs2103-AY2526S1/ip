package vicky.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testTodoConstructorAndToString() {
        Todo todo = new Todo("Buy milk");
        assertEquals("Buy milk", todo.name);
        assertEquals("[T] [ ] Buy milk", todo.toString());
        Todo doneTodo = new Todo("Buy milk", true);
        assertEquals("[T] [X] Buy milk", doneTodo.toString());
    }

    @Test
    public void testToStorageString() {
        Todo todo = new Todo("Buy milk");
        assertEquals("Todo | 1 | Buy milk", todo.toStorageString());
        Todo doneTodo = new Todo("Buy milk", true);
        assertEquals("Todo | 0 | Buy milk", doneTodo.toStorageString());
    }

}
