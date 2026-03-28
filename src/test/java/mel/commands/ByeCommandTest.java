package mel.commands;

import mel.apps.StorageStub;
import mel.apps.UiStub;
import mel.exceptions.MelException;
import mel.tasks.TaskListStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest  {
    private static UiStub ui = new UiStub();

    @Test
    public void testConstructor() throws MelException {
        // test case 1: extra argument after bye
        MelException e1 = assertThrows(MelException.class, () ->  new ByeCommand("123123123"));
        assertTrue(e1.getMessage().contains("Are you trying to type"));

        //test case 2: no argument after bye
        assertDoesNotThrow(() -> new ByeCommand(""));


    }

    @Test
    public void testisExit() throws MelException {
        Command bye = new ByeCommand("");
        assertEquals(true, bye.isExit());

    }

}
