//package com.elsria.time.dateparser;
//
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertInstanceOf;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class CompositeDateParserTest {
//    private List<DateParser> dateParsers = List.of(
//            new DayOfWeekParser(),
//            new SimpleDateParser(),
//            new FullDateParserTest(),
//            new FullDateMonthFirstParser(),
//            new PhraseParser()
//    );
//
//    @Test
//    public void testCompositeDateParser() {
//        CompositeDateParser cdp = new CompositeDateParser(dateParsers);
//        String testString = """
//                25-12-2025,
//                15-2-24,
//                September 5th 2025,
//                October 21st,
//                29 June 2023,
//                31st August
//                """;
//        List<LocalDate> result = cdp.processString(testString);
//        for (LocalDate localDate : result) {
//            System.out.println(localDate);
//        }
//        List<LocalDate> testDateSolutions = List.of(
//                LocalDate.of(2025, 12, 25),
//                LocalDate.of(2024, 2, 15),
//                LocalDate.of(2025, 9, 5),
//                LocalDate.of(2025, 10, 21),
//                LocalDate.of(2023, 6, 29),
//                LocalDate.of(2025, 8, 31)
//        );
//
//        assertInstanceOf(List.class, result, "proccessString returns a List");
//        assertEquals(testDateSolutions.size(), result.size());
//
//        for (int i = 0; i < result.size(); i++) {
//            assertEquals(testDateSolutions.get(i), result.get(i));
//        }
//    }
//
//    @Test
//    public void anotherDummyTest(){
//        assertEquals(4, 4);
//    }
//}
