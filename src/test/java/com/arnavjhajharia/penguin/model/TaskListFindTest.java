package com.arnavjhajharia.penguin.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListFindTest {

    private TaskList tasks;

    @BeforeEach
    void setUp() {
        tasks = new TaskList(20);
        tasks.add("read book", TaskType.TODO);                    // 1
        tasks.add("booking venue", TaskType.TODO);                // 2
        tasks.add("team meeting notes", TaskType.TODO);           // 3
        tasks.add("Project status Aug update", TaskType.TODO);    // 4
    }

    @Test
    void find_partialSubstring_matchesMultiple() {
        String result = tasks.find("book").toString();
        assertTrue(result.contains("read book"));
        assertTrue(result.contains("booking venue"));
        // Should not pull unrelated
        assertFalse(result.contains("team meeting notes"));
    }

    @Test
    void find_phraseQuoted_matchesExactPhrase() {
        String result = tasks.find("\"team meeting\"").toString();
        assertTrue(result.contains("team meeting notes"));
        assertFalse(result.contains("read book"));
        assertFalse(result.contains("booking venue"));
    }

    @Test
    void find_typoTolerance_oneEditDistance_matches() {
        String result = tasks.find("bokk").toString(); // one substitution from 'book'
        assertTrue(result.contains("read book"));
        // 'bokk' should not match 'booking' (length gap > 1, not substring)
        assertFalse(result.contains("booking venue"));
    }

    @Test
    void find_allTermsMustMatch_withTokens() {
        String result = tasks.find("project Aug").toString();
        assertTrue(result.contains("Project status Aug update"));
        assertFalse(result.contains("read book"));
        assertFalse(result.contains("booking venue"));
        assertFalse(result.contains("team meeting notes"));
    }
}


