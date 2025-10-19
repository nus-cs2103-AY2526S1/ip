package com.cc;

import com.cc.tasks.Deadlines;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlinesTest {
    @Test
    void prettyPrintsDate() {
        Deadlines d = new Deadlines("return book", "2019-12-02");
        assertTrue(d.toString().contains("Dec 02 2019"));
    }
}
