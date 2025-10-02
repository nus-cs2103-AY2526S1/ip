package com.neokortex.time.timeparser;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a time parser that parses time in the following formats:
 *
 * <ul>
 *     <li>hh:mm?:ss?am/pm</li>
 *     <li>hh.mm?.ss?am/pm</li>
 * </ul>
 *
 * <p>
 * Where 'hh' represents 2 digit hour, 'mm' represent 2 digit minutes, 'ss' represent 2 digit seconds. '?' indicates
 * that the item is optional.
 * </p>
 */
public class TwelveHourTimeParser extends TimeParser {
    private static final String TWELVE_HOUR_REGEX =
            "(?i)"
                    + "(0?[1-9]|1[0-2])"
                    + "(?:[.:]([0-5]\\d))?"
                    + "(?:[.:]([0-5]\\d))?"
                    + "\\s*([ap])\\.?m\\.?";

    private static final Pattern pattern = Pattern.compile(TWELVE_HOUR_REGEX, Pattern.CASE_INSENSITIVE);


    @Override
    public String parse(String input, ArrayList<LocalTime> potentialTimes) {
        Matcher matcher = pattern.matcher(input);
        boolean invalid = false;

        StringBuffer strippedInput = new StringBuffer();

        while (matcher.find()) {
            int hour = Integer.parseInt(matcher.group(1));
            int minute = matcher.group(2) == null ? 0 : Integer.parseInt(matcher.group(2));
            int second = matcher.group(3) == null ? 0 : Integer.parseInt(matcher.group(3));

            String meridiem = matcher.group(4);

            if (meridiem.matches("p\\.?m") && hour != 12) {
                hour += 12;
            } else if (meridiem.matches("a\\.?m") && hour == 12) {
                hour = 0;
            }

            try {
                potentialTimes.add(LocalTime.of(hour, minute, second));
                matcher.appendReplacement(strippedInput, "");
            } catch (DateTimeException e) {
                invalid = true;
            }
        }

        matcher.appendTail(strippedInput);

        return strippedInput.toString();
    }
}
