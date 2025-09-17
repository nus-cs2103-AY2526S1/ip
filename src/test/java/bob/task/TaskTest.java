package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import bob.exception.InvalidInputException;

public class TaskTest {
    // Todo task creation tests
    @Test
    public void createFromString_validTodoInput_returnsTodoTask() throws InvalidInputException {
        String input = "todo read";
        Task task = Task.createFromString(input);
        assertEquals(Todo.class, task.getClass());
        assertEquals("[T][ ] read", task.toString());
    }

    @Test
    public void createFromString_emptyTodoDescription_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("todo ");
        });
    }

    @Test
    public void createFromString_todoWithOnlySpaces_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("todo    ");
        });
    }

    // Deadline task creation tests
    @Test
    public void createFromString_validDeadlineInput_returnsDeadlineTask() throws InvalidInputException {
        String input = "deadline return book /by 01/01/2025 1200";
        Task task = Task.createFromString(input);
        assertEquals(Deadline.class, task.getClass());
        assertEquals("[D][ ] return book (due: 01 Jan 2025 12:00)", task.toString());
    }

    @Test
    public void createFromString_deadlineWithoutBy_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("deadline return book");
        });
    }

    @Test
    public void createFromString_deadlineWithEmptyDescription_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("deadline /by 01/01/2025 1200");
        });
    }

    @Test
    public void createFromString_deadlineWithEmptyDate_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("deadline return book /by ");
        });
    }

    @Test
    public void createFromString_deadlineWithInvalidDateFormat_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("deadline return book /by tomorrow");
        });
    }

    // Event task creation tests
    @Test
    public void createFromString_validEventInput_returnsEventTask() throws InvalidInputException {
        String input = "event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400";
        Task task = Task.createFromString(input);
        assertEquals(Event.class, task.getClass());
        assertEquals("[E][ ] project meeting (01 Jan 2025 12:00 - 01 Jan 2025 14:00)", task.toString());
    }

    @Test
    public void createFromString_eventWithoutFrom_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("event project meeting /to 01/01/2025 1400");
        });
    }

    @Test
    public void createFromString_eventWithoutTo_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("event project meeting /from 01/01/2025 1200");
        });
    }

    @Test
    public void createFromString_eventWithEmptyDescription_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("event /from 01/01/2025 1200 /to 01/01/2025 1400");
        });
    }

    @Test
    public void createFromString_eventWithInvalidStartDate_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("event meeting /from yesterday /to 01/01/2025 1400");
        });
    }

    @Test
    public void createFromString_eventWithInvalidEndDate_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("event meeting /from 01/01/2025 1200 /to tomorrow");
        });
    }

    // Invalid task type tests
    @Test
    public void createFromString_unknownTaskType_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("unknown task description");
        });
    }

    @Test
    public void createFromString_emptyTaskType_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Task.createFromString("");
        });
    }

    // Task marking tests
    @Test
    public void markDone_unmarkedTask_returnsMarkedTask() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        Task markedTask = task.markDone();
        
        assertTrue(markedTask.isDone());
        assertEquals("X", markedTask.getStatusIcon());
        assertEquals("[T][X] read book", markedTask.toString());
    }

    @Test
    public void markDone_alreadyMarkedTask_remainsMarked() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        task.markDone();
        Task remarkedTask = task.markDone();
        
        assertTrue(remarkedTask.isDone());
        assertEquals("X", remarkedTask.getStatusIcon());
    }

    @Test
    public void unmarkDone_markedTask_returnsUnmarkedTask() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        task.markDone();
        Task unmarkedTask = task.unmarkDone();
        
        assertFalse(unmarkedTask.isDone());
        assertEquals(" ", unmarkedTask.getStatusIcon());
        assertEquals("[T][ ] read book", unmarkedTask.toString());
    }

    @Test
    public void unmarkDone_unmarkedTask_remainsUnmarked() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        Task stillUnmarkedTask = task.unmarkDone();
        
        assertFalse(stillUnmarkedTask.isDone());
        assertEquals(" ", stillUnmarkedTask.getStatusIcon());
    }

    // Task description search tests
    @Test
    public void containsDescription_taskWithMatchingKeyword_returnsTrue() throws InvalidInputException {
        Task task = Task.createFromString("todo read programming book");
        assertTrue(task.containsDescription("book"));
        assertTrue(task.containsDescription("programming"));
        assertTrue(task.containsDescription("read"));
    }

    @Test
    public void containsDescription_taskWithoutMatchingKeyword_returnsFalse() throws InvalidInputException {
        Task task = Task.createFromString("todo read programming book");
        assertFalse(task.containsDescription("movie"));
        assertFalse(task.containsDescription("homework"));
    }

    @Test
    public void containsDescription_partialMatch_returnsTrue() throws InvalidInputException {
        Task task = Task.createFromString("todo programming");
        assertTrue(task.containsDescription("gram"));
        assertTrue(task.containsDescription("prog"));
    }

    @Test
    public void containsDescription_emptyKeyword_returnsTrue() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        assertTrue(task.containsDescription(""));
    }

    // DateTime parsing tests
    @Test
    public void parseDateTimeString_validFormat_returnsLocalDateTime() {
        String input = "01/01/2025 1200";
        LocalDateTime result = Task.parseDateTimeString(input);
        assertEquals(2025, result.getYear());
        assertEquals(1, result.getMonthValue());
        assertEquals(1, result.getDayOfMonth());
        assertEquals(12, result.getHour());
        assertEquals(0, result.getMinute());
    }

    @Test
    public void formatDateTime_validDateTime_returnsFormattedString() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 1, 12, 0);
        String result = Task.formatDateTime(dateTime);
        assertEquals("01 Jan 2025 12:00", result);
    }

    @Test
    public void toInputStringDateTime_validDateTime_returnsInputFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 1, 12, 0);
        String result = Task.toInputStringDateTime(dateTime);
        assertEquals("01/01/2025 1200", result);
    }

    // Task input string conversion tests
    @Test
    public void toInputString_todoTask_returnsOriginalInput() throws InvalidInputException {
        Task task = Task.createFromString("todo read book");
        assertEquals("todo read book", task.toInputString());
    }

    @Test
    public void toInputString_deadlineTask_returnsOriginalInput() throws InvalidInputException {
        Task task = Task.createFromString("deadline return book /by 01/01/2025 1200");
        assertEquals("deadline return book /by 01/01/2025 1200", task.toInputString());
    }

    @Test
    public void toInputString_eventTask_returnsOriginalInput() throws InvalidInputException {
        Task task = Task.createFromString("event meeting /from 01/01/2025 1200 /to 01/01/2025 1400");
        assertEquals("event meeting /from 01/01/2025 1200 /to 01/01/2025 1400", task.toInputString());
    }
}
