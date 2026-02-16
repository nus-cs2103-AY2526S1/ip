package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ByeCommandTest extends CommandTest {

    @Test
    void byeCommand_exitsProgramAndReturnsMessage() {
        String response = lmbd.getResponse("bye");
        assertEquals("Bye. Hope to see you again soon!", response);
    }

    @Test
    void byeCommand_withArguments_stillExits() {
        // Bye command doesn't care about arguments, but we can test robustness
        String response = lmbd.getResponse("bye whatever args");
        assertEquals("Bye. Hope to see you again soon!", response);
    }
}
