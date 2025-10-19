package reboot.task;

import java.time.LocalDateTime;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_withDateOnly_success() {
        Deadline d = new Deadline("return book", false,
                LocalDate.of(2025, 12, 2));
        String expected = "[D][ ] return book (by: 02 12 2025)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void toString_withDateTime_success() {
        Deadline d = new Deadline("return book", false,
                LocalDateTime.of(2025, 12, 2, 21, 0));
        String expected = "[D][ ] return book (by: 02 12 2025 2100)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void toFileString_withDateOnly_success() {
        Deadline d = new Deadline("return book", false,
                LocalDate.of(2025, 12, 2));
        String expected = "D | 0 | return book | 02 12 2025";
        assertEquals(expected, d.toFileString());
    }

    @Test
    public void toFileString_withDateTime_success() {
        Deadline d = new Deadline("return book", false,
                LocalDateTime.of(2025, 12, 2, 21, 0));
        String expected = "D | 0 | return book | 02 12 2025 2100";
        assertEquals(expected, d.toFileString());
    }
}
