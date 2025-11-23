package stewie.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Ui}.
 */
class UiTest {

    @Test
    void showBye_always_returnsExitMessage() {
        assertEquals("Finally, you're leaving. Do try not to get lost on the way out.",
                Ui.showBye());
    }

    @Test
    void formatPrintable_multiLineString_eachLinePrefixed() {
        String input = "Line1\nLine2\nLine3";
        String expected = "\t Line1\n\t Line2\n\t Line3";
        assertEquals(expected, Ui.formatPrintable(input));
    }
}
