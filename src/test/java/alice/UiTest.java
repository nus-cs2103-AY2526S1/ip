package alice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        ui = new Ui();
    }

    @Test
    public void greet_printsGreetingMessage() {
        ui.greet();
        String output = outputStream.toString();

        assertTrue(output.contains("Hello! I'm " + ui.getBotName()));
        assertTrue(output.contains("What can I do for you?"));
    }

    @Test
    public void exit_printsExitMessage() {
        ui.exit();
        String output = outputStream.toString();

        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

}
