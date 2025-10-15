package bro.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class BroTest {
    @Test
    public void bro_constructor_initializesWithoutException() {
        assertDoesNotThrow(() -> new Bro());
    }
}
