package cheryl.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void markTask_setsDoneStatusCorrectly() {
        Todo todo = new Todo("Read book");
        assertFalse(todo.isDone());      // initially not done
        todo.mark();
        assertTrue(todo.isDone());       // should be marked done
    }
}
