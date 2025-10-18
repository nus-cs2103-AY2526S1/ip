package uy;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import uy.Deadlines;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    @Test
    public void testDeadlineCreation() {
        Deadlines deadline = new Deadlines("Submit assignment /by 2025-09-01");
        assertEquals(deadline.toString(), "[D][] Submit assignment (by: Sep 01 2025)");
    }
}
