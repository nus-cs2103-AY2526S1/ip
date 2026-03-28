package Meal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    void deleteTest() {
        TaskList list = new TaskList();
        list.add(new Task("shower"));
        list.add(new Task("clean room"));

        Task removedTask = list.delete(2);
        assertEquals("clean room", removedTask.getTaskName());
        assertEquals(1, list.size());
        assertEquals("shower", list.get(0).getTaskName());
    }
}