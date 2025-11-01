package yuan.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITest {
    private final ByteArrayOutputStream printedOutput = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private UI ui;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(printedOutput));
        ui = new UI();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void showWelcomeMessage() {
        ui.showWelcome();
        String output = printedOutput.toString();
        assertTrue(output.contains("Hello... I'm Yuan"));
        assertTrue(output.contains("Why are you bothering me"));
    }

    @Test
    public void showByeMessage() {
        ui.showBye();
        String output = printedOutput.toString();
        assertTrue(output.contains("Bye. I don't wanna see you again"));
    }
}