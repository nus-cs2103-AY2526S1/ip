package ryujiTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ryuji.task.ToDo;

public class ToDoTest {
    
    @Test
    void markShouldChangeStatusToTrue() {
        ToDo toDo = new ToDo("test");
        toDo.mark();

        assertEquals("X", toDo.getStatusIcon());
    }

    @Test
    void unmarkShouldChangeStatusToFalse() {
        ToDo toDo = new ToDo("test", true);
        toDo.unmark();

        assertEquals(" ", toDo.getStatusIcon());
    }
}
