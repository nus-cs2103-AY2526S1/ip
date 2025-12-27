package tension.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void dummyTest() {
        Command command = new Command(0, "help", true, "help me");
        assertEquals(0, command.getIndex());
        assertEquals("help", command.getCommandName());
        assertEquals(true, command.getIsMarked());
        assertEquals("help me", command.getFullWord());
    }
}
