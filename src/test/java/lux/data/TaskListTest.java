package lux.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addAndToString() {
        TaskList list = new TaskList();
        list.addTasks(new TodoTask("read"));
        String s = list.toString();
        assertTrue(s.contains("read"));
        assertTrue(s.startsWith("  0."));
    }

    @Test
    public void findReturnsMatchingTasks() {
        ArrayList<lux.data.Task> arr = new ArrayList<>();
        arr.add(new TodoTask("buy milk"));
        arr.add(new TodoTask("read book"));
        TaskList list = new TaskList(arr);
        TaskList found = list.find("read");
        String out = found.toString();
        assertTrue(out.contains("read book"));
    }
}
