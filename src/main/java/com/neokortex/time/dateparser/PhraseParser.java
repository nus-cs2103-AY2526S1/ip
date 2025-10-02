package com.neokortex.time.dateparser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a date parser that parses common phrases like the following.
 *
 * <p><b>Examples:</b></p>
 * <ul>
 *     <li>Today</li>
 *     <li>Tomorrow</li>
 *     <li>next week</li>
 *     <li>following month</li>
 * </ul>
 */
public class PhraseParser extends DateParser {
    private static final String PHRASE_REGEX =
            "(?i)"
                    + "\\b(today|tomorrow|"
                    + "day\\s+after|following\\s+day|"
                    + "next\\s+(?:week|month|year))\\b";

    private static final Pattern pattern =
            Pattern.compile(PHRASE_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public String parse(String input, ArrayList<LocalDate> potentialDates) {
        LocalDate currentDate = LocalDate.now();
        Matcher matcher = pattern.matcher(input);

        StringBuffer strippedInput = new StringBuffer();

        while (matcher.find()) {
            String keyword = matcher.group(1)
                    .replaceAll("\\s+", " ")
                    .trim()
                    .toLowerCase();

            switch (keyword) {
            case "today":
                potentialDates.add(currentDate);
                matcher.appendReplacement(strippedInput, "");
                break;
            case "tomorrow":
                potentialDates.add(currentDate.plusDays(1));
                matcher.appendReplacement(strippedInput, "");
                break;
            case "day after":
            case "following day":
                potentialDates.add(currentDate.plusDays(2));
                matcher.appendReplacement(strippedInput, "");
                break;
            case "next week":
                potentialDates.add(currentDate.plusWeeks(1));
                matcher.appendReplacement(strippedInput, "");
                break;
            case "next month":
                potentialDates.add(currentDate.plusMonths(1));
                matcher.appendReplacement(strippedInput, "");
                break;
            case "next year":
                potentialDates.add(currentDate.plusYears(1));
                matcher.appendReplacement(strippedInput, "");
                break;
            default:
                break;
            }
        }

        matcher.appendTail(strippedInput);

        return strippedInput.toString();

    }
}
