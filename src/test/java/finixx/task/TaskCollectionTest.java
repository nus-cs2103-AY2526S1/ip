package finixx.task;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the TaskCollection class.
 */
public class TaskCollectionTest {
    @Test
    public void testAddTask() {
        TaskCollection tasks = new TaskCollection();
        TodoTask t1 = new TodoTask("join sports club", true, "");
        tasks.addTask(t1);

        List<String> actual = tasks.getAllTasks().stream()
                .map(Task::toString)
                .toList();
        List<String> expected = List.of("[T][X] join sports club");

        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteTask() {
        TaskCollection tasks = new TaskCollection();
        TodoTask t1 = new TodoTask("join sports club", true, "");
        TodoTask t2 = new TodoTask("read book", false, "Harry Potter");
        tasks.addTask(t1);
        tasks.addTask(t2);

        tasks.removeTask(0);

        List<String> actual = tasks.getAllTasks().stream()
                .map(Task::toString)
                .toList();
        List<String> expected = List.of("[T][ ] read book <Harry Potter>");

        assertEquals(expected, actual);
    }

    @Test
    public void testAddDifferentTaskTypes() {
        TaskCollection tasks = new TaskCollection();
        tasks.addTask(new TodoTask("exercise", false, ""));
        tasks.addTask(new DeadlineTask("submit report", false, "", "2025-12-01"));
        tasks.addTask(new EventTask("meeting", false, "office", "2pm", "4pm"));

        List<String> actual = tasks.getAllTasks().stream()
                .map(Task::toString)
                .toList();

        List<String> expected = List.of(
                "[T][ ] exercise",
                "[D][ ] submit report (by: Dec 1 2025)",
                "[E][ ] meeting (from: 2pm to: 4pm) <office>"
        );

        assertEquals(expected, actual);
        assertEquals(3, tasks.getSize());
    }

    @Test
    public void testFindTasks() {
        TaskCollection tasks = new TaskCollection();
        tasks.addTask(new TodoTask("exercise", false, ""));
        tasks.addTask(new DeadlineTask("submit report", false, "", "2025-12-01"));
        tasks.addTask(new EventTask("meeting", false, "office", "2pm", "4pm"));
        tasks.addTask(new TodoTask("read book", false, "Harry Potter"));

        List<String> actual = tasks.findTasks("e").stream()
                .map(Task::toString)
                .toList();

        List<String> expected = List.of(
                "[T][ ] exercise",
                "[D][ ] submit report (by: Dec 1 2025)",
                "[E][ ] meeting (from: 2pm to: 4pm) <office>",
                "[T][ ] read book <Harry Potter>"
        );

        assertEquals(expected, actual);
    }

    @Test
    public void testGetTask() {
        TaskCollection tasks = new TaskCollection();
        TodoTask t1 = new TodoTask("join sports club", true, "");
        TodoTask t2 = new TodoTask("read book", false, "Harry Potter");
        tasks.addTask(t1);
        tasks.addTask(t2);

        Task retrievedTask = tasks.getTask(1);

        assertEquals("[T][ ] read book <Harry Potter>", retrievedTask.toString());
    }

    @Test
    public void testGetSize() {
        TaskCollection tasks = new TaskCollection();
        tasks.addTask(new TodoTask("exercise", false, ""));
        tasks.addTask(new DeadlineTask("submit report", false, "", "2025-12-01"));

        assertEquals(2, tasks.getSize());
    }

    @Test
    public void testGetAllTasks() {
        TaskCollection tasks = new TaskCollection();
        TodoTask t1 = new TodoTask("join sports club", true, "");
        TodoTask t2 = new TodoTask("read book", false, "Harry Potter");
        tasks.addTask(t1);
        tasks.addTask(t2);

        List<String> actual = tasks.getAllTasks().stream()
                .map(Task::toString)
                .toList();
        List<String> expected = List.of(
                "[T][X] join sports club",
                "[T][ ] read book <Harry Potter>"
        );

        assertEquals(expected, actual);
    }
}
