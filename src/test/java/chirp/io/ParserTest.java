package chirp.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void findAttributeTest() {
        assertEquals("here maybe", Parser.extractAttribute("deadline smth /fid random /at here maybe", "/at"));
        assertEquals("random 2 3", Parser.extractAttribute("deadline smth /fid random 2 3/at here maybe", "/fid"));
    }

    @Test
    public void attributeNotFoundTest() {
        assertEquals("", Parser.extractAttribute("/find /find /find /smth /else", "/find"));
        assertEquals("", Parser.extractAttribute("deadline smth /fid random /finde else", "/find"));
    }
}
