package com.neokortex.time.dateparser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FullDateMonthFirstNoYearParserTest {
    private static final FullDateMonthFirstNoYearParser fdmfnyp = new FullDateMonthFirstNoYearParser();

    @Test
    public void testWithAffix() {
        String testString = """
                December 25th,
                September 3rd,
                January 15th,
                March 2nd,
                May 1st,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdmfnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithNoAffix() {
        String testString = """
                December 25,
                September 3,
                January 15,
                March 2,
                May 1,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdmfnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithAffixShortMonth() {
        String testString = """
                Dec 25th,
                Sep 3rd,
                Jan 15th,
                Mar 2nd,
                May 1st,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdmfnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithNoAffixShortMonth() {
        String testString = """
                Dec 25,
                Sep 3,
                Jan 15,
                Mar 2,
                May 1,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdmfnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }


    @Test
    public void testGarbledDates() {
        String testString = """
                December 25th 2025 with a bunch of spaces to go
                Something before the date shouldn't change anything September 3 2024,
                Before... January 15th 2023 and after...,
                1st M and April May 1st 2024 2025 2026 garbage data
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdmfnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testIncorrectDates() {
        String testString = """
                Cannot merge frontDecember 25th 2025
                September 3Cannot merge back
                January Nothing In Between 15th The Dates 2023,
                So Anywaythis March should 2 not be 2025 detected,
                1st May 2024, Wrong Format!
                """;

        ArrayList<LocalDate> result = new ArrayList<>();
        fdmfnyp.parse(testString, result);

        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(0, result.size());
    }
}
