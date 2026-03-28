package ackermann.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ackermann.exceptions.CheckedException;
import ackermann.exceptions.task.EmptyNameException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventDatesException;

public class EventTest {

    @Test
    public void create_correct_event() throws CheckedException {
        Event event = new Event("Test", LocalDate.parse("2025-12-22"), LocalDate.parse("2025-12-24"));

        assertEquals("[E][ ] Test (from: Dec 22 2025 to: Dec 24 2025) [ ]", event.toString());
    }

    @Test
    public void invalid_dateTime_throws_error() {
        assertThrows(InvalidEventDatesException.class,
                ()-> new Event("Test",
                        LocalDate.parse("2025-12-22"),
                        LocalDate.parse("2025-12-21")));
    }

    @Test
    public void empty_name_throws_error() {
        assertThrows(EmptyNameException.class,
                ()-> new Event("",
                        LocalDate.parse("2025-12-22"),
                        LocalDate.parse("2025-12-21")));
    }

    @Test
    public void name_with_space_throws_error() {
        assertThrows(EmptyNameException.class,
                ()-> new Event("    ",
                        LocalDate.parse("2025-12-22"),
                        LocalDate.parse("2025-12-21")));
    }

    @Test
    public void allow_same_from_to_date() throws CheckedException {
        Event event = new Event("Test", LocalDate.parse("2025-12-22"), LocalDate.parse("2025-12-22"));

        assertEquals("[E][ ] Test (from: Dec 22 2025 to: Dec 22 2025) [ ]", event.toString());
    }
}
