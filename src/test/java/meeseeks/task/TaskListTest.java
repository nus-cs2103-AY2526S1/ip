package meeseeks.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class TaskListTest {

    @Test
    void testAddAndDelete() {
        TaskList tasks = new TaskList();
        Task t1 = new ToDo("buy milk");
        Task t2 = new ToDo("read book");

        tasks.add(t1);
        tasks.add(t2);

        assertEquals(2, tasks.size());
        assertEquals(t1, tasks.get(0));

        Task removed = tasks.delete(0);
        assertEquals(t1, removed);
        assertEquals(1, tasks.size());
    }
}