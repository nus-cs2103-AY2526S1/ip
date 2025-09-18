package chatowo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toDataString_returnsCorrectFormat() {
        Deadline deadline = new Deadline("homework", "2024-01-01");
        assertEquals("D | 0 | homework | 2024-01-01", deadline.toDataString());
        deadline.setDone();
        assertEquals("D | 1 | homework | 2024-01-01", deadline.toDataString());
    }

    @Test
    public void toString_returnsCorrectFormat() {
        Deadline deadline = new Deadline("homework", "2024-01-01");
        assertEquals("[D][ ] homework\n(by: Jan 1 2024)", deadline.toString());
        deadline.setDone();
        assertEquals("[D][X] homework\n(by: Jan 1 2024)", deadline.toString());
    }
}
