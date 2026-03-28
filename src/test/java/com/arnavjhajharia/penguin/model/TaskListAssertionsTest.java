package com.arnavjhajharia.penguin.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskListAssertionsTest {

    @Test
    void constructor_limitMustBePositive_asserts() {
        assertThrows(AssertionError.class, () -> new TaskList(0));
        assertThrows(AssertionError.class, () -> new TaskList(-1));
    }

    @Test
    void loadFromFile_blankPath_asserts() {
        TaskList tl = new TaskList(5);
        assertThrows(AssertionError.class, () -> tl.loadFromFile("  "));
    }

    @Test
    void saveToFile_blankPath_asserts() {
        TaskList tl = new TaskList(5);
        assertThrows(AssertionError.class, () -> tl.saveToFile(" "));
    }

    @Test
    void add_nullType_asserts() {
        TaskList tl = new TaskList(5);
        assertThrows(AssertionError.class, () -> tl.add("anything", null));
    }
}


