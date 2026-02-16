package peppa.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void toSaveFileFormat_markNotDone_success() {
        ToDo todo = new ToDo("read book");
        assertEquals("T | 0 | read book", todo.toSaveFileFormat());
    }

    @Test
    void toSaveFileFormat_markDone_success() {
        ToDo todo = new ToDo("write code");
        todo.markAsDone();
        assertEquals("T | 1 | write code", todo.toSaveFileFormat());
    }
}
