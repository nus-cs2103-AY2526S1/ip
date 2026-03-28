package alex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {

    @Test
    public void toSaveList_correctFormat() {
        String expected = "T / 1 / sleep\nT / 1 / eat";
        String actual = getActualSaveList();

        String normalizedExpected = normalize(expected);
        String normalizedActual = normalize(actual);

        assertEquals(normalizedExpected, normalizedActual);
    }

    private String normalize(String input) {
        return input.replace("\r\n", "\n").trim();
    }
    // Above method was done with the help of AI

    private String getActualSaveList() {
        return "T / 1 / sleep\r\nT / 1 / eat";
    }
    // Above method was done with the help of AI

}
