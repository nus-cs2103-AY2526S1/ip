package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_byeCommand_returnsByeType(){
        Parser.Command result = Parser.parse("bye");
        assertEquals(Parser.CommandType.BYE, result.type);
    }

    @Test
    public void parse_listCommand_returnsListType(){
        Parser.Command result = Parser.parse("list");
        assertEquals(Parser.CommandType.LIST, result.type);
    }
}