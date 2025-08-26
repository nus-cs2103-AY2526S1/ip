package rafayel.ui;

import org.junit.jupiter.api.Test;
import rafayel.RafayelException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RafayelTest {
    @Test
    public void testHandleReadDate_validFormats_success() {
        // Valid ones

        // Format 1: "MMM d yyyy HH:mm"
        assertEquals(
                LocalDateTime.of(2023, 1, 1, 14, 30),
                Rafayel.handleReadDate("Jan 1 2023 14:30")
        );

        // Format 2: "yyyy/MM/dd HH:mm"
        assertEquals(
                LocalDateTime.of(2023, 1, 1, 14, 30),
                Rafayel.handleReadDate("2023/01/01 14:30")
        );

        // Format 3: "dd-MM-yyyy HH:mm"
        assertEquals(
                LocalDateTime.of(2023, 1, 1, 14, 30),
                Rafayel.handleReadDate("01-01-2023 14:30")
        );
    }

    @Test
    public void testHandleReadDate_edgeCases_success() {
        // Edge cases ones

        // Leap year
        LocalDateTime result1 = Rafayel.handleReadDate("Feb 29 2024 12:00");
        assertEquals(LocalDateTime.of(2024, 2, 29, 12, 0), result1);

        // Test midnight
        LocalDateTime result2 = Rafayel.handleReadDate("2023/12/31 00:00");
        assertEquals(LocalDateTime.of(2023, 12, 31, 0, 0), result2);

        // Test end of day
        LocalDateTime result3 = Rafayel.handleReadDate("31-12-2023 23:59");
        assertEquals(LocalDateTime.of(2023, 12, 31, 23, 59), result3);

    }

    @Test
    public void testHandleReadDate_invalidFormats_returnsNull() {
        // Invalid inputs
        String[] invalidInputs = {"Invalid date format",
                "2023-01-01 14:30", // Wrong separator for format 2
                "01/01/2023 16:30", // Wrong separator for format 3
                "Jan 32 2023 14:30", // Invalid day
                "2023/13/01 14:30", // Invalid month
                "2023/01/01 25:00", // Invalid hour
                "2023/01/01 14:60", // Invalid minute
                "Jan 1 2023", // Missing time
                "14:30", // Missing date
//                "2023/02/29 18:30" // Invalid date (non-leap year)
                };

        for (String input : invalidInputs) {
            LocalDateTime result = Rafayel.handleReadDate(input);
            assertNull(result, "Should return null or empty input");
        }
    }

}
