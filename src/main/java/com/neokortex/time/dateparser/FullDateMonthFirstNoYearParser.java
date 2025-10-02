package com.neokortex.time.dateparser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.neokortex.time.Month;

/**
 * Represents a date parser that parses based on the format: "month_name day"
 *
 * <p>Notice that no year was specified</p>
 */
public class FullDateMonthFirstNoYearParser extends DateParser {
    private static final String FULL_DATE_MONTH_FIRST_REGEX =
            "(?i)"
                    + "\\b(jan(?:uary)?|feb(?:ruary)?|mar(?:ch)?|"
                    + "apr(?:il)?|may|jun(?:e)?|jul(?:y)?|"
                    + "aug(?:ust)?|sep(?:tember)?|oct(?:ober)?|"
                    + "nov(?:ember)?|dec(?:ember)?)\\s*"
                    + "(0?[1-9]|[12][0-9]|3[01])\\s*"
                    + "(?:st|nd|rd|th)?\\b";
    private static final Pattern pattern =
            Pattern.compile(FULL_DATE_MONTH_FIRST_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public String parse(String input, ArrayList<LocalDate> potentialDates) {
        LocalDate currentDate = LocalDate.now();
        Matcher dateMatcher = pattern.matcher(input);
        boolean error = false;

        StringBuffer strippedInput = new StringBuffer();

        while (dateMatcher.find()) {
            int day = dateMatcher.group(2) == null
                      ? 1
                      : Integer.parseInt(dateMatcher.group(2));
            int month = Month.fromAlias(dateMatcher.group(1))
                    .map(Month::getAsNumber)
                    .orElse(currentDate.getMonthValue());
            int year = currentDate.getYear();

            try {
                potentialDates.add(LocalDate.of(year, month, day));
            } catch (DateTimeException ignored) {
                error = true;
            }

            dateMatcher.appendReplacement(strippedInput, "");
        }

        dateMatcher.appendTail(strippedInput);

        return strippedInput.toString();
    }

}
