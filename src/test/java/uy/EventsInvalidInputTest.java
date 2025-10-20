package uy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import uy.Events;

public class EventsInvalidInputTest {
    @Test
    public void testInvalidEventThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Events("badly formatted event input");
        });
    }
}
