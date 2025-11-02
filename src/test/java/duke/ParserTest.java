package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Parser class.
 */
public class ParserTest {
    @Test
    public void parse_byeCommand_returnsByeType() {
        Parser.Command result = Parser.parse("bye");
        assertEquals(Parser.CommandType.BYE, result.getType());
    }

    @Test
    public void parse_listCommand_returnsListType() {
        Parser.Command result = Parser.parse("list");
        assertEquals(Parser.CommandType.LIST, result.getType());
    }
}
