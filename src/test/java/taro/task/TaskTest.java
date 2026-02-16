package taro.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markAsDone_toString_reflectsChange() {
        Task t = new Task("read book", false);
        assertEquals("[ ] read book", t.toString());
        t.markAsDone();
        assertEquals("[X] read book", t.toString()); // markAsDone -> X
    }

    @Test
    public void toFile_formatsCorrectly_forDoneAndUndone() {
        Task t1 = new Task("return book", false);
        assertEquals("| 0 | return book", t1.toFile());

        Task t2 = new Task("return book", true);
        assertEquals("| 1 | return book", t2.toFile());
    }
}
