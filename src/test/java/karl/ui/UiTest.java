package karl.ui;

import karl.task.Deadline;
import karl.task.TaskList;
import karl.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UiTest {
    private Ui ui;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
    }

    @Test
    public void showWelcome_returnsCorrectWelcomeMessage() {
        String expectedMessage = "Hello! I'm Karl 🤖\nWhat can I do for you?\n";
        String actualMessage = ui.showWelcome();
        assertEquals(expectedMessage, actualMessage,
                "The welcome message returned should match the expected greeting.");
    }

    @Test
    public void showGoodbye_addsGoodbyeMessage() {
        ui.showGoodbye();
        String output = ui.getLastMessage();
        assertTrue(output.toLowerCase().contains("bye"));
    }

    @Test
    public void showAddedTask_addsDescriptionAndSize() {
        Todo todo = new Todo("Test add");
        ui.showAddedTask(todo, 2);
        String output = ui.getLastMessage();
        assertTrue(output.contains("Test add"));
        assertTrue(output.contains("2"));
    }

    @Test
    public void showRemovedTask_addsDescriptionAndSize() {
        Deadline deadline = new Deadline("Remove me", "2026-10-10");
        ui.showRemovedTask(deadline, 0);
        String output = ui.getLastMessage();
        assertTrue(output.contains("Remove me"));
        assertTrue(output.contains("0"));
    }

    @Test
    public void showTaskList_displaysAllTasks() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("A"));
        tasks.add(new Todo("B"));
        ui.showTaskList(tasks);
        String output = ui.getLastMessage();
        assertTrue(output.contains("A"));
        assertTrue(output.contains("B"));
    }

    @Test
    public void showMarkedTask_addsDoneIndicator() {
        Todo todo = new Todo("do it");
        todo.markAsDone();
        ui.showMarkedTask(todo);
        String output = ui.getLastMessage();
        assertTrue(output.contains("do it"));
        assertTrue(output.contains("done") || output.contains("mark"));
    }

    @Test
    public void showUnmarkedTask_addsUndoneIndicator() {
        Todo todo = new Todo("undo it");
        ui.showUnmarkedTask(todo);
        String output = ui.getLastMessage();
        assertTrue(output.contains("undo it"));
        assertTrue(output.contains("not done") || output.contains("unmark"));
    }

    @Test
    public void showMessage_arbitraryMessage_bufferedCorrectly() {
        String msg = "Arbitrary test message.";
        ui.showMessage(msg);
        String output = ui.getLastMessage();
        assertEquals(msg + "\n", output);
    }

}
