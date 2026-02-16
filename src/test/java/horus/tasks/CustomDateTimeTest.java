package horus.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeTest {

    @Test
    public void testDateOnlyInput() {
        CustomDateTime testObj = new CustomDateTime("19/12/2023");
        assertEquals("Dec 19 2023 11:59pm ", testObj.toString() );
        testGetData(testObj);
    }

    @Test
    public void testTimeOnlyInput() {
        CustomDateTime testObj = new CustomDateTime("1600");
        assertEquals( LocalDate.now().format( DateTimeFormatter.ofPattern("MMM d yyyy")) + " 4:00pm ", testObj.toString() );
        testGetData(testObj);
    }

    @Test
    public void testDateTimeInput() {
        CustomDateTime testObj = new CustomDateTime("30/08/2025 1300");
        assertEquals("Aug 30 2025 1:00pm ", testObj.toString() );
        testGetData(testObj);
    }

    @Test
    public void testDateTimeWithExtraStringInput() {
        CustomDateTime testObj = new CustomDateTime("30/08/2025 1300 after lunch");
        assertEquals("Aug 30 2025 1:00pm after lunch", testObj.toString() );
        testGetData(testObj);
    }

    @Test
    public void testEquals() {
        CustomDateTime testObj = new CustomDateTime("30/08/2025 1300 after lunch");
        assertEquals(testObj.equals(null), false);
    }

    public void testGetData(CustomDateTime testObj)  {
        assertEquals(testObj.toString(), new CustomDateTime(testObj.getData()).toString() );
    }
}
