package mambo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void respondTest() {
        assertEquals(System.lineSeparator()
                + "───────────────────────────────────────────────────────────────"
                + "───────────────────────────────────────────────────────────────────────"
                + System.lineSeparator()
                + "hi"
                + System.lineSeparator()
                + "───────────────────────────────────────────────────────────────"
                + "───────────────────────────────────────────────────────────────────────"
                + System.lineSeparator(), new Ui().respond("hi"));

    }
}
