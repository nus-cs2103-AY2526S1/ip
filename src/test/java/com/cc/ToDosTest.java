package com.cc;

import com.cc.tasks.ToDos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDosTest {
    @Test
    void createsTodo() {
        ToDos t = new ToDos("run");
        assertEquals("[T][ ] run", t.toString());
    }
}