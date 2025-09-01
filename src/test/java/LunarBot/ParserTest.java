package LunarBot;

import LunarBot.Command.Command;
import LunarBot.Command.DeleteCommand;
import LunarBot.Command.ListCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void testListCommand() {
        Command listCommand = Parser.parse("list");
        assertTrue(listCommand instanceof ListCommand);
    }

    @Test
    void testDeleteCommand() {
        Command deleteCommand = Parser.parse("delete 1");
        assertTrue(deleteCommand instanceof DeleteCommand);
    }
}
