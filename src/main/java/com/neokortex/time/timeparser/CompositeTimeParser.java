package com.neokortex.time.timeparser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the collection of {@link TimeParser}s, all of which will be used to extract
 * a {@link List} of {@link LocalTime} based on a given input.
 *
 * <p><b>Note: The order of the {@link TimeParser}s matter as the {@link TimeParser}s will be chained
 * in order to extract the string.</b></p>
 *
 * @see TimeParser
 */
public class CompositeTimeParser {
    private final List<TimeParser> timeParsers;

    public CompositeTimeParser(List<TimeParser> timeParsers) {
        this.timeParsers = timeParsers;
    }

    /**
     * Processes a give input String by passing it through a chain of {@link TimeParser}s. Each {@link TimeParser}
     * adds to the current {@link List} of {@link LocalTime} which could possibly represent the user input, and
     * returns the same processString but with the parsed Time remove from it. This new String is then fed
     * into the next {@link TimeParser}, and the process repeats until there are no {@link TimeParser}s left.
     *
     * @param input the String to extract a {@link LocalTime} from
     * @return a {@link List} of potential {@link LocalTime}s that can be extracted from the input.
     */
    public List<LocalTime> processString(String input) {
        ArrayList<LocalTime> potentialTimes = new ArrayList<>();
        String processed = input;
        for (TimeParser tp : this.timeParsers) {
            processed = tp.parse(processed, potentialTimes);
        }
        return potentialTimes;
    }
}
