package dyke.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dyke.data.Library;
import dyke.ui.FakeUi;


public class ParserTest {
    private Parser parser;

    @Test
    public void testFailedDeadline() {
        FakeUi ui = new FakeUi();
        parser = new Parser(new Library(), ui, new Storage("./data/dyke.txt"));

        parser.parseCommand(new String[]{"deadline"});

        assertEquals("I think you forgot the description...", ui.getLastMessage());

        parser.parseCommand(new String[]{"deadline", "this test /by "});

        assertEquals("When should this be done by?", ui.getLastMessage());

        parser.parseCommand(new String[]{"deadline", "this test /by 2025/04-05"});

        assertEquals("Invalid datetime: 2025/04-05", ui.getLastMessage());
    }

    @Test
    public void testFailedEvent() {
        FakeUi ui = new FakeUi();
        parser = new Parser(new Library(), ui, new Storage("./data/dyke.txt"));

        // Incomplete command, no dates
        parser.parseCommand(new String[]{"event", "this test"});

        assertEquals("You know, I kinda gotta know when it's gonna happen."
                + "\n\t format: /from *day-time* /to *day-time*", ui.getLastMessage());


        // Incomplete command, no /to date
        parser.parseCommand(new String[]{"event", "this test /from 2025/04/05"});;

        assertEquals("You know, I kinda gotta know when it's gonna happen."
                + "\n\t format: /from *day-time* /to *day-time*", ui.getLastMessage());

        // Invalid command, datetime in wrong format
        parser.parseCommand(new String[]{"event", "this test /from 2024/-04/05 /to 2024/-04/05"});;

        assertEquals("Invalid datetime: 2024/-04/05", ui.getLastMessage());
    }
}
