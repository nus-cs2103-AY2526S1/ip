package marcus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParserMark() {
        ArrayList<String> result = new ArrayList<>();
        result.add("mark");
        result.add("4");
        assertEquals(result, Parser.parseCommand("mark 4"));
    }

    @Test
    public void testParserUnmark() {
        ArrayList<String> result = new ArrayList<>();
        result.add("unmark");
        result.add("4");
        assertEquals(result, Parser.parseCommand("unmark 4"));
    }

    @Test
    public void testParserToDo() {
        ArrayList<String> result = new ArrayList<>();
        result.add("toDo");
        result.add("sleep in");
        assertEquals(result, Parser.parseCommand("todo sleep in"));
    }

    @Test
    public void testParserInvalidDeadline() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Invalid Command");
        assertEquals(result, Parser.parseCommand("deadline sleep /by 2025"));
    }

    @Test
    public void testParserValidDeadline() {
        ArrayList<String> result = new ArrayList<>();
        result.add("deadline");
        result.add("sleep");
        result.add("2025-09-03");
        assertEquals(result, Parser.parseCommand("deadline sleep /by 2025-09-03"));
    }

    @Test
    public void testParserInvalidDeadlineDate() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Invalid Command");
        assertEquals(result, Parser.parseCommand("deadline sleep /by 2025-13-03"));
    }

    @Test
    public void testParserInvalidCommand() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Invalid Command");
        assertEquals(result, Parser.parseCommand("task please"));
    }
}
