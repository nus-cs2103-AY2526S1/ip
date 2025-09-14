package tsundere.parser;

import org.junit.jupiter.api.Test;
import tsundere.TsundereException;
import tsundere.command.AbstractCommand;
import tsundere.command.ListCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTest {
    @Test
    public void parse_listInput_returnsListCommand() throws TsundereException {
        String list = "list";

        AbstractCommand expected = new ListCommand();
        AbstractCommand output = CommandParser.parse(list);

        assertEquals(expected, output,
                "valid list command should return ListCommand");
    }
}
