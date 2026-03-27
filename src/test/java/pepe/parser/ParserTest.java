package pepe.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pepe.command.DeadlineCommand;
import pepe.command.DeleteCommand;
import pepe.command.EventCommand;
import pepe.command.ExitCommand;
import pepe.command.ListCommand;
import pepe.command.MarkCommand;
import pepe.command.TodoCommand;
import pepe.command.UnmarkCommand;
import pepe.exception.PepeExceptions;

class ParserTest {

    @Test
    void testParserReturnsCorrectCommandInstances() throws PepeExceptions {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 1"));
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 1"));
        assertInstanceOf(TodoCommand.class, Parser.parse("todo Finish homework"));
        assertInstanceOf(DeadlineCommand.class, Parser.parse("deadline Submit report /by 2099-12-31"));
        assertInstanceOf(EventCommand.class, Parser.parse("event Meeting /from 2099-01-01 /to 2099-01-02"));
    }

    @Test
    void testInvalidCommandThrowsException() {
        PepeExceptions exception = assertThrows(PepeExceptions.class, () -> Parser.parse("foobar"));
        assertEquals("""
                These are the commands:
                Add Tasks: todo, deadline, event
                Mark/Unmark Tasks: mark, unmark
                View Tasks: list
                Delete Task: delete
                Find task: find
                """, exception.getMessage());
    }
}
