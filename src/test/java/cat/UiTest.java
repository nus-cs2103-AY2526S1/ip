package cat;

import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testShowWelcome() {
        String actual = Ui.showWelcome();
        String expected = "Hello! I am your neighbourhood stray cat.\n"
                        + "What can I do for you?\n";
        assertEquals(expected, actual);
    }

    @Test
    void testShowAsk() {
        String actual = Ui.showAsk();
        String expected = "Enter your meows for me to record down\n"
                + "or <list> to show all your recorded inputs\n"
                + "or <bye> to exit.";
        assertEquals(expected, actual);
    }

    @Test
    void testShowBye() {
        String actual = Ui.showBye();
        String expected = "Bye. Hope to see you again soon with Chicken Cat Treats\n";
        assertEquals(expected, actual);
    }

    @Test
    void testShowInput() {
        String input = "hello";
        String expected = "You have Meowed: hello\n";
        String actual = Ui.showInput(input);
        assertEquals(expected, actual);
    }
}