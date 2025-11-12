package seedu.haru;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    void testShowWelcome() {
        Ui ui = new Ui();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.showWelcome("HARU");

        String expected =
                "    --------------------------------------\n" +
                "    Hello! I'm\n" + "HARU\n" + "    What can I do for you today?\n" +
                "    --------------------------------------\n";

        assertEquals(expected, outContent.toString());

        System.setOut(System.out);
        ui.close();
    }

    @Test
    void testShowGoodbye() {
        Ui ui = new Ui();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.showGoodbye();

        String expected = "\n" +
                "    --------------------------------------\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    --------------------------------------\n";

        assertEquals(expected, outContent.toString());

        System.setOut(System.out);
        ui.close();
    }

    @Test
    void testShowError() {
        Ui ui = new Ui();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.showError("Error Message");

        String expected = "\n" +
                "    --------------------------------------\n" +
                "    Error Message\n" +
                "    --------------------------------------\n";

        assertEquals(expected, outContent.toString());

        System.setOut(System.out);
        ui.close();
    }

    @Test
    void testShowMessage() {
        Ui ui = new Ui();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.showMessage("Hello, user!");

        String expected = "\n" +
                "    --------------------------------------\n" +
                "Hello, user!\n" +
                "    --------------------------------------\n";

        assertEquals(expected, outContent.toString());

        System.setOut(System.out);
        ui.close();
    }
}
