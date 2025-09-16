package chatbot.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    public void testParser() {
        boolean[] greetCalled = {false};
        boolean[] fallbackCalled = {false};

        Map<String, Consumer<String[]>> commands = Map.of(
                "greet", (args) -> greetCalled[0] = true,
                "", (args) -> fallbackCalled[0] = true
        );

        Parser parser = new Parser(commands);
        parser.getCommand("greet").accept(new String[]{});
        parser.getCommand("unknown").accept(new String[]{});

        assertTrue(greetCalled[0]);
        assertTrue(fallbackCalled[0]);
    }
}
