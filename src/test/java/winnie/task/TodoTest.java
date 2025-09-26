package winnie.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    private Todo todo;

    @BeforeEach
    public void setUp() {
        todo = new Todo("Buy groceries");
    }

    @Test
    public void constructor_validDescription_createsTodoTask() {
        assertEquals("Buy groceries", todo.getDescription());
        assertEquals(TaskEnum.TODO, todo.getTaskType());
        assertFalse(todo.isDone());
    }

    @Test
    public void getDescription_afterCreation_returnsCorrectDescription() {
        assertEquals("Buy groceries", todo.getDescription());
    }

    @Test
    public void isDone_initialState_returnsFalse() {
        assertFalse(todo.isDone());
    }

    @Test
    public void markAsDone_afterMarking_returnsTrueForIsDone() {
        todo.markAsDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void markAsNotDone_afterUnmarking_returnsFalseForIsDone() {
        todo.markAsDone();
        todo.markAsNotDone();
        assertFalse(todo.isDone());
    }

    @Test
    public void getStatusIcon_notDone_returnsEmptyCheckbox() {
        assertEquals("[ ]", todo.getStatusIcon());
    }

    @Test
    public void getStatusIcon_done_returnsCheckedBox() {
        todo.markAsDone();
        assertEquals("[X]", todo.getStatusIcon());
    }

    @Test
    public void getTypeIcon_todoTask_returnsCorrectIcon() {
        assertEquals("[T]", todo.getTypeIcon());
    }

    @Test
    public void toString_notDone_returnsCorrectFormat() {
        String expected = "[T][ ] Buy groceries";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void toString_done_returnsCorrectFormat() {
        todo.markAsDone();
        String expected = "[T][X] Buy groceries";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void getTaskType_todoTask_returnsTodoType() {
        assertEquals(TaskEnum.TODO, todo.getTaskType());
    }
}
