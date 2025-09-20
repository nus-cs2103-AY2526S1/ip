package marquess;

import marquess.command.AddCommand;
import marquess.command.Command;
import marquess.command.DeleteCommand;
import marquess.command.ExitCommand;
import marquess.command.FindCommand;
import marquess.command.InvalidCommand;
import marquess.command.ListCommand;
import marquess.command.MarkCommand;
import marquess.exception.InsufficientParametersException;
import marquess.exception.InvalidParameterException;
import marquess.exception.MarquessException;
import marquess.task.Deadline;
import marquess.task.Event;
import marquess.task.Task;
import marquess.task.Todo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parser_addTodoBase_success() throws Exception {
        Field taskField = AddCommand.class.getDeclaredField("task");
        taskField.setAccessible(true);
        Field descriptionField = Task.class.getDeclaredField("description");
        descriptionField.setAccessible(true);

        Parser parser = new Parser();
        Command c = parser.parse("todo test");
        assertInstanceOf(AddCommand.class, c);
        assertInstanceOf(Todo.class, taskField.get(c));
        assertEquals("test", descriptionField.get(taskField.get(c)));
    }

    @Test
    public void parser_addTodoInsufficientParameters_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        try {
            Command c = parser.parse("todo");
            fail();
        } catch (MarquessException e) {
            assertEquals("The command has insufficient parameters: task requires - description",
                    e.getMessage());
        }
    }

    @Test
    public void parser_addDeadlineBase_success() throws Exception {
        Field taskField = AddCommand.class.getDeclaredField("task");
        taskField.setAccessible(true);
        Field descriptionField = Task.class.getDeclaredField("description");
        descriptionField.setAccessible(true);
        Field byField = Deadline.class.getDeclaredField("by");
        byField.setAccessible(true);

        Parser parser = new Parser();
        Command c = parser.parse("deadline test /by 2025-01-01");
        assertInstanceOf(AddCommand.class, c);
        assertInstanceOf(Deadline.class, taskField.get(c));
        assertEquals("test", descriptionField.get(taskField.get(c)));
        assertEquals("2025-01-01", byField.get(taskField.get(c)).toString());
    }

    @Test
    public void parser_addEventBase_success() throws Exception {
        Field taskField = AddCommand.class.getDeclaredField("task");
        taskField.setAccessible(true);
        Field descriptionField = Task.class.getDeclaredField("description");
        descriptionField.setAccessible(true);
        Field startField = Event.class.getDeclaredField("start");
        startField.setAccessible(true);
        Field endField = Event.class.getDeclaredField("end");
        endField.setAccessible(true);

        Parser parser = new Parser();
        Command c = parser.parse("event party /from 2025-08-10 /to 2025-08-10");
        assertInstanceOf(AddCommand.class, c);
        assertInstanceOf(Event.class, taskField.get(c));
        assertEquals("party", descriptionField.get(taskField.get(c)));
        assertEquals(LocalDate.parse("2025-08-10"), startField.get(taskField.get(c)));
        assertEquals(LocalDate.parse("2025-08-10"), endField.get(taskField.get(c)));
    }

    @Test
    public void parser_addEventMissingFrom_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        try {
            parser.parse("event party /to 10pm");
            fail();
        } catch (MarquessException e) {
            assertInstanceOf(InsufficientParametersException.class, e);
        }
    }

    @Test
    public void parser_addEventMissingTo_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        try {
            parser.parse("event party /from 8pm");
            fail();
        } catch (MarquessException e) {
            // Accept any error message for missing /to
            assertInstanceOf(InsufficientParametersException.class, e);
        }
    }

    @Test
    public void parser_markBase_success() throws Exception {
        Parser parser = new Parser();
        Command c = parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, c);
    }

    @Test
    public void parser_markInvalidIndex_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        try {
            parser.parse("mark one");
            fail();
        } catch (MarquessException e) {
            assertInstanceOf(InvalidParameterException.class, e);
        }
    }

    @Test
    public void parser_deleteBase_success() throws Exception {
        Parser parser = new Parser();
        Command c = parser.parse("delete 1 2 3");
        assertInstanceOf(DeleteCommand.class, c);
    }

    @Test
    public void parser_findBase_success() throws Exception {
        Parser parser = new Parser();
        Command c = parser.parse("find book");
        assertInstanceOf(FindCommand.class, c);
    }

    @Test
    public void parser_findMissingString_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        try {
            parser.parse("find");
            fail();
        } catch (MarquessException e) {
            assertInstanceOf(InsufficientParametersException.class, e);
        }
    }

    @Test
    public void parser_listBase_success() throws Exception {
        Parser parser = new Parser();
        Command c = parser.parse("list");
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    public void parser_byeBase_success() throws Exception {
        Parser parser = new Parser();
        Command c = parser.parse("bye");
        assertInstanceOf(ExitCommand.class, c);
    }

    @Test
    public void parser_invalidCommand_returnsInvalidCommand() throws Exception {
        Parser parser = new Parser();
        Command c = parser.parse("foobar");
        assertInstanceOf(InvalidCommand.class, c);
    }

    @Test
    public void parser_emptyString_returnsInvalidCommand() throws Exception {
        Parser parser = new Parser();
        Command c = parser.parse("");
        assertInstanceOf(InvalidCommand.class, c);
    }

    @Test
    public void parser_whitespaceOnly_returnsInvalidCommand() throws Exception {
        Parser parser = new Parser();
        Command c = parser.parse("   ");
        assertInstanceOf(InvalidCommand.class, c);
    }

    @Test
    public void parser_multipleSpaces_success() throws Exception {
        Parser parser = new Parser();
        Command c = parser.parse("todo    spaced   out");
        assertInstanceOf(AddCommand.class, c);
    }
}
