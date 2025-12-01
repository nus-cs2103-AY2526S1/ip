package tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

//learnt about ordering with chatGPT
@TestMethodOrder(OrderAnnotation.class)
public class TodoTaskTest {
    private TaskInformation info = new TaskInformation("todo read book", "todo");
    private Task todo = new TodoTask(info);

    @Test
    @Order(1)
    public void todoFormatStringTest() {
        String expected = "[T][ ] read book";
        String actual = todo.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void todoFormatStorageTest() {
        String expected = "T |   | read book";
        String actual = todo.toSave();
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    public void markDoneTest() {
        todo.markDone();
        String expected = "[T][X] read book";
        String actual = todo.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    public void markUndoneTest() {
        TodoTask todo = new TodoTask(info);
        todo.markDone();
        todo.markUndone();
        String expected = "[T][ ] read book";
        String actual = todo.toString();
        assertEquals(expected, actual);
    }
}
