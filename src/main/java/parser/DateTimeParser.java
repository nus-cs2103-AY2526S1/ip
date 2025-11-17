package parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Class that handles parsing of dates and times.
 * Stores information on the time format.
 */
public class DateTimeParser {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    private static final int EXPECTED_SEGMENTS = 2;


    private static final Parser parser = new Parser();

    //ParseDate and parseTime methods were generated with AI and modified to make more sense.
    public LocalDate parseDate(String input) {
        List<String> dateAndTime = parser.stringSplitter(input, " ");
        LocalDate returnDate = null;
        try {
            returnDate = LocalDate.parse(dateAndTime.get(0));
        } catch (DateTimeParseException e) {
            return returnDate;
        }
        return returnDate;
    }

    public LocalTime parseTime(String input) {
        List<String> dateAndTime = parser.stringSplitter(input, " ");
        LocalTime returnTime = null;

        if (dateAndTime.size() == EXPECTED_SEGMENTS) {
            try {
                returnTime = LocalTime.parse(dateAndTime.get(1), TIME_FORMAT);
            } catch (DateTimeParseException e) {
                return returnTime;
            }
        }
        return returnTime;
    }
}
