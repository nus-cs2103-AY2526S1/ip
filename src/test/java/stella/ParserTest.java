package stella;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import stella.exception.IncompleteInstructionException;
import stella.exception.UnknownInstructionException;

/**
 * Contains tests for the Parser class.
 */
public class ParserTest {
    @Test
    public void findCommand_unknownInstruction_exceptionThrown() {
        try {
            new Parser(new TaskList(new ArrayList<>())).findCommand("hi");
            fail();
        } catch (UnknownInstructionException e) {
            assertEquals("hi", e.getMessage());
        } catch (IncompleteInstructionException e) {
            // wrong exception thrown
            fail();
        }
    }

    @Test
    public void findCommand_incompleteInstruction_exceptionThrown() {
        try {
            new Parser(new TaskList(new ArrayList<>())).findCommand("mark");
            fail();
        } catch (IncompleteInstructionException e) {
            assertEquals("mark", e.getMessage());
        } catch (UnknownInstructionException e) {
            // wrong exception thrown
            fail();
        }
    }
}
