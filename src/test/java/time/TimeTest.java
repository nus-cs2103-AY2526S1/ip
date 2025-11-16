package time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exception.TimeFormatException;

public class TimeTest {
    @Test
    public void time_correctFormat_success() throws TimeFormatException {
        // Implement test for correct format
        String input = "25/12/2023 18:30";
        Time time = Time.parseDateTime(input);
        assertEquals("25 Dec 2023 18:30", time.getDateTime());
    }

    @Test
    public void time_incorrectFormat_exceptionThrown() {
        // Implement test for incorrect format
        String input = "2023-12-25 18:30";
        try {
            Time.parseDateTime(input);
        } catch (TimeFormatException e) {
            assertEquals("Invalid date/time format. Please use 'dd/MM/yyyy HH:mm' format.", e.getMessage());
        }
    }
}
