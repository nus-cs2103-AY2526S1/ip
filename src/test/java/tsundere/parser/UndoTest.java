package tsundere.parser;

import org.junit.jupiter.api.Test;
import tsundere.TsundereException;
import tsundere.command.AbstractCommand;
import tsundere.command.UndoCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UndoTest {
    @Test
    public void parse_undoInput_returnsUndoCommand() throws TsundereException {
        String undo = "undo";

        AbstractCommand expected = new UndoCommand();
        AbstractCommand output = CommandParser.parse(undo);

        assertEquals(expected, output,
                "valid undo command should return UndoCommand");
    }
}
