package darren.assistant.tasks;

import jarvis.tasks.DeadlinesTask;
import jarvis.tasks.Task;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlinesTaskTest {
    @Test
    @DisplayName("Default Deadlines Task Test")
    public void DeadlinesTaskDefault() {
        Task d = new DeadlinesTask("Task1", LocalDateTime.parse("2025-08-30T00:00"));
        assertFalse(d.isDone());
        assertEquals(d.toString(), "[D][ ] Task1 (by: Aug 30 2025, 12:00 AM)");
    }

    @Test
    @DisplayName("Deadlines Task unmarked")
    public void DeadlinesTaskUnmarked() {
        Task d = new DeadlinesTask("Task1", LocalDateTime.parse("2025-08-30T00:00"));
        d.markAsDone();
        d.markAsNotDone();
        assertFalse(d.isDone());
        assertEquals(d.toString(), "[D][ ] Task1 (by: Aug 30 2025, 12:00 AM)");
    }

    @Test
    @DisplayName("Deadlines Task marked")
    public void DeadlinesTaskMarked() {
        Task d = new DeadlinesTask("Task1", LocalDateTime.parse("2025-08-30T00:00"));
        d.markAsDone();
        assertTrue(d.isDone());
        assertEquals(d.toString(), "[D][X] Task1 (by: Aug 30 2025, 12:00 AM)");
    }
}
