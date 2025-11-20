package jobe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

public class CommandTypeTest {
    
    @Test
    public void stringToCommand_validCommand_returnsCommandType() throws JobeException {
        CommandType cmdType = CommandType.stringToCommand("LIST");
        assertEquals(CommandType.LIST, cmdType);
    }
    
    @Test
    public void stringToCommand_invalidCommand_throwsException() {
        JobeException exception = assertThrows(
                JobeException.class, () -> CommandType.stringToCommand("HELP")
        );
        assertEquals("OOPS!!!! I'm Sorry, but I do not recognise the command.", exception.getMessage());
    }
}
