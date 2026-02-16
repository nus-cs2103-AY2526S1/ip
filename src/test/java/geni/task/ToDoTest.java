package geni.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testGetStatusIcon() {
        Todo todo1 = new Todo("Finish assignment");
        assertEquals(" ", todo1.getStatusIcon());
        // You can mark done and test again
        todo1.markAsDone();
        assertEquals("X", todo1.getStatusIcon());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Finish assignment");
        todo.markAsDone();
        String expected = "T | 1 | Finish assignment";
        assertEquals(expected, todo.toSaveFormat());
    }

    @Test
    public void testToSaveFormat_whenDone() {
        Todo todo = new Todo("Finish assignment");
        todo.markAsDone();
        todo.markAsUndone();
        String expected = "T | 0 | Finish assignment";
        assertEquals(expected, todo.toSaveFormat());
    }

    @Test
    public void testToString_formatting() {
        Todo todo = new Todo("Read book");
        String expected = "[T][ ] Read book"; // status icon blank initially
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testGetDescription() {
        Todo todo = new Todo("Practice coding");
        assertEquals("Practice coding", todo.getDescription());
    }


}
