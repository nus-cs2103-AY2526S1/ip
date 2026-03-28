package dume.task;

import dume.util.DateTimeHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void toFileFormat_includes_by() {
        Deadline d = new Deadline("return book", "2/9/2003 1800");
        assertEquals("D | 0 | return book | 2/9/2003 1800", d.toFileFormat());
    }

    @Test
    void toString_prints_by() {
        Deadline d = new Deadline("return book", "2/9/2003 1800");
        String exp = "[D][ ] return book (by: " + DateTimeHelper.convert("2/9/2003 1800") + ")";
        assertEquals(exp, d.toString());
    }
}
