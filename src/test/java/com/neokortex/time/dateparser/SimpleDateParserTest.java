package com.neokortex.time.dateparser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleDateParserTest {
    private static final SimpleDateParser sdp = new SimpleDateParser();

    @Test
    public void testDoubleDigits() {
        String testString = """
                25-12-2025,
                03/09/2025,
                15-01-1979,
                02-03-2013,
                01/05/2024,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(1979, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        sdp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testSingleDigit() {
        String testString = """
                25-12-2025,
                3/9/2025,
                15-1-1979,
                2-3-2013,
                1/5/2024,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(1979, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        sdp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testDoubleDigitHalfYear() {
        String testString = """
                25-12-25,
                03/09/25,
                15-01-79,
                02-03-13,
                01/05/24,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(2079, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        sdp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }

    @Test
    public void testSingleDigitHalfYear() {
        String testString = """
                25-12-25,
                3/9/25,
                15-1-79,
                2-3-13,
                1/5/24,
                """;

        List<LocalDate> testDateSolutions = List.of(
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2025, 9, 3),
                LocalDate.of(2079, 1, 15),
                LocalDate.of(2013, 3, 2),
                LocalDate.of(2024, 5, 1)
        );

        ArrayList<LocalDate> result = new ArrayList<>();
        sdp.parse(testString, result);

        assertEquals(testDateSolutions.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(testDateSolutions.get(i), result.get(i));
        }
    }


}
