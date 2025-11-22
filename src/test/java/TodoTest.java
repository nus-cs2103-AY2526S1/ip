import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import task.Todo;

/**
 * Tests for the {@link Todo} task.
 */
public class TodoTest {

    /**
     * Tests that {@link Todo#toString()} returns the expected
     * formatted string representation of a todo.
     */
    @Test
    public void todo_toString_correctFormat() {
        Todo t = new Todo("Finish homework");
        assertEquals("[T][ ] Finish homework", t.toString());
    }

    /**
     * Tests that marking and unmarking a {@link Todo}
     * correctly changes its done status.
     */
    @Test
    public void todo_markAndUnmark_changesStatus() {
        Todo t = new Todo("Finish homework");
        t.markAsDone();
        assertTrue(t.isDone());
        t.markAsNotDone();
        assertTrue(!t.isDone());
    }

}
