package bugsbunny.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void getTasksDueBy_returnsOnlyDueTasks() {
        TaskList taskList = new TaskList();

        LocalDateTime cutoff = LocalDateTime.of(2025, 8, 30, 16, 0);

        taskList.addTask(new ToDo("just a todo")); // not due
        taskList.addTask(new Deadline("report", LocalDateTime.of(2025, 8, 30, 14, 0))); // due
        taskList.addTask(new Deadline("slides", LocalDateTime.of(2025, 8, 30, 18, 0))); // not due

        ArrayList<Task> dueTasks = taskList.getTasksDueBy(cutoff);

        assertEquals(1, dueTasks.size(), "Only one task should be due by 16:00");
        assertEquals("report", dueTasks.get(0).description, "The due task should be 'report'");
    }

    @Test
    void getTasksDueBy_emptyList_returnsEmpty() {
        TaskList taskList = new TaskList();

        LocalDateTime cutoff = LocalDateTime.of(2025, 8, 30, 16, 0);

        ArrayList<Task> dueTasks = taskList.getTasksDueBy(cutoff);

        assertTrue(dueTasks.isEmpty(), "Empty task list should return empty due tasks");
    }
}
