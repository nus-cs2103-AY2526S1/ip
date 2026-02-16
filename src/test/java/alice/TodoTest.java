package alice;

import alice.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void toFileFormat_returnsCorrectFormat() {
        Todo t = new Todo("read book", true);
        assertEquals("T | X | read book", t.toFileFormat());
    }
}
