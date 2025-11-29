package kleebot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    void testGetTypeAndBy() {
        Deadline deadline = new Deadline("Submit assignment", "Monday");
        assertEquals("D", deadline.getType());
        assertEquals("Monday", deadline.getBy());
    }

    @Test
    void testToString() {
        Deadline deadline = new Deadline("Submit assignment", "Monday");
        assertEquals("[D][ ]Submit assignment (by: Monday)", deadline.toString());
    }
}