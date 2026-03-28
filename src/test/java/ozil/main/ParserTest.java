package ozil.main;

import ozil.command.FindTaskCommand;
import ozil.command.ListTasksByTimeCommand;
import ozil.command.TerminatingCommand;
import ozil.command.ListTasksCommand;
import ozil.command.DeleteTaskCommand;
import ozil.command.AddEventTaskCommand;
import ozil.command.AddDeadlineTaskCommand;
import ozil.command.AddTodoTaskCommand;
import ozil.command.MarkTaskCommand;
import ozil.exception.OzilException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void parserTest() throws OzilException {
        assertTrue(Parser.handleInput("bye") instanceof TerminatingCommand);
        assertTrue(Parser.handleInput("list") instanceof ListTasksCommand);
        assertTrue(Parser.handleInput("mark 2") instanceof MarkTaskCommand);
        assertTrue(Parser.handleInput("delete 2") instanceof DeleteTaskCommand);
        assertTrue(Parser.handleInput("todo todo") instanceof AddTodoTaskCommand);
        assertTrue(Parser.handleInput("deadline deadline /by 25-09-2025 2300") instanceof AddDeadlineTaskCommand);
        assertTrue(Parser.handleInput("event event /from 29-09-2025 /to 1800") instanceof AddEventTaskCommand);
        assertTrue(Parser.handleInput("latest") instanceof ListTasksByTimeCommand);
        assertTrue(Parser.handleInput("find homework") instanceof FindTaskCommand);
    }
}
