package angus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ToDoTest {
    @Test
    public void testGetDescription() {
        assertEquals("Test description", new ToDo("Test description").getDescription());
    }
    @Test
    public void getCompleteStatus_incompleteToDo_success() {
        ToDo toDo = new ToDo("Test");
        assertEquals("0", toDo.getCompleteStatus());
    }
    @Test
    public void getCompleteStatus_completeToDo_success() {
        ToDo toDo = new ToDo("Test");
        toDo.markDone();
        assertEquals("1", toDo.getCompleteStatus());
    }
    @Test
    public void getStatusIcon_incompleteToDo_success() {
        ToDo toDo = new ToDo("Test");
        assertEquals("[ ]", toDo.getStatusIcon());
    }
    @Test
    public void getStatusIcon_completeToDo_success() {
        ToDo toDo = new ToDo("Test");
        toDo.markDone();
        assertEquals("[X]", toDo.getStatusIcon());
    }
    @Test
    public void getStatusIcon_unmarkingCompletedTask_success() {
        ToDo toDo = new ToDo("Test");
        toDo.markDone();
        assertEquals("[X]", toDo.getStatusIcon());
        toDo.markNotDone();
        assertEquals("[ ]", toDo.getStatusIcon());
    }
    @Test
    public void testStringConversion() {
        ToDo toDo = new ToDo("Test");
        assertEquals("[T][ ] Test", toDo.toString());
        toDo.markDone();
        assertEquals("[T][X] Test", toDo.toString());
    }
}
