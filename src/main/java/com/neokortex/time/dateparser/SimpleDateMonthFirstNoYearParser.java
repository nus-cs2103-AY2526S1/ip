package com.neokortex.time.dateparser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a date parser that parses dates in the format: mm/dd
 * <p>
 * 'mm' represent numerical month, 'dd' representing day in the month
 * </p>
 */
public class SimpleDateMonthFirstNoYearParser extends DateParser {
    private static final String SIMPLE_DATE_REGEX =
            "(?i)"
                    + "\\b(0?[1-9]|1[0-2])"
                    + "\\s*[/\\-]\\s*"
                    + "(0?[1-9]|[12][0-9]|3[01])\\b";

    private static final Pattern pattern =
            Pattern.compile(SIMPLE_DATE_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public String parse(String input, ArrayList<LocalDate> potentialDates) {
        LocalDate currentDate = LocalDate.now();
        Matcher dateMatcher = pattern.matcher(input);
        boolean error = false;

        StringBuffer strippedInput = new StringBuffer();

        while (dateMatcher.find()) {
            int day = Integer.parseInt(dateMatcher.group(2));
            int month = Integer.parseInt(dateMatcher.group(1));
            int year = currentDate.getYear();

            year = (year < 100) ? 2000 + year : year;

            try {
                potentialDates.add(LocalDate.of(year, month, day));
                dateMatcher.appendReplacement(strippedInput, "");
            } catch (DateTimeException ignored) {
                error = true;
            }

        }

        dateMatcher.appendTail(strippedInput);

        return strippedInput.toString();
    }
}
