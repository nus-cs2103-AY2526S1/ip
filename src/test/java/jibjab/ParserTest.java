package jibjab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseDeadline() {
        String input = "deadline test /by Jan 07 2077 18:00";
        String actual = Parser.parseDeadline(input)[1];
        assertEquals("Jan 07 2077 18:00", actual);
    }

    @Test
    public void parseEvent() {
        String input = "event blah /from Jan 01 2077 18:00 /to 01/02/2088 18:00";
        String actualFrom = Parser.parseEvent(input)[1];
        String actualTo = Parser.parseEvent(input)[2];
        assertEquals("Jan 01 2077 18:00", actualFrom);
        assertEquals("01/02/2088 18:00", actualTo);
    }
}
