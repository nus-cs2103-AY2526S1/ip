package clare.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testWelcome() {
        UI ui = new UI();
        ui.welcome();
        String expectedOutput =
                "Hello, I am Clare!\n"
                        + "So happy to see you today.\n"
                        + "What can I help?";
        String output = outContent.toString();
        assertTrue(output.contains(expectedOutput));
    }

    @Test
    void testFarewell() {
        UI ui = new UI();
        ui.farewell();
        String expectedOutput = "Bye dear. I will miss you!";
        String output = outContent.toString();
        assertTrue(output.contains(expectedOutput));
    }

    @Test
    void testShowMessage() {
        UI ui = new UI();
        String message = "This is a test message.";
        ui.showMessage(message);
        String output = outContent.toString();
        assertTrue(output.contains(message));
    }
}
