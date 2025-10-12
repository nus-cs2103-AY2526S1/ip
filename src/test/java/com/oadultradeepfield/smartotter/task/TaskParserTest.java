package com.oadultradeepfield.smartotter.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.oadultradeepfield.smartotter.SmartOtterException;

class TaskParserTest {
    @Test
    void createTodoTask_shouldReturnTodoTask_whenTypeIsT() {
        Optional<Task> taskOpt = TaskParser.createTask("T", 0, "Buy milk", null, null);

        assertTrue(taskOpt.isPresent());
        Task task = taskOpt.get();
        assertInstanceOf(ToDoTask.class, task);
        assertEquals("Buy milk", task.getTaskName());
        assertFalse(task.isDone());
    }

    @Test
    void createDeadlineTask_shouldReturnDeadlineTaskWithDeadline() {
        LocalDateTime deadline = LocalDateTime.of(2025, 1, 1, 12, 0);
        Optional<Task> taskOpt = TaskParser.createTask("D", 1, "Submit report", deadline, null);

        assertTrue(taskOpt.isPresent());
        Task task = taskOpt.get();
        assertInstanceOf(DeadlineTask.class, task);
        assertEquals("Submit report", task.getTaskName());
        assertTrue(task.isDone()); // because status=1
    }

    @Test
    void createEventTask_shouldReturnEventTaskWithTimes() {
        LocalDateTime start = LocalDateTime.of(2025, 1, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 1, 10, 0);

        Optional<Task> taskOpt = TaskParser.createTask("E", 0, "Meeting", start, end);

        assertTrue(taskOpt.isPresent());
        Task task = taskOpt.get();
        assertInstanceOf(EventTask.class, task);
        assertEquals("Meeting", task.getTaskName());
        assertFalse(task.isDone());
    }

    @Test
    void createTask_shouldReturnEmptyOptional_whenTypeIsUnknown() {
        Optional<Task> taskOpt = TaskParser.createTask("X", 0, "Unknown", null, null);
        assertTrue(taskOpt.isEmpty());
    }

    @Test
    void parse_shouldThrowException_whenEndTimeBeforeStartTime() {
        String line = "E | 0 | Meeting | 1/1/2025 1000 | 1/1/2025 0900";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> TaskParser.parse(line));

        assertEquals("😵‍💫 Oops! - Start time must be before end time.", exception.getMessage());
    }

    @Test
    void parse_shouldThrowException_whenStatusIsInvalid() {
        String line = "T | 5 | Buy milk";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> TaskParser.parse(line));

        assertEquals("😵‍💫 Oops! - Invalid status value", exception.getMessage());
    }

    @Test
    void parse_shouldThrowException_whenDateIsInvalid() {
        String line = "D | 0 | Submit report | invalid-date";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> TaskParser.parse(line));

        assertTrue(exception.getMessage().startsWith("😵‍💫 Oops! - Invalid deadline or start time"));
    }

    @Test
    void parse_shouldThrowException_whenNotEnoughParts() {
        String line = "T | 0";

        SmartOtterException exception =
            assertThrows(SmartOtterException.class, () -> TaskParser.parse(line));

        assertEquals("😵‍💫 Oops! - Not enough parts", exception.getMessage());
    }
}
