package mochi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void overloadConstructorTest() {
        try {
            Deadline d = new Deadline("CS2100 Assignment", "2024-05-25", true);
            assertEquals("[D][x] CS2100 Assignment (by: 25/May/2024 00:00)", d.toString());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void saveStringTest() {
        try {
            Deadline d = new Deadline("CS2100 Assignment", "2024-05-25");
            assertEquals("D | 0 | CS2100 Assignment | 2024-05-25 0000", d.getSaveString());
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}
