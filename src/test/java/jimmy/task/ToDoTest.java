package jimmy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testMarkDone() {
        ToDo todo1 = new ToDo("ToDo 1", false, Task.EMPTY_TAG);
        todo1.markDone();
        assertEquals("[T][X] ToDo 1", todo1.toString(), "ToDo is not marked done successfully");
        todo1.markNotDone();
        assertEquals("[T][ ] ToDo 1", todo1.toString(), "ToDo is not marked not done successfully");
    }

    @Test
    public void testToStorageString() {
        ToDo todo1 = new ToDo("ToDo 1", false, Task.EMPTY_TAG);
        assertEquals("|TODO|ToDo 1|false", todo1.toStorageString(), "ToDo toStorageString() is not correct");
        todo1.markDone();
        assertEquals("|TODO|ToDo 1|true", todo1.toStorageString(), "ToDo toStorageString() is not correct");
    }

    @Test
    public void testToString() {
        ToDo todo1 = new ToDo("ToDo 1", false, Task.EMPTY_TAG);
        assertEquals("[T][ ] ToDo 1", todo1.toString(), "ToDo toString() is not correct");
        todo1.markDone();
        assertEquals("[T][X] ToDo 1", todo1.toString(), "ToDo toString() is not correct");
    }
}
