package bro.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void printHello_returnsCorrectMessage() {
        String msg = Ui.printHello();
        assertTrue(msg.contains("Hello bro! I'm Bro"));
        assertTrue(msg.contains("What can a brother do for you?"));
    }
}
