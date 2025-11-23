package burh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    void testToString() {
        Todo todo = new Todo("eat chicken nuggets");
        // assuming Task.toString() gives "[ ] read book" initially
        assertEquals("[T][  ] eat chicken nuggets", todo.toString());
    }

    @Test
    void testGetSaveString_notDone() {
        Todo todo = new Todo("say Burh");
        // assuming Task.getSaveString() returns "0|" for not done
        assertEquals("T|F|say Burh" , todo.getSaveString());
    }
}
