package snowy.task;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void markCheck() {
        Deadline deadline = new Deadline("finish assignment", "2025-08-29");
        deadline.mark();
        assertEquals(deadline.checkStatus(), true);
    }
}
