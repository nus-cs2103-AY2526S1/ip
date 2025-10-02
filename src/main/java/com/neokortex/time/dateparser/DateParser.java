package com.neokortex.time.dateparser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an abstract {@code DateParser} which facilitates extracting a {@link LocalDate} from a given
 * user input String.
 *
 * <p>
 * Provides a default {@link CompositeDateParser} with the specified order to run the input String through when
 * attempting to extract a {@link LocalDate} from it.
 * </p>
 *
 * Each child class must implement a {@link #parse(String, ArrayList)} method.
 *
 * @see CompositeDateParser
 */
public abstract class DateParser {

    /**
     * Represents the collection of {@link DateParser}s, all of which will be used to extract
     * a {@link List} of {@link LocalDate} based on a given input.
     *
     * <p><b>Note: The order of the {@link DateParser}s matter as the {@link DateParser}s will be chained
     * in order to extract the string.</b></p>
     *
     * @see DateParser
     */
    public static CompositeDateParser createDefaultParser() {
        return new CompositeDateParser(List.of(
                new FullDateParser(),
                new FullDateNoYearParser(),
                new FullDateMonthFirstParser(),
                new FullDateMonthFirstNoYearParser(),
                new SimpleDateParser(),
                new SimpleDateNoYearParser(),
                new SimpleDateMonthFirstParser(),
                new SimpleDateMonthFirstNoYearParser(),
                new DayOfWeekParser(),
                new PhraseParser()
        ));
    }

    /**
     * Extracts a {@link List} of potential {@link LocalDate}s to from the given input string. It returns
     * the same input String with the matched times removed to prevent repeat detections.
     *
     * @param input the input string to be parsed
     * @param potentialDates the list to add potential {@link LocalDate}s to as a result of extracting from
     *                       the input string
     * @return the input string but with the part of the String that matches the {@code DateParser}'s format
     *         extracted to prevent repeat detections.
     */
    public abstract String parse(String input, ArrayList<LocalDate> potentialDates);
}
