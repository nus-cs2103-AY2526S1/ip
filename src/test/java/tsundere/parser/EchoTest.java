package tsundere.parser;

import org.junit.jupiter.api.Test;
import tsundere.TsundereException;
import tsundere.command.AbstractCommand;
import tsundere.command.DeleteCommand;
import tsundere.command.EchoCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoTest {
    @Test
    public void parse_unknownInput_returnsEchoCommand() throws TsundereException {
        String typoList = "listt";
        String typoTodo = "tode task";

        AbstractCommand expected1 = new EchoCommand(typoList);
        AbstractCommand output1 = CommandParser.parse(typoList);

        AbstractCommand expected2 =new EchoCommand(typoTodo);
        AbstractCommand output2 =CommandParser.parse(typoTodo);

        assertEquals(expected1, output1,
                "unknown command should return EchoCommand");
        assertEquals(expected2, output2,
                "unknown command should return EchoCommand");
    }
}
