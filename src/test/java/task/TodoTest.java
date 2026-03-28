package task;  //same package as the class being tested


import lenny.logic.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoTest {
    @Test
    void toString_containsTAndDescription() {
        Todo todo = new Todo("shop groceries", false);
        assertTrue(todo.toString().startsWith("[T]"));
        assertTrue(todo.toString().contains("shop groceries"));
    }
}