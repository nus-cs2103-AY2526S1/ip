package bob.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Junit tests for unit testing of TaskList
 * Additional tests created by an LLM
 */
public class TaskListTest {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void test1() {
        Deadline test = new Deadline("thing", LocalDate.parse("2025-12-25", DATE_FORMAT));
        TaskList testList = new TaskList();
        testList.fileAddItem(test);
        testList.markItem(1);
        assertTrue(test.isComplete());
    }

    @Test
    void markItem_marksCorrectly() {
        TaskList list = new TaskList();
        list.addItem(new ToDo("Read book"));
        list.markItem(1);
        assertTrue(list.getTaskList().get(0).isComplete());
    }

    @Test
    void unmarkItem_unmarksCorrectly() {
        TaskList list = new TaskList();
        list.addItem(new ToDo("Read book"));
        list.markItem(1);
        list.unmarkItem(1);
        assertFalse(list.getTaskList().get(0).isComplete());
    }

    @Test
    void deleteItem_removesCorrectTask() {
        TaskList list = new TaskList();
        list.addItem(new ToDo("Task1"));
        list.addItem(new ToDo("Task2"));

        String removed = list.deleteItem(1);
        assertEquals(1, list.getTaskList().size());
        assertFalse(removed.contains("Task2"));
    }
}
