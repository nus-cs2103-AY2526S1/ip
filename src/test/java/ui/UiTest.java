package ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UiTest {

    @Test
    void testShowWelcomeMessage() {
        Ui ui = new Ui();

        String output = ui.showWelcome();

        assertTrue(output.contains("hi! i'm rainy!"));
    }

    @Test
    void testShowByeMessage() {
        Ui ui = new Ui();

        String output = ui.showBye();

        assertTrue(output.contains("bai bai"));
    }
}
