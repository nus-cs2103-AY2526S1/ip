package yappy.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import yappy.task.Deadline;


public class DeadlineTest {

    @Test
    public void testToFileString_correctFormat() {
        LocalDateTime by = LocalDateTime.of(2025, 8, 31, 23, 59);
        Deadline deadlineTask = new Deadline("Submit report", true, by);
        assertEquals("D|true|Submit report|2025-08-31 23:59\n", deadlineTask.toFileString());
    }

    @Test
    public void testToString_correctFormat() {
        LocalDateTime by = LocalDateTime.of(2025, 8, 31, 23, 59);
        Deadline deadlineTask = new Deadline("Submit report", true, by);
        assertEquals("[D][X] Submit report (by: Aug 31 2025 23:59)", deadlineTask.toString());
    }
}
