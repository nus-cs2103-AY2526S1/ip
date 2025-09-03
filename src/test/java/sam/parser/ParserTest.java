package sam.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    
    @Test
    public void testParseSimpleCommand() {
        String[] result = Parser.parse("list");
        assertEquals("list", result[0]);
        assertEquals("", result[1]);
    }
    
    @Test
    public void testParseCommandWithArguments() {
        String[] result = Parser.parse("todo buy groceries");
        assertEquals("todo", result[0]);
        assertEquals("buy groceries", result[1]);
    }
}
