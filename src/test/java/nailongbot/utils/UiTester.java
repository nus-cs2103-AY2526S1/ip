package nailongbot.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;

// Tester for Ui Class
class UiTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;
    private Ui ui;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ui = new Ui();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        ui.close();
    }

    @Test
    void testPrintWelcome() {
        ui.printWelcome();
        String output = outContent.toString();
        assertTrue(output.contains("Hello! I'm jkbot"));
        assertTrue(output.contains("What can I do for you?"));
    }

    @Test
    void testPrintLine() {
        ui.printLine();
        assertEquals(Ui.LINE, outContent.toString());
    }

    @Test
    void testPrintMessage() {
        String message = "Test message";
        ui.printMessage(message);
        assertEquals(message + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testPrintGoodbye() {
        ui.printGoodbye();
        String output = outContent.toString();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

    // remove readCommand test since Ui no longer provides it
}
