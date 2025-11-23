package jinwoo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jinwoo.exception.DukeException;

class ParserTest {

    @Test
    void parseToDate_inValidDate() {
        try {
            Parser.parseToDate("1111-11-111");
            fail();
        } catch (Exception e) {
            assertEquals("Warning ??? : Please enter date in the format yyyy-MM-dd.", e.getMessage());
        }
    }

    @Test
    void parseCommand_lessArguement() {
        try {
            Parser.parseCommand(" deadline return book /by   ");
            fail();
        } catch (DukeException e) {
            assertEquals("Warning ??? : Missing command input.", e.getMessage());
        }
    }

    @Test
    void parseCommand_wrongAction() {
        try {
            Parser.parseCommand(" hello return book /by   ");
            fail();
        } catch (DukeException e) {
            assertEquals("Warning ??? : Wrong action.", e.getMessage());
        }
    }
}
