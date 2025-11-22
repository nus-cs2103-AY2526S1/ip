package utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UiTest {
    Ui ui = new Ui();

    @Test
    public void echoTest() {
        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Code under test
        ui.echo("Hello World!");

        // Restore original System.out
        System.setOut(originalOut);

        // assertEquals(outContent.toString(), "\tHello World!" + System.lineSeparator());
    }

    @Test
    public void showErrorTest() {
        // Capture System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Code under test
        ui.showError("Hello World!");

        // Restore original System.out
        System.setOut(originalOut);

        // assertEquals(outContent.toString(), "Error: Hello World!" + System.lineSeparator());
    }
}
