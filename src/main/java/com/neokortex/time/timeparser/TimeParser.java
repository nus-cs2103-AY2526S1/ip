package com.neokortex.time.timeparser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an abstract {@code TimeParser} which facilitates extracting a {@link LocalTime} from a given
 * user input String.
 *
 * <p>
 * Provides a default {@link CompositeTimeParser} with the specified order to run the input String through when
 * attempting to extract a {@link LocalTime} from it.
 * </p>
 *
 * Each child class must implement a {@link #parse(String, ArrayList)} method.
 *
 * @see CompositeTimeParser
 */
public abstract class TimeParser {

    /**
     * Constructs a default {@link CompositeTimeParser}, where each {@link TimeParser} will be used
     * in sequence to extract the necessary potential {@link LocalTime}s from the input String
     *
     * @return the default {@link CompositeTimeParser}
     */
    public static CompositeTimeParser createDefaultParser() {
        return new CompositeTimeParser(List.of(
                new TwelveHourTimeParser(),
                new TwentyFourHourTimeParser()
        ));
    }

    /**
     * Extracts a {@link List} of potential {@link LocalTime}s to from the given input string. It returns
     * the same input String with the matched times removed to prevent repeat detections.
     *
     * @param input the input string to be parsed
     * @param potentialTimes the list to add potential {@link LocalTime}s to as a result of extracting from
     *                       the input string
     * @return the input string but with the part of the String that matches the {@code TimeParser}'s format
     *         extracted to prevent repeat detections.
     */
    public abstract String parse(String input, ArrayList<LocalTime> potentialTimes);
}
