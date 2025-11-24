package woop;

import org.junit.jupiter.api.Test;
import woop.logic.EventTask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EventTaskTest {
    @Test
    public void equals_sameTask_true() {
        assertEquals(new EventTask("read", "2 pm" , "4 pm"),
                new EventTask("read", "2 pm" , "4 pm"));
    }

    @Test
    public void equals_differentStart_false() {
        assertNotEquals(new EventTask("read", "2 pm" , "4 pm"),
                new EventTask("read", "2pm" , "4 pm"));
    }

    @Test
    public void equals_differentEnd_false() {
        assertNotEquals(new EventTask("read", "2 pm" , "4 pm"),
                new EventTask("read", "2 pm" , "4pm"));
    }
}