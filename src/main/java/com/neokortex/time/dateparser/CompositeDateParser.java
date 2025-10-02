package com.neokortex.time.dateparser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the collection of {@link DateParser}s, all of which will be used to extract
 * a {@link List} of {@link LocalDate} based on a given input.
 *
 * <p><b>Note: The order of the {@link DateParser}s matter as the {@link DateParser}s will be chained
 * in order to extract the string.</b></p>
 *
 * @see DateParser
 */
public class CompositeDateParser {
    private final List<DateParser> dateParsers;

    public CompositeDateParser(List<DateParser> dateParsers) {
        this.dateParsers = dateParsers;
    }

    /**
     * Processes a give input String by passing it through a chain of {@link DateParser}s. Each {@link DateParser}
     * adds to the current {@link List} of {@link LocalDate} which could possibly represent the user input, and
     * returns the same processString but with the parsed Time remove from it. This new String is then fed
     * into the next {@link DateParser}, and the process repeats until there are no {@link DateParser}s left.
     *
     * @param input the String to extract a {@link LocalTime} from
     * @return a {@link List} of potential {@link LocalTime}s that can be extracted from the input.
     */
    public List<LocalDate> processString(String input) {
        ArrayList<LocalDate> potentialDates = new ArrayList<>();
        String processed = input;
        for (DateParser dp : this.dateParsers) {
            processed = dp.parse(processed, potentialDates);
        }
        return potentialDates;
    }
}
