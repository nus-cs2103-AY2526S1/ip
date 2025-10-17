package lux.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import lux.commands.Command;
import lux.data.AliasList;
import lux.exception.LuxException;

public class ParserTest {
    @Test
    public void parse_knownCommands_noArgs() throws LuxException {
        AliasList aliases = new AliasList();
        Command c = Parser.parse("list", aliases);
        assertEquals("lux.commands.ListCommand", c.getClass().getName());

        c = Parser.parse("bye", aliases);
        assertEquals("lux.commands.ByeCommand", c.getClass().getName());
    }

    @Test
    public void parse_unknownCommand_throws() {
        AliasList aliases = new AliasList();
        assertThrows(LuxException.class, () -> Parser.parse("nope", aliases));
    }
}
