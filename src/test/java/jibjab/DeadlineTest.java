package jibjab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testTaskComplete() {
        Deadline deadline = new Deadline("test", "01/01/2077 18:00");
        deadline.setDone();
        assertEquals("[D][X] test (by: Jan 01 2077 18:00)", deadline.toString());
    }
}
