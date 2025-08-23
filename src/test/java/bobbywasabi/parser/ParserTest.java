package bobbywasabi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bobbywasabi.exceptions.BobbyWasabiException;

public class ParserTest {

    @Test
    public void isValidInteger_wrongCommandInputs_exceptionThrown() {
        try {
            assertEquals(false,
                    Parser.isValidInteger("mark 3 seventeen", 1));
            fail();
        } catch (BobbyWasabiException e) {
            assertEquals("We only accept two inputs - the command and the integer",
                    e.getMessage());
        }
    }

    @Test
    public void isValidInteger_indexOutOfBounds_exceptionThrown() {
        try {
            assertEquals(false,
                    Parser.isValidInteger("mark 17", 1));
            fail();
        } catch (BobbyWasabiException e) {
            assertEquals("Index given in input is out of range, "
                    + "please try an index within the range of your list", e.getMessage());
        }
    }

    @Test
    public void isValidInteger_notValidNumber_exceptionThrown() {
        try {
            assertEquals(false,
                    Parser.isValidInteger("mark 17silk", 1));
            fail();
        } catch (BobbyWasabiException e) {
            assertEquals("Please input an index following your command", e.getMessage());
        }
    }

}
