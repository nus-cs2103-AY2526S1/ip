package jaiden.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void listTest() {
        assertEquals("Here are the tasks in your list:\n", new TaskList().list());
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));
        tasks.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        tasks.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        assertEquals("Here are the tasks in your list:\n1.[T][ ] test\n"
                + "2.[D][ ] test (by: Aug 22 2025)\n3.[E][ ] test (from: Aug 22 2025 to: Aug 22 2025)\n",
                new TaskList(tasks).list());
    }

    @Test
    public void markTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));
        tasks.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        tasks.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        TaskList test = new TaskList(tasks);
        assertEquals("Nice! I've marked this task as done:\n[T][X] test", test.mark(0));
        assertEquals("Nice! I've marked this task as done:\n[D][X] test (by: Aug 22 2025)",
                test.mark(1));
        assertEquals("Nice! I've marked this task as done:\n"
                + "[E][X] test (from: Aug 22 2025 to: Aug 22 2025)", test.mark(2));
    }

    @Test
    public void unmarkTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test", true));
        tasks.add(new Deadline("test", true, LocalDate.parse("2025-08-22")));
        tasks.add(new Event("test", true, LocalDate.parse("2025-08-22"),
                LocalDate.parse("2025-08-22")));
        TaskList test = new TaskList(tasks);
        assertEquals("OK, I've marked this task as not done yet:\n[T][ ] test",
                test.unmark(0));
        assertEquals("OK, I've marked this task as not done yet:\n[D][ ] test (by: Aug 22 2025)",
                test.unmark(1));
        assertEquals("OK, I've marked this task as not done yet:\n"
                + "[E][ ] test (from: Aug 22 2025 to: Aug 22 2025)", test.unmark(2));
    }

    @Test
    public void addTest() {
        TaskList test = new TaskList();
        assertEquals("Got it. I've added this task:\n[T][ ] test\n"
                + "Now you have 1 tasks in the list.", test.add(new Todo("test")));
        assertEquals("Got it. I've added this task:\n[D][ ] test (by: Aug 22 2025)\n"
                + "Now you have 2 tasks in the list.",
                test.add(new Deadline("test", LocalDate.parse("2025-08-22"))));
        assertEquals("Got it. I've added this task:\n"
                + "[E][ ] test (from: Aug 22 2025 to: Aug 22 2025)\nNow you have 3 tasks in the list.",
                test.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22"))));
    }

    @Test
    public void removeTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));
        tasks.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        tasks.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        TaskList test = new TaskList(tasks);
        assertEquals("Noted. I've removed this task:\n"
                + "[E][ ] test (from: Aug 22 2025 to: Aug 22 2025)\nNow you have 2 tasks in the list.",
                test.remove(2));
        assertEquals("Noted. I've removed this task:\n[D][ ] test (by: Aug 22 2025)\n"
                + "Now you have 1 tasks in the list.", test.remove(1));
        assertEquals("Noted. I've removed this task:\n[T][ ] test\n"
                + "Now you have 0 tasks in the list.", test.remove(0));
    }

    @Test
    public void viewTest() {
        assertEquals("Here are the tasks on Aug 22 2025 in your list:\n",
                new TaskList().view(LocalDate.parse("2025-08-22")));
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        tasks.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        tasks.add(new Deadline("test", LocalDate.parse("2024-08-22")));
        tasks.add(new Event("test", LocalDate.parse("2024-08-22"), LocalDate.parse("2024-08-22")));
        assertEquals("Here are the tasks on Aug 22 2025 in your list:\n"
                + "1.[D][ ] test (by: Aug 22 2025)\n2.[E][ ] test (from: Aug 22 2025 to: Aug 22 2025)\n",
                new TaskList(tasks).view(LocalDate.parse("2025-08-22")));
    }

    @Test
    public void findTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));
        tasks.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        tasks.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        assertEquals("Here are the matching tasks in your list:\n1.[T][ ] test\n"
                + "2.[D][ ] test (by: Aug 22 2025)\n3.[E][ ] test (from: Aug 22 2025 to: Aug 22 2025)\n",
                new TaskList(tasks).find("test"));
    }

    @Test
    public void saveTest() {
        assertEquals("", new TaskList().save());
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));
        tasks.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        tasks.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        tasks.add(new Todo("test", true));
        tasks.add(new Deadline("test", true, LocalDate.parse("2025-08-22")));
        tasks.add(new Event("test", true,
                LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        assertEquals("T | 0 | test\nD | 0 | test | 2025-08-22\nE | 0 | test | 2025-08-22 | 2025-08-22\n"
                + "T | 1 | test\nD | 1 | test | 2025-08-22\nE | 1 | test | 2025-08-22 | 2025-08-22\n",
                new TaskList(tasks).save());
    }

    @Test
    public void equalsTest() {
        assertEquals(new TaskList(), new TaskList());
        ArrayList<Task> tasks1 = new ArrayList<>();
        ArrayList<Task> tasks2 = new ArrayList<>();
        ArrayList<Task> tasks3 = new ArrayList<>();
        tasks1.add(new Todo("test1"));
        tasks2.add(new Todo("test1"));
        tasks3.add(new Todo("test2"));
        assertEquals(new TaskList(tasks1), new TaskList(tasks2));
        assertNotEquals(new TaskList(tasks1), new TaskList(tasks3));
    }
}
