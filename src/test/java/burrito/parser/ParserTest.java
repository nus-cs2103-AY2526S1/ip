package burrito.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void dateParseTest(){
        assertEquals("Aug 31 2025", Parser.dateParser("2025/08/31"));
    }

    @Test
    public void timeParseTest(){
        assertEquals("09:00 AM", Parser.timeParser("0900"));
    }
}
