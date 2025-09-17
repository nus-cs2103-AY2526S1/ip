package haru.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import haru.exception.HaruException;
import haru.exception.InvalidTaskIdException;

public class TaskListTest {
    @Test
    public void parseTaskIdTest() throws HaruException, IOException {
        TaskList taskList = TaskList.empty(null);
        Task task1 = new ToDo("read book");
        Task task2 = new ToDo("return book");
        Task task3 = new ToDo("buy bread");
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        assertEquals(0, taskList.parseTaskId("1"));
        assertEquals(1, taskList.parseTaskId("2"));
        assertEquals(2, taskList.parseTaskId("3"));
        assertThrows(InvalidTaskIdException.class, () -> taskList.parseTaskId("-1"));
        assertThrows(InvalidTaskIdException.class, () -> taskList.parseTaskId("4"));
        assertThrows(InvalidTaskIdException.class, () -> taskList.parseTaskId("abc"));
    }
}
