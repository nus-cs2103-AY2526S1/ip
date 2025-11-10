package bob.util;

import bob.tasks.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Junit tests for unit testing of Parser
 * Additional tests created by an LLM
 */
public class ParserTest {
    @Test
    public void test1() {
        TaskList baseList = new TaskList();
        TaskList testList = new TaskList();
        baseList.addItem(new ToDo("thing"));
        Parser.parseInput("todo thing", testList);

        String base = baseList.getTaskList().get(0).toString();
        String test = testList.getTaskList().get(0).toString();
        assertEquals(base, test);
    }

    @Test
    void parseTodo_addsTask() {
        TaskList list = new TaskList();
        String response = Parser.parseInput("todo read book", list);
        assertEquals(1, list.getTaskList().size());
        assertTrue(response.contains("Task added"));
    }


    @Test
    void parseDeadline_addsDeadline() {
        TaskList list = new TaskList();
        String response = Parser.parseInput("deadline return book /by 2025-09-20", list);

        assertEquals(1, list.getTaskList().size());
        Task task = list.getTaskList().get(0);
        assertInstanceOf(Deadline.class, task);
        assertTrue(response.contains("return book"));
    }

    @Test
    void parseEvent_addsEvent() {
        TaskList list = new TaskList();
        String response = Parser.parseInput("event conference /from 2025-09-18 /to 2025-09-19", list);

        assertEquals(1, list.getTaskList().size());
        assertInstanceOf(Event.class, list.getTaskList().get(0));
    }

    @Test
    void parseInvalidCommand_returnsErrorMessage() {
        TaskList list = new TaskList();
        String response = Parser.parseInput("blah blah", list);
        assertTrue(response.contains("blah blah\nType `help` to see available commands."));
    }
}
