package ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void chatbotPrint_normalPrint_success() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Random r = new Random();
        Ui u = new Ui();
        for(int i = 0; i < 100; i++) {
            String generatedString = r.ints(0, 200 + 1)
                    .limit(10)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            u.chatbotPrint(generatedString);

            assertEquals("    " + generatedString + System.lineSeparator(), outputStreamCaptor.toString());
            outputStreamCaptor.reset();
        }
        System.setOut(System.out);
    }

    @Test
    public void getInput_closedScanner_exceptionThrown() {
        String data = "testing Ui\r\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Ui u = new Ui();
        u.printLine();
        try {
            u.close();
            String s = u.getInput();
        } catch (Exception e) {
            assertEquals("Scanner closed", e.getMessage());
        } finally {
            System.setIn(stdin);
        }
    }
}
