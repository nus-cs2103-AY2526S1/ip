package johnny.parser;

import johnny.ui.*;
import johnny.commands.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void testDateParse() {
        Ui ui = new Ui();
        LocalDate date = Parser.parseDate("29/03/2024", ui);
        assertEquals(LocalDate.of(2024, 3, 29), date);
    }

    @Test
    public void testDelete_validInput() {
        Ui ui = new Ui();
        String input = "delete 4";
        Command c = Parser.parseDelete(input, ui);
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void testDelete_invalidInput() {
        Ui ui = new Ui();
        String input = "delete 4.";
        Command c = Parser.parseDelete(input, ui);
        assertTrue(c instanceof ErrorCommand);
    }

    @Test
    public void testDelete_invalidInputFloat() {
        Ui ui = new Ui();
        String input = "delete 0.4";
        Command c = Parser.parseDelete(input, ui);
        assertTrue(c instanceof ErrorCommand);
    }

    @Test
    public void testMark_validInput() {
        Ui ui = new Ui();
        String input = "mark 4";
        Command c = Parser.parseMark(input, ui);
        assertTrue(c instanceof MarkCommand);
    }

    @Test
    public void testMark_invalidInput() {
        Ui ui = new Ui();
        String input = "mark 4.";
        Command c = Parser.parseMark(input, ui);
        assertTrue(c instanceof ErrorCommand);
    }

    @Test
    public void testMark_invalidInputFloat() {
        Ui ui = new Ui();
        String input = "mark 4.0";
        Command c = Parser.parseMark(input, ui);
        assertTrue(c instanceof ErrorCommand);
    }

    @Test
    public void testEvent_validInput() {
        Ui ui = new Ui();
        String input = "event Project meeting /from 25/08/2025 14:00 /to 16:00";
        Command command = Parser.parseEvent(input, ui);

        assertNotNull(command);
        assertTrue(command instanceof EventCommand);

        EventCommand c = (EventCommand) command;
        assertEquals("Project meeting", c.getName());
        assertEquals(LocalDateTime.of(2025, 8, 25, 14, 0), c.getStart());
        assertEquals(LocalTime.of(16, 0), c.getEnd());
    }

    @Test
    public void testEvent_missingEnd() {
        Ui ui = new Ui();
        String input = "event Project meeting /from 25/08/2025 14:00";

        Command command = Parser.parseEvent(input, ui);

        assertTrue(command instanceof ErrorCommand);
    }

    @Test
    void testParseDeadline_validInput() {
        Ui ui = new Ui();
        String input = "deadline Submit report /by 30/08/2025";

        Command command = Parser.parseDeadline(input, ui);

        assertNotNull(command);
        assertTrue(command instanceof DeadlineCommand);

        DeadlineCommand deadlineCommand = (DeadlineCommand) command;
        assertEquals("Submit report", deadlineCommand.getName());
        assertEquals(LocalDate.of(2025, 8, 30), deadlineCommand.getDeadline());
    }

    @Test
    void testParseDeadline_invalidDateFormat() {
        Ui ui = new Ui();
        String input = "deadline Submit report /by 2025-08-30";

        Command command = Parser.parseDeadline(input, ui);

        assertTrue(command instanceof ErrorCommand);
    }

    @Test
    void testParseTodo_validInput() {
        Ui ui = new Ui();
        String input = "todo Buy groceries";

        Command command = Parser.parseTodo(input, ui);

        assertNotNull(command);
        assertTrue(command instanceof TodoCommand);

        TodoCommand todoCommand = (TodoCommand) command;
        assertEquals("Buy groceries", todoCommand.getName());
    }

    @Test
    void testParseTodo_missingTaskName() {
        Ui ui = new Ui();
        String input = "todo";

        Command command = Parser.parseTodo(input, ui);

        assertTrue(command instanceof ErrorCommand);
    }
}
