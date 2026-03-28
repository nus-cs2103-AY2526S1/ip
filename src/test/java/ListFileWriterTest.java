import ember.ListManager;
import ember.exception.InvalidInputException;
import ember.exception.MissingInformationException;
import ember.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ListFileWriterTest {

    private ListManager manager;

    @BeforeEach
    void setUp() {
        manager = new ListManager();
        // Clear the list before each test to ensure a clean state
        manager.list.clear();
    }

    // Helper method to get the descriptions from the manager's list
    private List<String> managerDescriptionList() {
        return manager.list.stream()
                .map(Task::description)
                .collect(Collectors.toList());
    }

    @Test
    void testInputList_AddTodoTask_Valid() throws MissingInformationException, InvalidInputException {
        manager.inputList("Read book");
        assertEquals(1, manager.list.size());
        assertEquals("[T][ ] Read book", managerDescriptionList().get(0));
    }

    @Test
    void testInputList_AddDeadlineTask_Valid() throws MissingInformationException, InvalidInputException {
        manager.inputList("Submit report", "2025-10-26");
        assertEquals(1, manager.list.size());
        assertEquals("[D][ ] Submit report (by: 26 Oct, 2025)", managerDescriptionList().get(0));
    }

    @Test
    void testInputList_AddDeadlineTask_InvalidDate() {
        assertThrows(InvalidInputException.class, () -> {
            manager.inputList("Invalid date", "not-a-date");
        });
    }

    @Test
    void testInputList_AddEventTask_Valid() throws MissingInformationException, InvalidInputException {
        manager.inputList("Project meeting", "2025-01-01", "2026-01-02");
        assertEquals(1, manager.list.size());
        assertEquals("[E][ ] Project meeting (from: 1 Jan, 2025, to: 2 Jan, 2026)", managerDescriptionList().get(0));
    }

    @Test
    void testDeleteTask_RemovesCorrectTask_ValidIndex() throws MissingInformationException, InvalidInputException {
        manager.inputList("Task A");
        manager.inputList("Task B");
        manager.inputList("Task C");
        manager.deleteTask(2); // Deleting Task B
        List<String> descriptions = managerDescriptionList();
        assertEquals(2, descriptions.size());
        assertEquals("[T][ ] Task A", descriptions.get(0));
        assertEquals("[T][ ] Task C", descriptions.get(1));
    }

    @Test
    void testDeleteTask_InvalidIndex_ThrowsException() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            manager.deleteTask(5); // List is empty, so this should fail
        });
        assertTrue(exception.getMessage().contains("Please try again"));
    }

    @Test
    void testDeleteTask_NegativeIndex_ThrowsException() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            manager.deleteTask(-1);
        });
        assertTrue(exception.getMessage().contains("Please try again"));
    }

    @Test
    void testMarkTask_ValidIndex() throws MissingInformationException, InvalidInputException {
        manager.inputList("Task to mark");
        manager.mark(1);
        String markedTask = managerDescriptionList().get(0);
        assertTrue(markedTask.startsWith("[T][X]")); // Check if it's marked
    }

    @Test
    void testMarkTask_InvalidIndex() {
        assertThrows(InvalidInputException.class, () -> {
            manager.mark(1); // List is empty
        });
    }

    @Test
    void testUnmarkTask_ValidIndex() throws MissingInformationException, InvalidInputException {
        manager.inputList("Task to unmark");
        manager.mark(1); // First, mark it
        manager.unmark(1); // Then, unmark it
        String unmarkedTask = managerDescriptionList().get(0);
        assertTrue(unmarkedTask.startsWith("[T][ ]")); // Check if it's unmarked
    }

    @Test
    void testUnmarkTask_InvalidIndex() {
        assertThrows(InvalidInputException.class, () -> {
            manager.unmark(1); // List is empty
        });
    }


    @Test
    void testFindKeyword_MatchExists() throws MissingInformationException, InvalidInputException {
        manager.inputList("Buy groceries");
        manager.inputList("Do homework");
        manager.inputList("Buy new laptop");
        String result = manager.findKeyword("buy");
        assertTrue(result.contains("Buy groceries"));
        assertTrue(result.contains("Buy new laptop"));
        assertFalse(result.contains("Do homework"));
    }

    @Test
    void testFindKeyword_NoMatch() throws MissingInformationException, InvalidInputException {
        manager.inputList("Buy groceries");
        manager.inputList("Do homework");
        String result = manager.findKeyword("watch");
        assertFalse(result.contains("Buy groceries"));
        assertFalse(result.contains("Do homework"));
        assertTrue(result.contains("Here are the matching tasks in your list:\n"));
    }

    @Test
    void testSortByName_MultipleTasks() throws MissingInformationException, InvalidInputException {
        manager.inputList("B - Task");
        manager.inputList("C - Task");
        manager.inputList("A - Task");
        String result = manager.sortByName();
        String[] lines = result.split("\n");
        assertEquals("1. [T][ ] A - Task", lines[1].trim());
        assertEquals("2. [T][ ] B - Task", lines[2].trim());
        assertEquals("3. [T][ ] C - Task", lines[3].trim());
    }

    @Test
    void testSortByName_EmptyList() {
        String result = manager.sortByName();
        assertEquals("Here are the matching tasks in your list:\n", result);
    }
}