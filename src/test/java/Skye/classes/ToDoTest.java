package Skye.classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void todo_testStringConversion(){
        ToDo test = new ToDo("a");
        assertEquals("[T][ ] a", test.toString());
    }

}
