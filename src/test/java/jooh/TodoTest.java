package jooh;

import org.junit.jupiter.api.Test;
import jooh.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todo_toString_correctFormat() {
        Todo todo = new Todo("buy milk", false);
        assertEquals("[T] [ ] buy milk", todo.toString());
    }
}
