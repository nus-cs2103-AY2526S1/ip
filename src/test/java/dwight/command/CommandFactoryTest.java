package dwight.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Unit tests for {@link CommandFactory}. */
class CommandFactoryTest {

    /** Verifies that the "list" keyword returns a {@link ListCommand}. */
    @Test
    void getListCommandReturnsListCommand() throws Exception {
        Command cmd = CommandFactory.getCommand("list");
        assertTrue(cmd instanceof ListCommand);
    }

    /** Verifies that the "mark" keyword returns a {@link MarkCommand}. */
    @Test
    void getMarkCommandReturnsMarkCommand() throws Exception {
        Command cmd = CommandFactory.getCommand("mark");
        assertTrue(cmd instanceof MarkCommand);
    }

    /** Verifies that the "unmark" keyword returns a {@link UnmarkCommand}. */
    @Test
    void getUnmarkCommandReturnsUnmarkCommand() throws Exception {
        Command cmd = CommandFactory.getCommand("unmark");
        assertTrue(cmd instanceof UnmarkCommand);
    }

    /** Verifies that the "delete" keyword returns a {@link DeleteCommand}. */
    @Test
    void getDeleteCommandReturnsDeleteCommand() throws Exception {
        Command cmd = CommandFactory.getCommand("delete");
        assertTrue(cmd instanceof DeleteCommand);
    }

    /** Verifies that the "new" keyword returns a {@link NewCommand}. */
    @Test
    void getNewCommandReturnsNewCommand() throws Exception {
        Command cmd = CommandFactory.getCommand("new");
        assertTrue(cmd instanceof NewCommand);
    }

    /** Verifies that the "find" keyword returns a {@link FindCommand}. */
    @Test
    void getFindCommandReturnsFindCommand() throws Exception {
        Command cmd = CommandFactory.getCommand("find");
        assertTrue(cmd instanceof FindCommand);
    }

    /** Verifies that an unknown keyword results in {@link UnknownCommandException}. */
    @Test
    void getUnknownCommandThrowsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> CommandFactory.getCommand("xyz"));
    }

    /** Verifies that command lookup is case-sensitive by default. */
    @Test
    void getUppercaseCommandThrowsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> CommandFactory.getCommand("LIST"));
    }
}
