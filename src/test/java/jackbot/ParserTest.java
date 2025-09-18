package jackbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void parse_simpleCommands_caseInsensitive_andTrimmed() throws JackbotException {
        assertEquals(Parser.Type.LIST, parser.parse("list").type);
        assertEquals(Parser.Type.LIST, parser.parse("  LIST  ").type);
        assertEquals(Parser.Type.BYE,  parser.parse("Bye").type);
    }

    @Test
    void parse_mark_parsesIndex_orThrows() throws JackbotException {
        var r = parser.parse("mark 3");
        assertEquals(Parser.Type.MARK, r.type);
        assertEquals(3, r.index);

        JackbotException ex = assertThrows(JackbotException.class,
                () -> parser.parse("mark three"));
        assertTrue(ex.getMessage().toLowerCase().contains("parse task index"));
    }

    @Test
    void parse_unmark_and_delete() throws JackbotException {
        assertEquals(2, parser.parse("unmark 2").index);
        assertEquals(10, parser.parse("delete 10").index);
    }

    @Test
    void parse_todo_deadline_event_capturesPayload() throws JackbotException {
        var t = parser.parse("todo   buy milk  ");
        assertEquals(Parser.Type.TODO, t.type);
        assertEquals("buy milk", t.text);

        var d = parser.parse("deadline return book /by Sunday 5pm");
        assertEquals(Parser.Type.DEADLINE, d.type);
        assertEquals("return book /by Sunday 5pm", d.text);

        var e = parser.parse("event project sync /at Mon 2-3pm ");
        assertEquals(Parser.Type.EVENT, e.type);
        assertEquals("project sync /at Mon 2-3pm", e.text);
    }

    @Test
    void parse_unknownCommand_throws() {
        assertThrows(JackbotException.class, () -> parser.parse("abracadabra"));
    }
}
