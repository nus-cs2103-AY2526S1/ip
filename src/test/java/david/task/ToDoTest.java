package david.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toDoTest() throws Exception {
        ToDo test = new ToDo("test");
        assertEquals("[T][ ] test", test.toString());

        test.markAsDone();
        assertEquals("[T][X] test", test.toString());

        test.markAsUndone();
        assertEquals("[T][ ] test", test.toString());

    }

    @Test
    public void anotherDummyTest() {
        assertEquals(4, 4);
    }
}
