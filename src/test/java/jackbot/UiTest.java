package jackbot;

import jackbot.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream oldOut;

    @BeforeEach
    void setUp() {
        oldOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(oldOut);
    }

    @Test
    void showWelcome_and_showError_areFramed() {
        Ui ui = new Ui();
        ui.showWelcome();
        ui.showError("oops");

        String s = out.toString();
        assertTrue(s.contains("Hello! I'm Jackbot"));
        assertTrue(s.contains("ERROR: oops"));
        assertTrue(s.contains("____________________________________________________________"));
    }

    @Test
    void showList_formatsIndicesAndTasks() {
        Ui ui = new Ui();
        ui.showList(List.of(new Todo("one"), new Todo("two")));

        String s = out.toString();
        assertTrue(s.contains("Your previous entries:"));
        assertTrue(s.contains("1. [T][ ] one"));
        assertTrue(s.contains("2. [T][ ] two"));
    }
}
