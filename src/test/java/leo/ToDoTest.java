package leo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDoTest {
    @Test
    public void checkSaveFormat() {
        ToDo t = new ToDo("watch lecture");
        assertEquals("T | 0 | watch lecture", t.toSaveFormat());
    }
}
