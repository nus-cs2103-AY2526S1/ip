package bobbywasabi.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void findTasksThatMatchKeyword_hasMatches_Success() {
        ArrayList<Task> list = new ArrayList<Task>();
        list.add(new ToDo("hello", false));
        list.add(new ToDo("hello world", false));
        list.add(new ToDo("hell", false));
        TaskList tasks = new TaskList(list);
        String actual = tasks.findTasksThatMatchKeyword("hello");

        String expected = """
                1. [T][ ] hello
                2. [T][ ] hello world
                """;

        assertEquals(expected, actual);
    }
}
