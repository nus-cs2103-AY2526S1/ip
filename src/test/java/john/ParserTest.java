package john;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void getCommand() {
        assertEquals(Command.MARK, Parser.parseCommand("mark 3"));
        assertEquals(Command.WRONG, Parser.parseCommand("mark"));
        assertEquals(Command.WRONG, Parser.parseCommand("mark this"));
        assertEquals(Command.UNMARK, Parser.parseCommand("UNMARK 3"));
        assertEquals(Command.WRONG, Parser.parseCommand("unmark "));
        assertEquals(Command.WRONG, Parser.parseCommand("unmark what"));
        assertEquals(Command.LIST, Parser.parseCommand("list"));
        assertEquals(Command.TODO, Parser.parseCommand("todo aaaa"));
        assertEquals(Command.TODO, Parser.parseCommand("TODO "));
        assertEquals(Command.WRONG, Parser.parseCommand("todos"));
        assertEquals(Command.DEADLINE, Parser.parseCommand("deadline"));
        assertEquals(Command.WRONG, Parser.parseCommand("deadlines"));
        assertEquals(Command.EVENT, Parser.parseCommand("event "));
        assertEquals(Command.WRONG, Parser.parseCommand("events today /from a /to b"));
        assertEquals(Command.DELETE, Parser.parseCommand("delete 3"));
        assertEquals(Command.WRONG, Parser.parseCommand("delete a"));
        assertEquals(Command.BYE, Parser.parseCommand("BYE"));
    }
}
