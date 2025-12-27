package Dan.Parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Dan.Command.*;

public class ParserTest {
    @Test
    public void testParseUserInput_ListCommand() {
        Command result = Parser.parseUserInput("list");
        assertInstanceOf(ListCommand.class, result);
    }

    @Test
    public void testParseUserInput_ExitCommand() {
        Command result = Parser.parseUserInput("bye");
        assertInstanceOf(ExitCommand.class, result);
    }

    @Test
    public void testParseUserInput_MarkCommand() {
        Command result = Parser.parseUserInput("mark 1");
        assertInstanceOf(MarkCommand.class, result);
    }
}
