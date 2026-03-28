package aurora.command;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandReaderTest {
    @Test
    void read_validListCommand_returnsListCommand() {
        Command cmd = CommandReader.read("list");
        assertInstanceOf(ListCommand.class, cmd);
    }

    @Test
    void read_invalidListCommand_returnsInvalidCommand() {
        Command cmd = CommandReader.read("list hello");
        assertInstanceOf(InvalidCommand.class, cmd);
        assertTrue(cmd.execute(null).contains("Invalid list command"));
    }

    @Test
    void read_validHelpCommand_returnsHelpCommand() {
        Command cmd = CommandReader.read("help");
        assertInstanceOf(HelpCommand.class, cmd);
    }

    @Test
    void read_invalidHelpCommand_returnsInvalidCommand() {
        Command cmd = CommandReader.read("help now");
        assertInstanceOf(InvalidCommand.class, cmd);
        assertTrue(cmd.execute(null).contains("Invalid help command"));
    }

    @Test
    void read_validMarkCommand_returnsMarkCommand() {
        Command cmd = CommandReader.read("mark 3");
        assertInstanceOf(MarkCommand.class, cmd);
    }

    @Test
    void read_invalidMarkCommand_returnsInvalidCommand() {
        Command cmd = CommandReader.read("mark alpha beta");
        assertInstanceOf(InvalidCommand.class, cmd);
        assertTrue(cmd.execute(null).contains("Invalid mark command"));
    }

    @Test
    void read_validDeleteCommand_returnsDeleteCommand() {
        Command cmd = CommandReader.read("delete 2");
        assertInstanceOf(DeleteCommand.class, cmd);
    }

    @Test
    void read_invalidDeleteCommand_returnsInvalidCommand() {
        Command cmd = CommandReader.read("delete gamma delta");
        assertInstanceOf(InvalidCommand.class, cmd);
        assertTrue(cmd.execute(null).contains("Invalid delete command"));
    }

    @Test
    void read_validTodoTask_returnsAddCommand() {
        Command cmd = CommandReader.read("TODO read a book");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void read_validDeadlineTask_returnsAddCommand() {
        Command cmd = CommandReader.read("DEADLINE math quiz /by: 121225");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void read_validEventTask_returnsAddCommand() {
        Command cmd = CommandReader.read("EVENT basketball /from: 121225 /to: 131225");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void read_invalidTask_returnsInvalidCommand() {
        Command cmd = CommandReader.read("todo");
        assertInstanceOf(InvalidCommand.class, cmd);
        assertTrue(cmd.execute(null).contains("Invalid todo format"));
    }

    @Test
    void read_invalidInput_returnsInvalidCommand() {
        Command cmd = CommandReader.read("There is nothing here to see.");
        assertInstanceOf(InvalidCommand.class, cmd);
    }
}
