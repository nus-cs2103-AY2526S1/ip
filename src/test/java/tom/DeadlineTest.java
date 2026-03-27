package tom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2025-10-19 1300", inputFormatter);
        Deadline dl = new Deadline("do something", by);
        assertEquals("D | 0 | do something | Oct 19 2025, 01:00 PM", dl.saveDesc());
    }
}
