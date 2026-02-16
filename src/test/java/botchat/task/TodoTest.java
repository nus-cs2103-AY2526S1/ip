package botchat.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo todo = new Todo("borrow book");
        assertEquals("[T][ ] borrow book", todo.toString());
    }

    @Test
    public void toStorageTest(){
        Todo todo = new Todo("return book");
        assertEquals("T | 0 | return book", todo.toStorage());
    }
}
