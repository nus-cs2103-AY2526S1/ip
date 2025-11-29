package kleebot.ui;

import kleebot.ui.Parser;
import kleebot.command.*;
import kleebot.task.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParseTodo() {
        Command cmd = null;
        try {
            cmd = Parser.parse("todo buy milk");
        } catch (KleeExceptions e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(TodoCommand.class, cmd);

        TodoCommand todo = (TodoCommand) cmd;
        assertEquals("buy milk", todo.getDescription().trim());
    }

    @Test
    void testParseDeadlineWithDate() {
        Command cmd = null;
        try {
            cmd = Parser.parse("deadline submit assignment /by 2025-12-25");
        } catch (KleeExceptions e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(DeadlineCommand.class, cmd);

        DeadlineCommand deadline = (DeadlineCommand) cmd;
        assertEquals("submit assignment", deadline.getDescription());
        assertEquals("Dec 25 2025", deadline.getBy()); // formatted date
    }

    @Test
    void testParseDeadlineWithoutDetails() {
        try {
            Command cmd = Parser.parse("deadline");
            assertInstanceOf(UnknownCommand.class, cmd);
        } catch (KleeExceptions e) {
            assertEquals(Ui.ErrorMessage.MISSING_DETAILS.getMessage(), e.getMessage());
        }
    }

    @Test
    void testParseDeadlineWithoutBy() {
        try {
            Command cmd = Parser.parse("deadline maths homework");
            assertInstanceOf(UnknownCommand.class, cmd);
        } catch (KleeExceptions e) {
            assertEquals(Ui.ErrorMessage.MISSING_BY.getMessage(), e.getMessage());
        }
    }

    @Test
    void testParseDeadlineWithoutByDetails() {
        try {
            Command cmd = Parser.parse("deadline maths homework /by");
            assertInstanceOf(UnknownCommand.class, cmd);
        } catch (KleeExceptions e) {
            assertEquals(Ui.ErrorMessage.MISSING_BY_2.getMessage(), e.getMessage());
        }
    }

    @Test
    void testParseEventWithDates() {
        Command cmd = null;
        try {
            cmd = Parser.parse("event project meeting /from 2025-01-01 /to 2025-01-05");
        } catch (KleeExceptions e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(EventCommand.class, cmd);

        EventCommand event = (EventCommand) cmd;
        assertEquals("project meeting", event.getDescription());
        assertEquals("Jan 01 2025", event.getFrom());
        assertEquals("Jan 05 2025", event.getTo());
    }

    @Test
    void testParseEventMissingFrom() {
        try {
            Command cmd = Parser.parse("event project meeting /to tomorrow");
            assertInstanceOf(UnknownCommand.class, cmd);
        } catch (KleeExceptions e) {
            assertEquals(Ui.ErrorMessage.MISSING_FROM.getMessage(), e.getMessage());
        }

    }

    @Test
    void testParseEventMissingTo() {
        try {
            Command cmd = Parser.parse("event project meeting /from today");
            assertInstanceOf(UnknownCommand.class, cmd);
        } catch (KleeExceptions e) {
            assertEquals(Ui.ErrorMessage.MISSING_TO.getMessage(), e.getMessage());
        }

    }


    @Test
    void testParseListCommand() {
        Command cmd = null;
        try {
            cmd = Parser.parse("list");
        } catch (KleeExceptions e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(ListCommand.class, cmd);
    }

    @Test
    void testParseMarkCommand() {
        Command cmd = null;
        try {
            cmd = Parser.parse("mark 2");
        } catch (KleeExceptions e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(MarkCommand.class, cmd);
    }

    @Test
    void testParseUnmarkCommand() {
        Command cmd = null;
        try {
            cmd = Parser.parse("unmark 3");
        } catch (KleeExceptions e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(UnmarkCommand.class, cmd);
    }

    @Test
    void testParseDeleteCommand() {
        Command cmd = null;
        try {
            cmd = Parser.parse("delete 1");
        } catch (KleeExceptions e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(DeleteCommand.class, cmd);
    }

    @Test
    void testParseEchoCommand() {
        Command cmd = null;
        try {
            cmd = Parser.parse("echo hello world");
        } catch (KleeExceptions e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(EchoCommand.class, cmd);
    }

    @Test
    void testParseUnknownCommand() {
        Command cmd = null;
        try {
            cmd = Parser.parse("foobar something");
        } catch (KleeExceptions e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(UnknownCommand.class, cmd);
    }

    @Test
    void testParseDateStrValidFormats() {
        assertEquals("Jan 01 2025", Parser.parseDateStr("2025-01-01"));
        assertEquals("Jan 01 2025", Parser.parseDateStr("01/01/2025"));
        assertEquals("Jan 01 2025", Parser.parseDateStr("2025/01/01"));
        assertEquals("Jan 01 2025", Parser.parseDateStr("01-01-2025"));
    }

    @Test
    void testParseDateStrInvalidFormat() {
        assertEquals("tomorrow", Parser.parseDateStr("tomorrow")); // stays same
    }
}