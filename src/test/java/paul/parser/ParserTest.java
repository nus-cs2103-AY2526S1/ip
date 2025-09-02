package paul.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void getCommandType_todoCommand_shouldReturnTODO() {
        String input = "todo finish homework";
        Parser.CommandType result = Parser.getCommandType(input);
        assertEquals(Parser.CommandType.TODO, result, "Should return TODO for 'todo' command");
    }

    @Test
    public void getCommandType_unknownCommand_shouldReturnUNKNOWN() {
        String input = "paul";
        Parser.CommandType result = Parser.getCommandType(input);
        assertEquals(Parser.CommandType.UNKNOWN, result, "Should return UNKNOWN for unknown command");
    }
}
