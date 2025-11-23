package burh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    void testAdditionAndDeletion() {
        TaskList t = new TaskList();
        t.addTask(new Todo("say hi"));
        t.addTask(new Todo("say bye"));
        t.deleteTask(1);
        assertEquals(1, t.getStringList().size());

    }

    @Test
    void testMarking() {
        TaskList t = new TaskList();
        t.addTask(new Todo("say hi"));
        t.addTask(new Todo("say bye"));
        t.completeTask(2);
        List<String> l = t.getStringList();
        assertEquals("F" , l.get(0).split("\\|")[1]);
        assertEquals("T" , l.get(1).split("\\|")[1]);
    }
}
