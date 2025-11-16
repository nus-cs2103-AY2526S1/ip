package tuesday.parser;
import org.junit.jupiter.api.Test;
import tuesday.command.Command;
import tuesday.command.AddCommand;
import tuesday.exception.TuesdayException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {
    @Test
    public void parseValidTodoTest() throws TuesdayException {
        Command cmd = Parser.parse("todo read book");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    public void parseInvalidTodoTest() {
        assertThrows(TuesdayException.class, () -> {
            Parser.parse("deadline finish homework"); // missing /by part of input
        });
    }
}
