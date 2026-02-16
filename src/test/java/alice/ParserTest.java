package alice;

import alice.command.*;
import alice.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_emptyInput_throwsMissingParameterException() {
        assertThrows(MissingParameterException.class, () -> Parser.parse(""));
    }

    @Test
    public void parse_list_returnsListCommand() throws AliceException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_todoWithoutDescription_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parse_deadlineWithoutBy_throwsMissingParameterException() {
        assertThrows(MissingParameterException.class, () -> Parser.parse("deadline return book"));
    }

    @Test
    public void parse_deleteNonNumber_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("delete abc"));
    }

    @Test
    public void parse_bye_returnsExitCommand() throws AliceException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }
}
