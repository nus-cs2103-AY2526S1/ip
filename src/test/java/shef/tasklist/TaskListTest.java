package shef.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import shef.exception.InvalidArgumentException;
import shef.task.EventTask;
import shef.task.Task;
import shef.task.TodoTask;

/*
Test cases generated using ChatGPT.
 */
public class TaskListTest {
    @Test
    public void testAddTasks() {
        TaskList list = new TaskList();

        Task todo = new TodoTask("test 1");
        assertEquals(list.add(todo),
                "Got it. I've added this task:\n  [T][ ] test 1\nYou now have 1 task in the list.");

        Task event = new EventTask("test 2", "now", "later");
        assertEquals(list.add(event),
                "Got it. I've added this task:\n  "
                        + "[E][ ] test 2 (from: now to: later)\nYou now have 2 tasks in the list.");
    }

    @Test
    public void testMarkTaskValidId() {
        TaskList list = new TaskList();

        Task todo = new TodoTask("test task");
        list.add(todo);

        assertEquals(list.markAsDone(1), "Nice! I've marked this task as done:\n  " + "[T][X] test task");
        assertEquals(list.unmarkAsDone(1), "Ok, I've marked this task as not done yet:\n  " + "[T][ ] test task");
    }

    @Test
    public void testMarkTaskInvalidId() {
        TaskList list = new TaskList();

        Task todo = new TodoTask("test task");
        list.add(todo);

        try {
            list.markAsDone(2);
            assert false : "Execution should not reach this line.";
        } catch (InvalidArgumentException e) {
            assertEquals(e.getMessage(), "Invalid task id!");
        }

        try {
            list.unmarkAsDone(2);
            assert false : "Execution should not reach this line.";
        } catch (InvalidArgumentException e) {
            assertEquals(e.getMessage(), "Invalid task id!");
        }
    }

    @Test
    public void testDeleteTask() {
        TaskList list = new TaskList();

        Task todo = new TodoTask("test");
        list.add(todo);

        assertEquals(list.deleteTask(1),
                "Noted. I've removed the task:\n  [T][ ] test\nYou now have 0 tasks in the list.");
        try {
            list.deleteTask(1);
            assert false : "Execution should not reach this line.";
        } catch (InvalidArgumentException e) {
            assertEquals(e.getMessage(), "Invalid task id!");
        }
    }

    @Test
    public void testGetMatches() {
        TaskList tl = new TaskList();
        tl.add(new TodoTask("read book"));
        tl.add(new TodoTask("Read notes"));
        tl.add(new TodoTask("borrow book"));

        List<Task> hits1 = tl.getMatches("book"); // matches 'read book' and 'borrow book'
        assertEquals(2, hits1.size());
        assertTrue(hits1.stream().allMatch(t -> t.getDescription().contains("book")));

        List<Task> hits2 = tl.getMatches("Read"); // matches 'Read notes' only (case-sensitive)
        assertEquals(1, hits2.size());
        assertTrue(hits2.get(0).getDescription().contains("Read"));

        List<Task> hits3 = tl.getMatches("missing");
        assertTrue(hits3.isEmpty());
    }

    @Test
    void testToStringEmpty() {
        TaskList tl = new TaskList();
        assertEquals("List is empty!", tl.toString());
    }

    @Test
    void testToStringNumbering() {
        TaskList tl = new TaskList();
        Task a = new TodoTask("a");
        Task b = new TodoTask("b");
        Task c = new TodoTask("c");
        tl.add(a);
        tl.add(b);
        tl.add(c);

        String s = tl.toString();
        // Structure: "1.<a>\n2.<b>\n3.<c>"
        assertTrue(s.startsWith("1."), "Should start with 1.");
        assertTrue(s.contains("\n2."), "Should contain newline then 2.");
        assertTrue(s.endsWith("3." + c.toString()), "Should end with third item");
        assertTrue(s.contains(a.toString()) && s.contains(b.toString()) && s.contains(c.toString()),
                "Should include all tasks");
    }

    @Test
    void testToCsvStringEmpty() {
        TaskList tl = new TaskList();
        assertEquals("", tl.toCsvString());
    }
}
