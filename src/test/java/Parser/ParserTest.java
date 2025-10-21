package Parser;

import Command.DeadlineCommand;
import JohnException.JohnException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void deadline_valid_parsed() throws Exception {
        Parser p = new Parser();
        assertTrue(p.parse("deadline submit report /by 2025-09-30") instanceof DeadlineCommand);
    }

    @Test
    void deadline_invalid_throws() {
        Parser p = new Parser();
        assertThrows(JohnException.class, () -> p.parse("deadline x due 2025-09-30"));
    }
}