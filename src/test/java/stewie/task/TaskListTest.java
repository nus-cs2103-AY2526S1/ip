package stewie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for TaskList.
 */
public class TaskListTest {

    private TaskList taskList;
    private ToDoTask todoTask;
    private DeadlineTask deadlineTask;
    private EventTask eventTask;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        todoTask = new ToDoTask("Read a book");
        deadlineTask = new DeadlineTask("Pay bills", LocalDateTime.of(2025, 10, 26, 18, 0));
        eventTask = new EventTask(
                "Team meeting",
                LocalDateTime.of(2025, 11, 1, 10, 0),
                LocalDateTime.of(2025, 11, 1, 12, 0)
        );
    }

    @Test
    public void testAddTask() {
        String expectedMessage =
                        "I've scribbled down your little task:\n"
                        + " [T][ ] Read a book\n"
                        + "Now, do try to keep up, won't you?\n"
                        + "You have 1 tasks left";
        String actualMessage = taskList.addTask(todoTask);
        assertEquals(expectedMessage, actualMessage);
        assertEquals(1, taskList.size());
    }

    @Test
    public void testMarkTask() {
        taskList.addTask(todoTask);
        String expectedMessage =
                        "Behold! I've declared this paltry task complete.\n"
                        + " [T][X] Read a book\n"
                        + "Don't get cocky. You still have a long way to go.";
        String actualMessage = taskList.markTask(1);
        assertEquals(expectedMessage, actualMessage);
        assertTrue(taskList.getTasks().get(0).isDone);
    }

    @Test
    public void testUnmarkTask() {
        taskList.addTask(todoTask);
        taskList.markTask(1);
        String expectedMessage =
                        "You're toying with me! I've marked this back as incomplete.\n"
                        + " [T][ ] Read a book\n"
                        + "Don't think for a second I'll forget this betrayal.";
        String actualMessage = taskList.unmarkTask(1);
        assertEquals(expectedMessage, actualMessage);
        assertEquals(false, taskList.getTasks().get(0).isDone);
    }

    @Test
    public void testDeleteTask() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);
        String expectedMessage =
                "Poof! Begone with you, you insignificant little undertaking!\n"
                        + " [T][ ] Read a book\n"
                        + "Don't get cocky. You still have a long way to go.\n"
                        + "You have 1 tasks left.";
        String actualMessage = taskList.deleteTask(1);
        assertEquals(expectedMessage, actualMessage);
        assertEquals(1, taskList.size());
        assertEquals(
                "[D][ ] Pay bills (by: 26 Oct 2025 18:00)",
                taskList.getTasks().get(0).getDescription()
        );
    }

    @Test
    public void testListTask() {
        taskList.addTask(todoTask);
        taskList.addTask(deadlineTask);
        taskList.addTask(eventTask);
        String expectedMessage =
                        "These, my dear simpleton, are the items on your agenda.\n"
                        + " 1. [T][ ] Read a book\n"
                        + " 2. [D][ ] Pay bills (by: 26 Oct 2025 18:00)\n"
                        + " 3. [E][ ] Team meeting (from: 01 Nov 2025 10:00 to: 01 Nov 2025 12:00)\n"
                        + "Failure is not an option.\n"
                        + "You have 3 tasks left";
        String actualMessage = taskList.listTask();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testListEmptyTask() {
        String expectedMessage = "No tasks? How utterly dreadful!\n";
        String actualMessage = taskList.listTask();
        assertEquals(expectedMessage, actualMessage);
    }
}
