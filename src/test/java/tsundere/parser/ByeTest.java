package tsundere.parser;

import org.junit.jupiter.api.Test;
import tsundere.TsundereException;
import tsundere.command.AbstractCommand;
import tsundere.command.ByeCommand;
import tsundere.command.DeleteCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeTest {
    @Test
    public void parse_validByeInput_returnsByeCommand() throws TsundereException {
        String bye = "bye";

        AbstractCommand expected = new ByeCommand();
        AbstractCommand output = CommandParser.parse(bye);

        assertEquals(expected, output,
                "valid bye command should return ByeCommand");
    }
}
