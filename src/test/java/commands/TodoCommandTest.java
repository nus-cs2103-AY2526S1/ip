package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.DawaeException;
import storage.Storage;
import taskstuff.TaskList;
import ui.Ui;

public class TodoCommandTest {
    @Test
    public void execute_noArgs_DawaeMissingArgumentExceptionThrown() {
        try {
            new TodoCommand("").execute(new TaskList(new ArrayList<>()), new Ui(), new Storage("Dawaetasks.txt"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Brah, give me ur task description...\uD83D\uDE44", e.getMessage());
        }
    }
    
    @Test
    public void testExecuteSuccess() throws DawaeException {
        assertTrue(new TodoCommand("Todo Eat").execute(
                new TaskList(new ArrayList<>()), new Ui(), new Storage("Dawaetasks.txt")));
    }
}
