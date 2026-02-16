package mon.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.Test;

class MonExceptionTest {
    @Test
    void testMonExceptionCreation() {
        String message = "Test error message";
        MonException exception = new MonException(message);
        assertEquals(message, exception.getMessage());
        assertInstanceOf(Exception.class, exception);
    }

    @Test
    void testTodoException() {
        MonException exception = MonException.todoException();
        String expectedMessage = "Unknown format for todo command!\n    Expected format: todo <task_description>";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testDeadlineException() {
        MonException exception = MonException.deadlineException();
        String expectedMessage = "Unknown format for deadline command!\n" +
            "    Expected format: deadline <task_description> /by <yyyy-MM-dd>\n" +
            "    Example: deadline submit assignment /by 2023-12-31";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testEventException() {
        MonException exception = MonException.eventException();
        String expectedMessage = "Unknown format for event command!\n" +
            "    Expected format: event <task_description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>\n" +
            "    Example: event team meeting /from 2023-12-01 /to 2023-12-01";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testUnknownCommandException() {
        MonException exception = MonException.unknownCommandException();
        String expectedMessage = "Unknown command!\n    Please try again.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testMarkException() {
        MonException exception = MonException.markException();
        String expectedMessage = "Unknown format for mark/unmark command!\n" +
            "    Expected format: mark <task_number> or unmark <task_number>";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testTaskOutOfBoundsException() {
        MonException exception = MonException.taskOutOfBoundsException();
        String expectedMessage = "Task number is out of bounds!\n    Please provide a valid task number.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testDeleteException() {
        MonException exception = MonException.deleteException();
        String expectedMessage = "Unknown format for delete command!\n    Expected format: delete <task_number>";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testDateFormatException() {
        MonException exception = MonException.dateFormatException();
        String expectedMessage = "Invalid date format!\n" +
            "    Expected format: yyyy-MM-dd (e.g., 2023-12-31)\n" +
            "    Please ensure the date is valid and follows the correct format.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testFileCorruptedException() {
        MonException exception = MonException.fileCorruptedException();
        String expectedMessage = "Data file appears to be corrupted!\n" +
            "    Unable to parse task information from the file.\n" +
            "    Please check the file format or restore from backup.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
