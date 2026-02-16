package bambam;


import bambam.command.Command;
import bambam.command.EventCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Contains unit tests for testing the behaviour of Parser class.
 */
public class ParserTest {
    /**
     * Tests that parse() method will return the Event command
     * when an event command input is passed.
     * @throws BambamException
     */
    @Test
    public void parseTest() throws BambamException {
        String fullCommand = "event dance /from 2025-01-01 1800 /to 2025-01-01 2100";
        Parser parser = new Parser();
        Command command = parser.parse(fullCommand);
        assertInstanceOf(EventCommand.class, command);
    }
}
