package parser;

import exceptions.EmptyTaskException;
import exceptions.NoSpaceAfterCommandException;
import org.junit.jupiter.api.Test;

import tasks.DeadlineTask;
import tasks.Task;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void testTodoCommandThrowsEmptyTaskException() {
        assertThrows(EmptyTaskException.class, () -> {
            Parser.processCommand("todo ");
        });
    }

    @Test
    void testTodoCommandThrowsNoSpaceAfterCommandException() {
        assertThrows(NoSpaceAfterCommandException.class, () -> {
            Parser.processCommand("todo");
        });
    }
    @Test
    void testDeadlineCommandParsesDescriptionAndTime() throws Exception {
        Parser.processCommand("deadline finish project /by 07-03-1919 0800");

        Task task = TaskList.getTaskAt(1); // assuming TaskList is 1-based
        assertTrue(task instanceof DeadlineTask);
        assertEquals("[DDLN][ ] finish project  (By: 07/03/1919 8:00am)", task.toString());
    }
}
