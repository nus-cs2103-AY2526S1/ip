package bob.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bob.command.AddCommand;
import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.ListCommand;
import bob.storage.Storage;
import bob.task.TaskManager;

public class ParserTest {
    private Storage storage = new Storage();
    private TaskManager manager = new TaskManager(storage);
    private final Parser parser = new Parser(manager);

    @Test
    void parser_byeCommand_byeCommandReturned() {
        Command c = parser.run("bye");
        assertTrue(c instanceof ByeCommand);
    }

    @Test
    void parser_listCommand_listCommandReturned() {
        Command c = parser.run("list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    void parser_addCommand_addTodoValid() {
        Command c = parser.run("todo sleep");
        assertTrue(c instanceof AddCommand);
        assertDoesNotThrow(() -> parser.run("todo sleep"));
    }

    @Test
    void parser_addCommand_addInvalidDeadline() {
        assertEquals(parser.run("deadline ok"), null);
    }

    @Test
    void parser_addCommand_addInvalidEvent() {
        assertEquals(parser.run("event idk /from2025-11-11 /to2025-11-12"), null);
    }

    @Test
    void parser_markCommand_markInvalidTask() {
        assertEquals(parser.run("mark 69"), null);
    }

    @Test
    void parser_unmarkCommand_unmarkInvalidTask() {
        assertEquals(parser.run("unmark 69"), null);
    }

}
