package darren.assistant.tasks;

import jarvis.tasks.Task;
import jarvis.tasks.ToDoTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTaskTest {
    @Test
    @DisplayName("New ToDo Task that is unmarked by default")
    public void ToDoTestUnmarkedDefault() {
        Task t = new ToDoTask("First test");
        assertFalse(t.isDone());
        assertEquals(t.toString(), "[T][ ] First test");
    }

    @Test
    @DisplayName("New ToDo task that is marked as done")
    public void ToDoTestMarked() {
        Task t = new ToDoTask("First test");
        t.markAsDone();
        assertTrue(t.isDone());
        assertEquals(t.toString(), "[T][X] First test");
    }

    @Test
    @DisplayName("New ToDo task that is marked as undone")
    public void ToDoTestUnmarked() {
        Task t = new ToDoTask("First test");
        t.markAsDone();
        t.markAsNotDone();
        assertFalse(t.isDone());
        assertEquals(t.toString(), "[T][ ] First test");
    }
}
