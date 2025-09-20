package lyra.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Test class for DateTimeUtil class.
 * Tests all public methods, date parsing, formatting, and edge cases.
 */
public class DateTimeUtilTest {
    
    @Test
    void testParseDate_ValidISOFormat() {
        String validDate = "2024-12-25";
        LocalDate expected = LocalDate.of(2024, 12, 25);
        LocalDate result = DateTimeUtil.parseDate(validDate);
        assertEquals(expected, result);
    }
    
    @Test
    void testParseDate_WithLeadingZeros() {
        String validDate = "2024-01-05";
        LocalDate expected = LocalDate.of(2024, 1, 5);
        LocalDate result = DateTimeUtil.parseDate(validDate);
        assertEquals(expected, result);
    }
    
    @Test
    void testParseDate_WithWhitespace() {
        String validDate = "  2024-12-25  ";
        LocalDate expected = LocalDate.of(2024, 12, 25);
        LocalDate result = DateTimeUtil.parseDate(validDate);
        assertEquals(expected, result);
    }
    
    @Test
    void testParseDate_LeapYear() {
        String validDate = "2024-02-29";
        LocalDate expected = LocalDate.of(2024, 2, 29);
        LocalDate result = DateTimeUtil.parseDate(validDate);
        assertEquals(expected, result);
    }
    
    @Test
    void testParseDate_FirstDayOfYear() {
        String validDate = "2025-01-01";
        LocalDate expected = LocalDate.of(2025, 1, 1);
        LocalDate result = DateTimeUtil.parseDate(validDate);
        assertEquals(expected, result);
    }
    
    @Test
    void testParseDate_LastDayOfYear() {
        String validDate = "2025-12-31";
        LocalDate expected = LocalDate.of(2025, 12, 31);
        LocalDate result = DateTimeUtil.parseDate(validDate);
        assertEquals(expected, result);
    }
    
    @Test
    void testParseDate_EmptyString() {
        String emptyDate = "";
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseDate(emptyDate);
        });
    }
    
    @Test
    void testParseDate_WhitespaceOnly() {
        String whitespaceDate = "   ";
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseDate(whitespaceDate);
        });
    }
    
    @Test
    void testParseDate_InvalidFormat() {
        String invalidDate = "25-12-2024";
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseDate(invalidDate);
        });
    }
    
    @Test
    void testParseDate_InvalidMonth() {
        String invalidDate = "2024-13-25";
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseDate(invalidDate);
        });
    }
    
    @Test
    void testParseDate_InvalidDay() {
        String invalidDate = "2024-12-32";
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseDate(invalidDate);
        });
    }
    
    @Test
    void testParseDate_NonLeapYearFebruary29() {
        String invalidDate = "2025-02-29";
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseDate(invalidDate);
        });
    }
    
    @Test
    void testParseDate_InvalidCharacters() {
        String invalidDate = "2024-12-2a";
        assertThrows(DateTimeParseException.class, () -> {
            DateTimeUtil.parseDate(invalidDate);
        });
    }
    
    @Test
    void testParseDate_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            DateTimeUtil.parseDate(null);
        });
    }
    
    @Test
    void testFormatForDisplay_ValidDate() {
        LocalDate date = LocalDate.of(2024, 12, 25);
        String expected = "Dec 25 2024";
        String result = DateTimeUtil.formatForDisplay(date);
        assertEquals(expected, result);
    }
    
    @Test
    void testFormatForDisplay_WithLeadingZeros() {
        LocalDate date = LocalDate.of(2024, 1, 5);
        String expected = "Jan 5 2024";
        String result = DateTimeUtil.formatForDisplay(date);
        assertEquals(expected, result);
    }
    
    @Test
    void testFormatForDisplay_LeapYear() {
        LocalDate date = LocalDate.of(2024, 2, 29);
        String expected = "Feb 29 2024";
        String result = DateTimeUtil.formatForDisplay(date);
        assertEquals(expected, result);
    }
    
    @Test
    void testFormatForDisplay_FirstDayOfYear() {
        LocalDate date = LocalDate.of(2025, 1, 1);
        String expected = "Jan 1 2025";
        String result = DateTimeUtil.formatForDisplay(date);
        assertEquals(expected, result);
    }
    
    @Test
    void testFormatForDisplay_LastDayOfYear() {
        LocalDate date = LocalDate.of(2025, 12, 31);
        String expected = "Dec 31 2025";
        String result = DateTimeUtil.formatForDisplay(date);
        assertEquals(expected, result);
    }
    
    @Test
    void testFormatForDisplay_DifferentMonths() {
        // Test all months to ensure proper abbreviation
        LocalDate janDate = LocalDate.of(2025, 1, 15);
        LocalDate febDate = LocalDate.of(2025, 2, 15);
        LocalDate marDate = LocalDate.of(2025, 3, 15);
        LocalDate aprDate = LocalDate.of(2025, 4, 15);
        LocalDate mayDate = LocalDate.of(2025, 5, 15);
        LocalDate junDate = LocalDate.of(2025, 6, 15);
        LocalDate julDate = LocalDate.of(2025, 7, 15);
        LocalDate augDate = LocalDate.of(2025, 8, 15);
        LocalDate sepDate = LocalDate.of(2025, 9, 15);
        LocalDate octDate = LocalDate.of(2025, 10, 15);
        LocalDate novDate = LocalDate.of(2025, 11, 15);
        LocalDate decDate = LocalDate.of(2025, 12, 15);
        
        assertEquals("Jan 15 2025", DateTimeUtil.formatForDisplay(janDate));
        assertEquals("Feb 15 2025", DateTimeUtil.formatForDisplay(febDate));
        assertEquals("Mar 15 2025", DateTimeUtil.formatForDisplay(marDate));
        assertEquals("Apr 15 2025", DateTimeUtil.formatForDisplay(aprDate));
        assertEquals("May 15 2025", DateTimeUtil.formatForDisplay(mayDate));
        assertEquals("Jun 15 2025", DateTimeUtil.formatForDisplay(junDate));
        assertEquals("Jul 15 2025", DateTimeUtil.formatForDisplay(julDate));
        assertEquals("Aug 15 2025", DateTimeUtil.formatForDisplay(augDate));
        assertEquals("Sep 15 2025", DateTimeUtil.formatForDisplay(sepDate));
        assertEquals("Oct 15 2025", DateTimeUtil.formatForDisplay(octDate));
        assertEquals("Nov 15 2025", DateTimeUtil.formatForDisplay(novDate));
        assertEquals("Dec 15 2025", DateTimeUtil.formatForDisplay(decDate));
    }
    
    @Test
    void testFormatForDisplay_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            DateTimeUtil.formatForDisplay(null);
        });
    }
    
    @Test
    void testConstants() {
        // Test that constants are accessible and have expected values
        assertNotNull(DateTimeUtil.STORAGE_DATE);
        assertNotNull(DateTimeUtil.DISPLAY_DATE);
        
        // Test that STORAGE_DATE follows ISO format
        LocalDate testDate = LocalDate.of(2024, 12, 25);
        String storageFormat = testDate.format(DateTimeUtil.STORAGE_DATE);
        assertEquals("2024-12-25", storageFormat);
        
        // Test that DISPLAY_DATE follows the expected pattern
        String displayFormat = testDate.format(DateTimeUtil.DISPLAY_DATE);
        assertEquals("Dec 25 2024", displayFormat);
    }
    
    @Test
    void testRoundTripParsingAndFormatting() {
        // Test that parsing and formatting work together correctly
        String originalDate = "2024-12-25";
        LocalDate parsedDate = DateTimeUtil.parseDate(originalDate);
        String formattedDate = DateTimeUtil.formatForDisplay(parsedDate);
        
        // The formatted date should match the expected display format
        assertEquals("Dec 25 2024", formattedDate);
        
        // We can parse the formatted date back to ISO format
        String reParsed = parsedDate.format(DateTimeUtil.STORAGE_DATE);
        assertEquals(originalDate, reParsed);
    }
}
