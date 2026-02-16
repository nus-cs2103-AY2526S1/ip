package alice;

import alice.exceptions.AliceException;
import alice.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void toFileFormat_returnsCorrectFormat() throws AliceException {
        Deadline d = new Deadline("return book", true, "2/12/2019 1800");
        assertEquals("D | X | return book | 2/12/2019 1800", d.toFileFormat());
    }

    @Test
    public void toString_returnsFormattedString() throws AliceException {
        Deadline d = new Deadline("return book", false, "2/12/2019 1800");
        String str = d.toString();
        assertTrue(str.contains("[D][ ] return book (by: Dec 2 2019"));
    }
}
