package choicebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import choicebot.ChoiceBotException;


/**
 * Unit tests for the {@link Todo} class.
 */
public class TodoTest {

    /**
     * Dummy test to verify the testing framework works.
     */
    @Test
    public void dummyTest() {
        assertEquals(2, 2, "Dummy test should pass.");
    }

    /**
     * Tests string representation of a Todo task.
     * Ensures that [X] or [ ] is displayed correctly.
     */
    @Test
    public void testStringConversion() throws ChoiceBotException {
        Todo doneTodo = new Todo("read book", true);
        assertEquals("[T][X] read book", doneTodo.toString(), "Todo marked done should have [X]");

        Todo notDoneTodo = new Todo("read book", false);
        assertEquals("[T][ ] read book", notDoneTodo.toString(), "Todo not done should have [ ]");
    }

    /**
     * Tests that creating a Todo with an empty description throws ChoiceBotException.
     */
    @Test
    public void todo_emptyDescription_throwsException() {
        assertThrows(ChoiceBotException.class, () -> new Todo("", false),
                "Empty description should throw ChoiceBotException");
    }

    /**
     * Tests that creating a Todo with a null description throws ChoiceBotException.
     */
    @Test
    public void todo_nullDescription_throwsException() {
        assertThrows(ChoiceBotException.class, () -> new Todo(null, false),
                "Null description should throw ChoiceBotException");
    }
}
