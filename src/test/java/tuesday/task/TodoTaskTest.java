package tuesday.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void toStringTestUnmarkedTest() {
        TodoTask todo = new TodoTask("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void toStringTestMarkedTest() {
        TodoTask todo = new TodoTask("read book");
        todo.markDone();
        assertEquals("[T][X] read book", todo.toString());
    }
}
