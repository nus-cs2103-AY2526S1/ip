package Dan.Parser;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void Test1() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = "28/08/2025";

        LocalDate actual = DateParser.parseDate(dateString);
        LocalDate temp = LocalDate.parse(dateString, inputFormat);
        String expectedString = temp.format(outputFormat);
        LocalDate expected = LocalDate.parse(expectedString, outputFormat);
        assertEquals(expected, actual);
    }

    @Test
    public void Test2() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = "2025-01-01";

        LocalDate actual = DateParser.parseDate(dateString);
        LocalDate temp = LocalDate.parse(dateString, inputFormat);
        String expectedString = temp.format(outputFormat);
        LocalDate expected = LocalDate.parse(expectedString, outputFormat);
        assertEquals(expected, actual);
    }

    @Test
    public void Test3() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy");

        String dateString = "2/2/2025";

        LocalDate actual = DateParser.parseDate(dateString);
        LocalDate temp = LocalDate.parse(dateString, inputFormat);
        String expectedString = temp.format(outputFormat);
        LocalDate expected = LocalDate.parse(expectedString, outputFormat);
        assertEquals(expected, actual);
    }

    @Test
    public void Test4() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

        String dateString = "Mar 03 2023";

        LocalDate actual = DateParser.parseDate(dateString);
        LocalDate temp = LocalDate.parse(dateString, inputFormat);
        String expectedString = temp.format(outputFormat);
        LocalDate expected = LocalDate.parse(expectedString, outputFormat);
        assertEquals(expected, actual);
    }

    @Test
    public void Test5() {
        LocalDate actual = DateParser.parseDate("invalid input");
        assertEquals(null, actual);
    }
}
