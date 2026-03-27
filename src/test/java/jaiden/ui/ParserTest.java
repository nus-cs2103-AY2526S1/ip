package jaiden.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jaiden.command.AddCommand;
import jaiden.command.ChangeMarkCommand;
import jaiden.command.DeleteCommand;
import jaiden.command.ExitCommand;
import jaiden.command.ListCommand;
import jaiden.command.UnknownCommand;
import jaiden.exception.JaidenException;

public class ParserTest {
    @Test
    public void parseTest_validFormat_success() throws Exception {
        assertEquals(new ListCommand(new String[]{"list"}), Parser.parse("list"));
        assertEquals(new ListCommand(new String[]{"show", "2025-08-22"}), Parser.parse("show", "2025-08-22"));
        assertEquals(new ListCommand(new String[]{"find", "read"}), Parser.parse("find", "read"));
        assertEquals(new ChangeMarkCommand(new String[]{"mark", "1"}), Parser.parse("mark", "1"));
        assertEquals(new ChangeMarkCommand(new String[]{"unmark", "1"}), Parser.parse("unmark", "1"));
        assertEquals(new AddCommand(new String[]{"todo", "test"}), Parser.parse("todo", "test"));
        assertEquals(new AddCommand(new String[]{"deadline", "test", "/by", "2025-08-22"}),
                Parser.parse("deadline", "test", "/by", "2025-08-22"));
        assertEquals(new AddCommand(new String[]{"event", "test", "/from", "2025-08-22", "/to", "2025-08-22"}),
                Parser.parse("event", "test", "/from", "2025-08-22", "/to", "2025-08-22"));
        assertEquals(new DeleteCommand(new String[]{"delete", "1"}), Parser.parse("delete", "1"));
        assertEquals(new ExitCommand(new String[]{"bye"}), Parser.parse("bye"));
        assertEquals(new UnknownCommand(new String[]{"test"}), Parser.parse("test"));
    }

    @Test
    public void parseTest_invalidFormat_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (Exception e) {
            assertEquals(new JaidenException("Oops! 😅 Looks like the description of a todo is missing. "
                            + "Could you fill that in for me?").getMessage(), e.getMessage());
        }
    }
}
