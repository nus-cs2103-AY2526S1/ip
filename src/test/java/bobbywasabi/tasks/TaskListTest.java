package bobbywasabi.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;
    private Task task3;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();

        task1 = new Task("Read book", false);
        task2 = new Task("Write report", true);
        task3 = new Task("Read article", false);

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
    }

    @Test
    void testFindTasksThatMatchKeyword_validFindTasks_success() {
        String result = taskList.findTasksThatMatchKeyword("Read");
        String expected = "1. [ ] Read book\n2. [ ] Read article\n";
        assertEquals(expected, result);
    }

    @Test
    void testConvertTaskToString_validConvertTask_success() {
        String result = taskList.convertTaskToString(2, task2);
        assertEquals("2. [X] Write report\n", result);
    }

    @Test
    void testConvertTasksToString_validList_success() {
        ArrayList<Task> subset = new ArrayList<>();
        subset.add(task1);
        subset.add(task3);

        String result = taskList.convertTasksToString(subset);
        String expected = "1. [ ] Read book\n2. [ ] Read article\n";
        assertEquals(expected, result);
    }

    @Test
    void testToString_returnsAllTasks_success() {
        String result = taskList.toString();
        String expected = "1. [ ] Read book\n2. [X] Write report\n3. [ ] Read article\n";
        assertEquals(expected, result);
    }

    @Test
    void testConvertTaskToString_invalidIndex_assertionError() {
        assertThrows(AssertionError.class, () -> taskList.convertTaskToString(5, task1));
    }
}
