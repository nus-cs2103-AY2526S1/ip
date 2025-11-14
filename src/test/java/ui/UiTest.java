package ui;

import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import task.*;
import exception.BaymaxException;

class UiTest {

    private Ui ui;

    @BeforeEach
    void setUp() {
        ui = new Ui();
    }

    @Test
    void testShowWelcome() {
        String output = ui.showWelcome();
        assertTrue(output.contains("Hello! I'm Baymax"), "Welcome message should contain greeting");
    }

    @Test
    void testShowLine() {
        String output = ui.showLine();
        assertEquals("___________________________________________________", output);
    }

    @Test
    void testShowTaskAdded() throws BaymaxException {
        Task t = new Todo("Test todo", TaskType.TODO);
        String output = ui.showTaskAdded(t, 1);

        assertTrue(output.contains("Task successfully added"), "Should contain add message");
        assertTrue(output.contains("Test todo"), "Should contain task description");
        assertTrue(output.contains("Now you have 1 tasks"), "Should show task count");
    }

    @Test
    void testShowTaskRemoved() throws BaymaxException {
        Task t = new Todo("Test todo", TaskType.TODO);
        String output = ui.showTaskRemoved(t, 0);

        assertTrue(output.contains("Task removed"), "Should contain remove message");
        assertTrue(output.contains("Test todo"), "Should contain task description");
        assertTrue(output.contains("Now you have 0 tasks"), "Should show updated task count");
    }

    @Test
    void testShowTaskMarked() throws BaymaxException {
        Task t = new Todo("Test todo", TaskType.TODO);
        String output = ui.showTaskMarked(t);

        assertTrue(output.contains("Task successfully marked"), "Should show mark message");
        assertTrue(output.contains("Test todo"), "Should show task description");
    }

    @Test
    void testShowTaskUnmarked() throws BaymaxException {
        Task t = new Todo("Test todo", TaskType.TODO);
        String output = ui.showTaskUnmarked(t);

        assertTrue(output.contains("Task successfully unmarked"), "Should show unmark message");
        assertTrue(output.contains("Test todo"), "Should show task description");
    }

    @Test
    void testShowListWithTasks() throws BaymaxException {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("First task", TaskType.TODO));
        tasks.add(new Deadline("Second task", TaskType.DEADLINE, "30/08/2025"));
        tasks.add(new Event("Third task", TaskType.EVENT, "30/08/2025", "01/09/2025"));

        String output = ui.showList(tasks);

        assertTrue(output.contains("Here are the tasks in your list"), "Should contain list header");
        assertTrue(output.contains("1.[T][ ] First task"), "Should show first task");
        assertTrue(output.contains("2.[D][ ] Second task"), "Should show deadline task");
        assertTrue(output.contains("3.[E][ ] Third task"), "Should show event task");
    }

    @Test
    void testShowListEmpty() {
        TaskList tasks = new TaskList();
        String output = ui.showList(tasks);
        assertTrue(output.contains("Here are the tasks in your list"), "List header should still appear");
        assertFalse(output.contains("[T]"), "No tasks should be shown");
    }

    @Test
    void testShowError() {
        String output = ui.showError("Something went wrong!");
        assertTrue(output.contains("Something went wrong!"), "Error message should appear");
        assertTrue(output.contains("___________________________________________________"), "Should have separator lines");
    }

    @Test
    void testShowBye() {
        String output = ui.showBye();
        assertTrue(output.contains("Bye. Hope to see you again soon!"), "Should show goodbye message");
    }

    @Test
    void testShowFoundTasks() throws BaymaxException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Find me", TaskType.TODO));
        tasks.add(new Deadline("Also find me", TaskType.DEADLINE, "30/08/2025"));

        String output = ui.showFoundTasks(tasks);

        assertTrue(output.contains("Here are the matching tasks in your list"), "Should show found header");
        assertTrue(output.contains("1.[T][ ] Find me"), "Should list first task");
        assertTrue(output.contains("2.[D][ ] Also find me"), "Should list second task");
    }

    @Test
    void testShowFoundTasksEmpty() {
        ArrayList<Task> tasks = new ArrayList<>();
        String output = ui.showFoundTasks(tasks);

        assertTrue(output.contains("Here are the matching tasks in your list"), "Header should appear even if empty");
        assertFalse(output.contains("[T]"), "No tasks should be shown");
    }
}
