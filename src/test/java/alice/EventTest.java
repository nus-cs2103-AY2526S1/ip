package alice;

import alice.exceptions.AliceException;
import alice.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void toFileFormat_returnsCorrectFormat() throws AliceException {
        Event e = new Event("meeting", true, "2/12/2019 1800", "2/12/2019 2000");
        assertEquals("E | X | meeting | 2/12/2019 1800 | 2/12/2019 2000", e.toFileFormat());
    }
}
