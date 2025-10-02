package com.neokortex.time.dateparser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FullDateParserTest {
    private static final FullDateParser fdp = new FullDateParser();

    @Test
    public void testWithAffix() {
        String testString = """
                25th December 2025,
                3rd September 2025,
                15th January 1979,
                2nd March 2013,
                1st May 2024,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(1979, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithNoAffix() {
        String testString = """
                25 December 2025,
                3 September 2025,
                15 January 1979,
                2 March 2013,
                1 May 2024,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(1979, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithAffixShortMonth() {
        String testString = """
                25th Dec 2025,
                3rd Sep 2025,
                15th Jan 1979,
                2nd Mar 2013,
                1st May 2024,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(1979, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testWithNoAffixShortMonth() {
        String testString = """
                25 Dec 2025,
                3 Sep 2025,
                15 Jan 1979,
                2 Mar 2013,
                1 May 2024,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(1979, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testGarbledDates() {
        String testString = """
                25th December 2025 with a bunch of spaces to go
                Something before the date shouldn't change anything 3 September 2024,
                Before... 15th January 2023 and after...,
                1st M and 2nd 1st May 2024 2025 2026 garbage data
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2024, 9, 3),
                LocalDate.of(2023, 1, 15),
                LocalDate.of(2024, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        fdp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testIncorrectDates() {
        String testString = """
                Cannot merge front25th December 2025
                3 SeptemberCannot merge back
                15th Nothing In Between January The Dates 2023,
                So Anyway 2 this should March not be 2025 detected,
                May 1st 2024, Wrong Format!
                """;

        ArrayList<LocalDate> result = new ArrayList<>();
        fdp.parse(testString, result);

        assertInstanceOf(List.class, result, "parse returns a List");
        assertEquals(0, result.size());
    }
}
