package betty.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void todo_toString_correctFormat() {
        Task task = new Todo("read book", false);

        assertEquals("[T][ ] read book ", task.toString());
    }

    @Test
    public void todo_toString_markDone() {
        Task todo = new Todo("read book", false);
        todo.markAsDone();

        assertEquals("[T][X] read book ", todo.toString());
    }

}
