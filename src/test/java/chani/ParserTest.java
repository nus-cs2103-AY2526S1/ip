package chani;
import chani.commands.Command;
import chani.commands.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void parse_givenListCommand_returnsListCommand() throws ChaniException{
        Command cmd = Parser.parse("list");
        assertInstanceOf(ListCommand.class, cmd);
    }
}
