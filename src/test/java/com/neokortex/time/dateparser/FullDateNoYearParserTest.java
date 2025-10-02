package com.neokortex.time.dateparser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FullDateNoYearParserTest {
    private static final FullDateNoYearParser fdnyp = new FullDateNoYearParser();

    @Test
    public void testWithAffix() {
        String testString = """
                25th December,
                3rd September,
                15th January,
                2nd March,
                1st May,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithNoAffix() {
        String testString = """
                25 December,
                3 September,
                15 January,
                2 March,
                1 May,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithAffixShortMonth() {
        String testString = """
                25th Dec,
                3rd Sep,
                15th Jan,
                2nd Mar,
                1st May,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithNoAffixShortMonth() {
        String testString = """
                25 Dec,
                3 Sep,
                15 Jan,
                2 Mar,
                1 May,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 3, 2),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }


    @Test
    public void testGarbledDates() {
        String testString = """
                25th December with a bunch of spaces to go
                Something before the date shouldn't change anything 3 September,
                Before... 15th January and after...,
                1st M and 2nd 1st May June July garbage data
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(Constants.DEFAULT_YEAR, 12, 25),
                LocalDate.of(Constants.DEFAULT_YEAR, 9, 3),
                LocalDate.of(Constants.DEFAULT_YEAR, 1, 15),
                LocalDate.of(Constants.DEFAULT_YEAR, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdnyp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testIncorrectDates() {
        String testString = """
                Cannot merge front25th December
                3 SeptemberCannot merge back
                15th Nothing In Between January The Dates 2023,
                So Anyway 2 this should March not be 2025 detected,
                May 1st, Wrong Format!
                """;

        ArrayList<LocalDate> result = new ArrayList<>();
        fdnyp.parse(testString, result);

        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(0, result.size());
    }
}
