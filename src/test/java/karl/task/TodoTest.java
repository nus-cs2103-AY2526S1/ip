package karl.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void constructor_setsDescription() {
        Todo todo = new Todo("Read book");
        assertEquals("Read book", todo.getDescription());
    }

    @Test
    public void toString_formatsTodoCorrectlyWithNotDone() {
        Todo todo = new Todo("sleep");
        assertEquals("[T][ ] sleep", todo.toString());
    }

    @Test
    public void toString_formatsTodoCorrectlyWithDone() {
        Todo todo = new Todo("submit work");
        todo.markAsDone();
        assertEquals("[T][X] submit work", todo.toString());
    }
}

// Used AI to help clean up code and naming conventions etc, mainly small issues brought up through code reviews