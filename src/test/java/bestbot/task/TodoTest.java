package bestbot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Todo task.
 */
public class TodoTest {

    @Test
    public void todoToString_changesWhenMarkedDone() {
        Todo t = new Todo("read book");
        String before = t.toString();
        // before should contain [ ] (not done)
        assertTrue(before.contains("[ ]"), "Expected not done before marking");

        t.markAsDone();
        String after = t.toString();
        // after should contain [X] to indicate done
        assertTrue(after.contains("[X]"), "Expected done mark after marking");
    }

}
