import tarawrr.Parser;
import tarawrr.Command;
import tarawrr.TodoCommand;
import tarawrr.TarawrrException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void parseValidInput() throws Exception {
        String input = "todo do homework";
        Command command = Parser.parseTask(input);
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void parseInvalidInput() throws Exception {
        String input = "blah";
        try {
            Parser.parseTask(input);
        } catch (TarawrrException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        }
    }
}
