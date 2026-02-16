package bubbles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parse_Error() throws Exception{
        try {
            String command = "haii";
            Parser.parse(command);

        } catch (Exception e) {
            assertEquals("Sawwryy... that one bounced right off my bubblehead! I don't understand it yet T.T", e.getMessage());
        }
    }

    @Test
    public void parse_todoCommand_returnsTodoCommand() throws Exception {
        Command c = Parser.parse("todo complete iP");
        assertTrue(c instanceof TodoCommand);
    }

    @Test
    public void parse_deadlineCommand_returnsDeadlineCommand() throws Exception {
        Command c = Parser.parse("deadline submit report /by 2025-08-29 14:25");
        assertTrue(c instanceof DeadlineCommand);
    }

    @Test
    public void parse_eventCommand_returnsEventCommand() throws Exception {
        Command c = Parser.parse("event meeting /from 2025-08-29 14:25 /to 2025-08-29 15:25");
        assertTrue(c instanceof EventCommand);
    }

    @Test
    public void parse_markCommand_returnsMarkCommand() throws Exception {
        Command c = Parser.parse("mark 2");
        assertTrue(c instanceof MarkCommand);
    }

    @Test
    public void parse_unmarkCommand_returnsUnmarkCommand() throws Exception {
        Command c = Parser.parse("unmark 3");
        assertTrue(c instanceof UnmarkCommand);
    }

    @Test
    public void parse_deleteCommand_returnsDeleteCommand() throws Exception {
        Command c = Parser.parse("delete 1");
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws Exception {
        Command c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);
    }

}
