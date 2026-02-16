import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guibot.exception.WrongDateTimeFormatException;
import guibot.task.Deadline;

public class DeadlineTest {
    @Test
    public void testStorageStringConversion() {
        Deadline d;
        try {
            d = Deadline.of(new String[]{"hello", "2025-09-01 2359"});
            assertEquals("d//false//hello/2025-09-01 2359", d.toStorageString());

            d.mark();
            assertEquals("d//true//hello/2025-09-01 2359", d.toStorageString());
        } catch (WrongDateTimeFormatException e) {
            assert(false);
        }
    }

    @Test
    public void testStringConversion() {
        Deadline d;
        try {
            d = Deadline.of(new String[]{"hello", "2025-09-01 2359"});
            assertEquals("[D][ ] hello (by: Sep 1 2025 11.59pm)", d.toString());

            d.mark();
            assertEquals("[D][X] hello (by: Sep 1 2025 11.59pm)", d.toString());
        } catch (WrongDateTimeFormatException e) {
            assert(false);
        }
    }
}
