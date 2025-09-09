package minhgpt.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void parseCommand_allCommands() {
        Command.initialise();
        assertEquals(Command.parseCommand("bye") instanceof CommandBye, true);
        assertEquals(Command.parseCommand("list") instanceof CommandList, true);
        assertEquals(Command.parseCommand("todo bruh") instanceof CommandAdd, true);
        assertEquals(Command.parseCommand("deadline bruh") instanceof CommandAdd, true);
        assertEquals(Command.parseCommand("event bruh") instanceof CommandAdd, true);
        assertEquals(Command.parseCommand("mark 1") instanceof CommandMark, true);
        assertEquals(Command.parseCommand("unmark 1") instanceof CommandUnmark, true);
        assertEquals(Command.parseCommand("delete 1") instanceof CommandDelete, true);
        assertEquals(Command.parseCommand("find bruh") instanceof CommandFind, true);
        assertEquals(Command.parseCommand("lmao") instanceof CommandInvalid, true);
    }
}
