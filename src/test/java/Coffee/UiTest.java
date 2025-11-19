package Coffee;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {

    @Test
    void showMessage_printsMessage() {
        Ui ui = new Ui();

        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        try {
            ui.showMessage("hello world");
        } finally {
            System.setOut(originalOut);
        }

        String printed = out.toString();
        assertTrue(printed.contains("hello world"),
                "Expected printed output to contain the message");
    }

    @Test
    void showLoadingError_printsErrorMessage() {
        Ui ui = new Ui();

        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        try {
            ui.showLoadingError();
        } finally {
            System.setOut(originalOut);
        }

        String printed = out.toString();
        assertTrue(printed.contains("There is an error with loading the file!"),
                "Expected printed output to contain the loading error message");
    }
}
