package ackermann.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidCodeException;
import ackermann.exceptions.InvalidDeleteException;
import ackermann.exceptions.InvalidFindException;
import ackermann.exceptions.InvalidMarkException;
import ackermann.exceptions.InvalidTagException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineByException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventFromException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventToException;
import ackermann.exceptions.task.InvalidTodo.InvalidTodoException;


public class ParserTest {
    private TaskList tasks;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        parser = new Parser(tasks);
    }


    @Test
    public void valid_Inputs() throws CheckedException {
        // Test "todo"
        String toDo = """
                Got it bro. I've added this ToDo:
                [T][ ] something [ ]
                Now you have 1 tasks in the list.""";
        assertEquals(toDo, parser.parse("todo something"));

        // Test "deadline"
        String deadline = """
                Can bro. I've added this Deadline:
                [D][ ] test1 (by Aug 23 2025) [ ]
                Now you have 2 tasks in the list.""";
        assertEquals(deadline, parser.parse("deadline test1 /by 2025-08-23"));

        // Test "event"
        String event = """
                Roger bro. I've added this Event:
                [E][ ] project meeting (from: Dec 1 2019 to: Dec 15 2019) [ ]
                Now you have 3 tasks in the list.""";
        assertEquals(event, parser.parse("event project meeting /from 2019-12-01 /to 2019-12-15"));

        // Test "list"
        String list = """
                Here are the tasks in your list bro:
                1. [T][ ] something [ ]
                2. [D][ ] test1 (by Aug 23 2025) [ ]
                3. [E][ ] project meeting (from: Dec 1 2019 to: Dec 15 2019) [ ]""";
        assertEquals(list, parser.parse("list"));

        // Test "mark"
        String mark = """
                Nice one bro! I've marked this task as done:
                [T][X] something [ ]""";
        assertEquals(mark, parser.parse("mark 1"));

        // Test "unmark"
        String unmark = """
                Ok bro, I've marked this task as not done yet:
                [T][ ] something [ ]""";
        assertEquals(unmark, parser.parse("unmark 1"));

        // Test "delete"
        String delete = """
                Ok bro, I've removed this task:
                [T][ ] something [ ]
                Now you have 2 tasks in the list.""";
        assertEquals(delete, parser.parse("delete 1"));

        // Test "find"
        String find = """
                Ok bro, I've helped you to search and here are the matching tasks in your list:
                [E][ ] project meeting (from: Dec 1 2019 to: Dec 15 2019) [ ]""";
        assertEquals(find, parser.parse("find meeting"));

        // Test "tag"
        String tag = """
                Noted bro, I have successfully tagged:
                [D][ ] test1 (by Aug 23 2025) [important]""";
        assertEquals(tag, parser.parse("tag 1 important"));

        // Verify the tag was added by listing tasks again
        String listAfterTag = """
                Here are the tasks in your list bro:
                1. [D][ ] test1 (by Aug 23 2025) [important]
                2. [E][ ] project meeting (from: Dec 1 2019 to: Dec 15 2019) [ ]""";
        assertEquals(listAfterTag, parser.parse("list"));


        // Test "bye"
        String bye = "Bye. Hope to see you again soon!";
        assertEquals(bye, parser.parse("bye"));
    }

    /**
     * Tests if correct exception is being thrown when wrong inputs
     */
    @Test
    public void invalid_Inputs_throwExceptions() {
        // Test unknown and uppercase codewords
        assertThrows(InvalidCodeException.class, () -> parser.parse("oops"));
        assertThrows(InvalidCodeException.class, () -> parser.parse("TODO something"));

        // Test "todo" with no description
        assertThrows(InvalidTodoException.class, () -> parser.parse("todo"));
        assertThrows(InvalidTodoException.class, () -> parser.parse("todo "));

        // Test "deadline" with missing parts
        assertThrows(InvalidDeadlineException.class, () -> parser.parse("deadline"));
        assertThrows(InvalidDeadlineByException.class, () -> parser.parse("deadline read book"));

        // Test "event" with missing parts
        assertThrows(InvalidEventException.class, () -> parser.parse("event"));
        assertThrows(InvalidEventFromException.class, () -> parser.parse("event project meeting"));
        assertThrows(InvalidEventToException.class, () -> parser.parse("event project meeting /from 2025-01-01"));

        // Test "mark" and "unmark" with invalid/missing index
        assertThrows(InvalidMarkException.class, () -> parser.parse("mark"));
        assertThrows(InvalidMarkException.class, () -> parser.parse("mark one"));
        assertThrows(InvalidMarkException.class, () -> parser.parse("unmark"));

        // Test "delete" with invalid/missing index
        assertThrows(InvalidDeleteException.class, () -> parser.parse("delete"));
        assertThrows(InvalidDeleteException.class, () -> parser.parse("delete one"));

        // Test "find" with no keyword
        assertThrows(InvalidFindException.class, () -> parser.parse("find"));
        assertThrows(InvalidFindException.class, () -> parser.parse("find "));

        // Test "tag" with missing parts
        assertThrows(InvalidTagException.class, () -> parser.parse("tag"));
        assertThrows(InvalidTagException.class, () -> parser.parse("tag 1"));
    }

    /**
     * Tests wrong date format
     */
    @Test
    public void invalid_dateFormats() {
        String expectedMessage = "Invalid Date!\nFollow the format 'YYYY-MM-DD'.";
        try {
            assertEquals(expectedMessage, parser.parse("deadline read book /by 23-08-2025"));
            assertEquals(expectedMessage, parser.parse("event meeting /from 2025-01-01 /to 02-01-2025"));
        } catch (CheckedException e) {
            fail("A CheckedException should not have been thrown for a date format error.");
        }
    }
}