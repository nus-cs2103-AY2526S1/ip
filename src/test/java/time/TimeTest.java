package time;

import org.junit.jupiter.api.Test;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {
    private TaskList list = new TaskList("./data/deadlineTestStorage.txt");
    @Test
    public void timeStringTest() {
        Time time = new Time("10-12-2022 1800");
        String expected = "10-12-2022 1800";
        String actual = time.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void timePrintTest() {
        Time time = new Time("10-12-2022 1800");
        String expected = "10 Dec 2022 18:00";
        String actual = time.toPrint();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidTimeTaskTest() {
        String actual = list.addTask("deadline get book /by 18 05 2025 1800", "deadline");
        String expected = "Candy needs the recipe right!\n"
                + "Please input in the format: 'dd-mm-yyyy HHmm' \n"
                + "If date/month < 10, remember to add '0' at the front!";
        assertEquals(expected, actual);
    }

    @Test
    public void invalidTimeTaskTestTwo() {
        String actual = list.addTask("deadline test book /by 8-02-2025 1800", "deadline");
        String expected = "Candy needs the recipe right!\n"
                + "Please input in the format: 'dd-mm-yyyy HHmm' \n"
                + "If date/month < 10, remember to add '0' at the front!";
        assertEquals(expected, actual);
    }

    @Test
    public void invalidTimeTaskTestThree() {
        String actual = list.addTask("deadline test book /by18-2-2025 1800", "deadline");
        String expected = "Candy needs the recipe right!\n"
                + "Please input in the format: 'dd-mm-yyyy HHmm' \n"
                + "If date/month < 10, remember to add '0' at the front!";
        assertEquals(expected, actual);
    }
}
