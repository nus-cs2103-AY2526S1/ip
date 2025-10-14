package lux.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lux.data.DeadlineTask;

public class DeadlineTaskTest {
    @Test
    public void containsMatchesDescription() {
        DeadlineTask d = new DeadlineTask("submit", java.time.LocalDateTime.now());
        assertTrue(d.contains("submit"));
    }
}
