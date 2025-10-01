package arn;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testMarkAsDoneAndToString() throws ArnException {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());

        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());

        todo.markAsNotDone();
        assertEquals("[T][ ] read book", todo.toString());
    }
}