package john;

import john.tasks.Deadline;
import john.tasks.Event;
import john.tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void get_correctIndex() {
        TaskList list = new TaskList();
        list.addTask(new Event("eveeeveve", LocalDate.now(), LocalDate.now()));
        list.addTask(new Deadline("dedede", LocalDate.now()));
        list.addTask(new ToDo("tdopop"));
        assertTrue(list.get(1).toString().contains("eveeeveve"));
        assertTrue(list.get(2).toString().contains("dedede"));
        assertTrue(list.get(3).toString().contains("tdopop"));
    }
    @Test
    public void deleteTask() {
        TaskList list = new TaskList();
        list.addTask(new Event("eveeeveve", LocalDate.now(), LocalDate.now()));
        list.addTask(new Deadline("dedede", LocalDate.now()));
        list.addTask(new ToDo("tdopop"));
        list.deleteTask(2);
        assertTrue(list.get(1).toString().contains("eveeeveve"));
        assertTrue(list.get(2).toString().contains("tdopop"));
    }
}
