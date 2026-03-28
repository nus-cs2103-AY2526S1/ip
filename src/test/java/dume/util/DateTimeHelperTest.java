package dume.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeHelperTest {
    @Test
    void convert_valid_dMY_HHmm() {
        // d/M/yyyy HHmm -> MMM dd yyyy HH:mm
        assertEquals("Aug 09 2022 18:00", DateTimeHelper.convert("9/8/2022 1800"));
    }

    @Test
    void convert_invalid_returns_raw() {
        assertEquals("abc", DateTimeHelper.convert("abc"));
    }

    @Test
    void convert_blank_returns_blank() {
        assertEquals("", DateTimeHelper.convert(""));
    }
}
