package siri.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTaskTest {
    @Test
    void display_formatsIsoDate_inEnglishShortMonth() {
        DeadlineTask d = new DeadlineTask("return book", "2020-10-10");
        String s = d.display();
        assertTrue(s.contains("Oct 10 2020"), "Expected pretty date like 'Oct 10 2020': " + s);
        assertTrue(s.startsWith("[D]["), "Should start with [D][ ] or [D][X]");
    }

    @Test
    void display_keepsFreeText_whenNotParsable() {
        DeadlineTask d = new DeadlineTask("return book", "Sunday");
        String s = d.display();
        assertTrue(s.contains("(by: Sunday)"));
    }

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }
}
