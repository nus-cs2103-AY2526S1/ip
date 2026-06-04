package sora.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    public void toString_notDone_returnsCorrectFormat() {
        LocalDateTime by = LocalDateTime.of(2006, 2, 8, 23, 59);
        Deadline deadline = new Deadline("Do something", by);
        assertEquals("[D][ ] Do something (by: Feb 08 2006 2359)", deadline.toString());
    }

    @Test
    public void toString_markedDone_returnsCorrectFormat() {
        LocalDateTime by = LocalDateTime.of(2006, 2, 8, 23, 59);
        Deadline deadline = new Deadline("Do something", by);
        deadline.markAsDone();
        assertEquals("[D][X] Do something (by: Feb 08 2006 2359)", deadline.toString());
    }

    @Test
    public void toFormat_notDone_returnsCorrectFormat() {
        LocalDateTime by = LocalDateTime.of(2006, 2, 8, 23, 59);
        Deadline deadline = new Deadline("Do something", by);
        assertEquals("D | 0 | Do something | Feb 08 2006 2359", deadline.toFormat());
    }

    @Test
    public void toFormat_done_returnsCorrectFormat() {
        LocalDateTime by = LocalDateTime.of(2006, 2, 8, 23, 59);
        Deadline deadline = new Deadline("Do something", by);
        deadline.markAsDone();
        assertEquals("D | 1 | Do something | Feb 08 2006 2359", deadline.toFormat());
    }
}
