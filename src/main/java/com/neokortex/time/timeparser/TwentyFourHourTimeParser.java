package com.neokortex.time.timeparser;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a time parser that parses time in the standard 24-hour time format: hhmm
 *
 * <p>It is assumed that seconds will not be provided</p>
 */
public class TwentyFourHourTimeParser extends TimeParser {
    private static final String TWENTY_FOUR_HOUR_REGEX =
            "(?i)"
                    + "([01]\\d|2[0-3])"
                    + "[.:]?([0-5]\\d)";

    private static final Pattern pattern =
            Pattern.compile(TWENTY_FOUR_HOUR_REGEX,
                    Pattern.CASE_INSENSITIVE);

    @Override
    public String parse(String input, ArrayList<LocalTime> potentialTimes) {
        Matcher matcher = pattern.matcher(input);
        boolean error = false;

        StringBuffer strippedInput = new StringBuffer();

        while (matcher.find()) {
            int hour = Integer.parseInt(matcher.group(1));
            int minute = matcher.group(2) == null
                    ? 0
                    : Integer.parseInt(matcher.group(2));

            try {
                potentialTimes.add(LocalTime.of(hour, minute));
                matcher.appendReplacement(strippedInput, "");
            } catch (DateTimeException ignore) {
                error = true;
            }
        }

        matcher.appendTail(strippedInput);

        return strippedInput.toString();
    }
}
