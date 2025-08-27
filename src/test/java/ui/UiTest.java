package ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void testShowWelcomeMessage() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.showWelcome();

        String output = outContent.toString();
        assertTrue(output.contains("hi! i'm rainy!"));
    }

    @Test
    void testShowByeMessage() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ui.showBye();

        String output = outContent.toString();
        assertTrue(output.contains("bai bai"));
    }
}