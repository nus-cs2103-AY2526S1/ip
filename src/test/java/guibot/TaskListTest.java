import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import guibot.TaskList;
import guibot.task.Todo;

public class TaskListTest {
    @Test
    public void testSize() {
        TaskList t = new TaskList();
        assertEquals(0, t.size());

        t.add(Todo.of("hi"));
        assertEquals(1, t.size());
    }

    @Test
    public void mark_taskNotFound_exceptionThrown() {
        try {
            assertEquals(0, new TaskList().mark(1));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("The index specified does not correspond to a task in the list. Please try again.",
                    e.getMessage());
        }
    }

    @Test
    public void testStringConversion() {
        TaskList t = new TaskList();
        assertEquals("", t.toString());

        t.add(Todo.of("something"));
        assertEquals("\n1.[T][ ] something", t.toString());
    }
}
