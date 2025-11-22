import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import command.AddCommand;
import command.ExitCommand;
import exception.GenieweenieException;
import parser.Parser;

/**
 * Tests for {@link parser.Parser}.
 */
public class ParserTest {

    @Test
    public void parsExitCommandReturnsExitCommand() throws GenieweenieException {
        assert(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parseAddCommandEmptyDescriptionThrowsException() {
        assertThrows(GenieweenieException.class, () -> Parser.parse("add"));
    }

    @Test
    public void parseUnknownCommandDefaultsToAdd() throws GenieweenieException {
        assert(Parser.parse("some random text") instanceof AddCommand);
    }
}
