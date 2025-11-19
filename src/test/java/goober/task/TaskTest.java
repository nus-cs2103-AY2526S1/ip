package goober.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import goober.helper.Parser;

public class TaskTest {

    @Test
    @DisplayName("Task/Todo: toString exact format + done/undone toggling")
    void todoToString_afterMarkDone_formatsAsDone() {
        Todo t = new Todo("read book");

        // default: undone
        assertEquals("[T][ ]( ) read book", t.toString(), "Initial Todo format should be [T][ ] description");

        // mark done
        t.markComplete();
        assertEquals("[T][X]( ) read book", t.toString(), "After markComplete, Todo should show [X]");

        // unmark
        t.unmarkComplete();
        assertEquals("[T][ ]( ) read book", t.toString(), "After unmarkComplete, Todo should show [ ] again");
    }

    @Test
    @DisplayName("Task implements Serializable")
    void taskSerialization_defaultInstance_isSerializable() {
        Task task = new Task("anything");
        assertInstanceOf(Serializable.class, task, "Task should implement Serializable");
    }

    @Test
    @DisplayName("Deadline: exact toString with Parser.dateTimeToString(by)")
    void deadlineToString_withDueDate_formatsAsExpected() {
        LocalDateTime by = LocalDateTime.of(2025, 9, 1, 18, 0);
        Deadline d = new Deadline("return book", by);

        String expectedUndone = "[D][ ]( ) return book (by: " + Parser.dateTimeToString(by) + ")";
        assertEquals(expectedUndone, d.toString(), "Deadline undone format must match exactly");

        d.markComplete();
        String expectedDone = "[D][X]( ) return book (by: " + Parser.dateTimeToString(by) + ")";
        assertEquals(expectedDone, d.toString(), "Deadline done format must match exactly");
    }

    @Test
    @DisplayName("Event: exact toString with Parser.dateTimeToString(from/to)")
    void eventToString_withFromAndTo_formatsAsExpected() {
        LocalDateTime from = LocalDateTime.of(2025, 9, 1, 14, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 1, 16, 0);
        Event e = new Event("CS2103T lecture", from, to);

        String expectedUndone =
                "[E][ ]( ) CS2103T lecture (from: " + Parser.dateTimeToString(from) + " to: " + Parser.dateTimeToString(
                        to) + ")";
        assertEquals(expectedUndone, e.toString(), "Event undone format must match exactly");

        e.markComplete();
        String expectedDone =
                "[E][X]( ) CS2103T lecture (from: " + Parser.dateTimeToString(from) + " to: " + Parser.dateTimeToString(
                        to) + ")";
        assertEquals(expectedDone, e.toString(), "Event done format must match exactly");
    }
}
