package ackermann.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ackermann.exceptions.CheckedException;
import ackermann.exceptions.task.EmptyNameException;

public class DeadlineTest {
    @Test
    public void create_correct_deadline() throws CheckedException {
        Deadline deadline = new Deadline("Test", LocalDate.parse("2025-12-22"));

        assertEquals("[D][ ] Test (by Dec 22 2025) [ ]", deadline.toString());
    }

    @Test
    public void empty_name_throws_error() {
        assertThrows(EmptyNameException.class,
                ()-> new Deadline("",
                        LocalDate.parse("2025-12-22")));
    }

    @Test
    public void name_with_space_throws_error() {
        assertThrows(EmptyNameException.class,
                ()-> new Deadline("    ",
                        LocalDate.parse("2025-12-22")));
    }

    @Test
    public void saves_edge_dates_correctly() throws CheckedException {
        Deadline deadline = new Deadline("Test", LocalDate.parse("2025-12-31"));

        assertEquals("[D][ ] Test (by Dec 31 2025) [ ]", deadline.toString());
    }
}
