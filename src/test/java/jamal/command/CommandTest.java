package jamal.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

    @Test
    public void notExitTest() {
        assertEquals(new DeleteCommand(0).isExit(),false);
        assertEquals(new ListCommand().isExit(), false);
        assertEquals(new MarkCommand(0).isExit(), false);
        assertEquals(new UnmarkCommand(0).isExit(), false);
    }

    @Test
    public void exitTest() {
        assertEquals(new ExitCommand().isExit(),true);
    }
}