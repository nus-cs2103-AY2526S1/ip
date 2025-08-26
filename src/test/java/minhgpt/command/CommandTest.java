package minhgpt.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void saveLoadTest() {
        Command.initialise();
        assertEquals(Command.parseCommand("bye") instanceof CommandBye, true);
        assertEquals(Command.parseCommand("list") instanceof CommandList, true);
        assertEquals(Command.parseCommand(" ") instanceof CommandAdd, true);
        assertEquals(Command.parseCommand("mark 1") instanceof CommandMark, true);
        assertEquals(Command.parseCommand("unmark 1") instanceof CommandUnmark, true);
        assertEquals(Command.parseCommand("delete 1") instanceof CommandDelete, true);
    }
}
