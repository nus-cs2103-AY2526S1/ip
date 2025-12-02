package som;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import som.task.Deadline;
import som.task.Event;
import som.task.Task;
import som.task.Todo;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
    }

    @Test
    void findTasksOnDateReturnsTask() throws SomException {
        // Arrange
        Deadline d = new Deadline("Return book", "2025-06-11 1900");
        tasks.add(d);
        Event e = new Event("Meeting", "2025-06-11 0900", "2025-06-11 1100");
        tasks.add(e);

        // Act
        List<Task> matches = tasks.findTasks("2025-06-11");

        // Assert
        assertEquals(2, matches.size());
        assertEquals(d, matches.get(0));
        assertEquals(e, matches.get(1));
    }

    @Test
    void findTasksOnDateReturnsEmptyList() throws SomException {
        // Arrange
        tasks.add(new Todo("Buy groceries"));

        // Act
        List<Task> matches = tasks.findTasks("2025-01-01");

        // Assert
        assertTrue(matches.isEmpty());
    }

    @Test
    void findTasksOnDateInvalidDate() {
        // Act & Assert
        assertThrows(SomException.class, () ->
                tasks.findTasks("invalid-date")
        );
    }


}
