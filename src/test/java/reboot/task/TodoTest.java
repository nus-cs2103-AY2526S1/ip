package reboot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toString_iP_success() {
        Todo t = new Todo("iP", false);
        String expected = "[T][ ] iP";
        assertEquals(expected, t.toString());
    }

    @Test
    public void toFileString_iP_success() {
        Todo t = new Todo("iP", false);
        String expected = "T | 0 | iP";
        assertEquals(expected, t.toFileString());
    }
}
