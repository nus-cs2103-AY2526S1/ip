package minhgpt.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;

public class TaskTest {
    @Test
    public void parseTaskTest() {
        Task.initialise();
        try {
            assertEquals(Task.parseTask("todo task1") instanceof TaskTodo, true);
            assertEquals(Task.parseTask("deadline task2 /by 1970-01-01") instanceof TaskDeadline,
                    true);
            assertEquals(
                    Task.parseTask(
                            "event task3 /from 1970-01-01 /to 1970-01-01") instanceof TaskEvent,
                    true);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
}
