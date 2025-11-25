package hachiware;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class ToDoTest {

    @Test
    void markAsDoneAndUnmark() {
        ToDo todo = new ToDo("Buy groceries");
        assertFalse(todo.isDone, "ToDo should not be done by default");

        todo.markAsDone();
        assertTrue(todo.isDone, "ToDo should be marked done");

        todo.markAsNotDone();
        assertFalse(todo.isDone, "ToDo should be marked not done");
    }

    @Test
    void toStringContainsStatusAndDescription() {
        ToDo todo = new ToDo("Read book");
        String str = todo.toString();
        assertTrue(str.contains("Read book"), "toString should contain task description");
        assertTrue(str.contains("[ ]") || str.contains("[X]"), "toString should show done status");
    }

    @Test
    void markDoneReflectsInToString() {
        ToDo todo = new ToDo("Exercise");
        todo.markAsDone();
        String str = todo.toString();
        assertTrue(str.contains("[X]"), "toString should reflect task marked as done");
    }
}
