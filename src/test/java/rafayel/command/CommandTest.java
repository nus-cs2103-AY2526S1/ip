package rafayel.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import rafayel.RafayelException;

public class CommandTest {
    @Test
    public void testHandleReadDate_validFormats_success() throws RafayelException {
        // Valid ones

        // Format 1: "MMM d yyyy HH:mm"
        assertEquals(LocalDateTime.of(2023, 1, 1, 14, 30), Command.handleReadDate("Jan 1 2023 14:30"));

        // Format 2: "yyyy/MM/dd HH:mm"
        assertEquals(LocalDateTime.of(2023, 1, 1, 14, 30), Command.handleReadDate("2023/01/01 14:30"));

        // Format 3: "dd-MM-yyyy HH:mm"
        assertEquals(LocalDateTime.of(2023, 1, 1, 14, 30), Command.handleReadDate("01-01-2023 14:30"));
    }

    @Test
    public void testHandleReadDate_edgeCases_success() throws RafayelException {
        // Edge cases ones

        // Leap year
        LocalDateTime result1 = Command.handleReadDate("Feb 29 2024 12:00");
        assertEquals(LocalDateTime.of(2024, 2, 29, 12, 0), result1);

        // Test midnight
        LocalDateTime result2 = Command.handleReadDate("2023/12/31 00:00");
        assertEquals(LocalDateTime.of(2023, 12, 31, 0, 0), result2);

        // Test end of day
        LocalDateTime result3 = Command.handleReadDate("31-12-2023 23:59");
        assertEquals(LocalDateTime.of(2023, 12, 31, 23, 59), result3);

    }

    @Test
    public void testHandleReadDate_invalidFormats_throwsException() {
        // Invalid inputs
        String[] invalidInputs = {
            "Invalid date format",
            "2023-01-01 14:30", // Wrong separator for format 2
            "01/01/2023 16:30", // Wrong separator for format 3
            "Jan 32 2023 14:30", // Invalid day
            "2023/13/01 14:30", // Invalid month
            "2023/01/01 25:00", // Invalid hour
            "2023/01/01 14:60", // Invalid minute
            "Jan 1 2023", // Missing time
            "14:30", // Missing date
        };

        for (String input : invalidInputs) {
            assertThrows(RafayelException.class, () -> Command.handleReadDate(input),
                    "Should throw RafayelException for invalid input: " + input);
        }
    }

}
