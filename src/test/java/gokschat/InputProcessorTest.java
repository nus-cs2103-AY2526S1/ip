package gokschat;

import gokschat.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/// This class contains tests for the InputProcessor class
///
/// @author Ravichandran Gokul
public class InputProcessorTest {

    // Valid dates for isValidDate method

    @Test
    void isValidDate_standardValidDate_returnsTrue() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/gokschat.txt");
        List<Task> listOfTasks = new ArrayList<>();
        InputProcessor inputProcessor = new InputProcessor(ui, storage, listOfTasks);
        assertEquals(inputProcessor.isValidDate("2025-08-29"), true);
    }

    @Test
    void isValidDate_validLeapDate_returnsTrue() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/gokschat.txt");
        List<Task> listOfTasks = new ArrayList<>();
        InputProcessor inputProcessor = new InputProcessor(ui, storage, listOfTasks);
        assertEquals(inputProcessor.isValidDate("2024-02-29"), true);
    }

    @Test
    void isValidDate_endMonthDate_returnsTrue() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/gokschat.txt");
        List<Task> listOfTasks = new ArrayList<>();
        InputProcessor inputProcessor = new InputProcessor(ui, storage, listOfTasks);
        assertEquals(inputProcessor.isValidDate("2025-10-31"), true);
    }

    // Invalid dates for isValidDate method

    @Test
    void isValidDate_invalidLeapDate_returnsFalse() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/gokschat.txt");
        List<Task> listOfTasks = new ArrayList<>();
        InputProcessor inputProcessor = new InputProcessor(ui, storage, listOfTasks);
        assertEquals(inputProcessor.isValidDate("2025-02-29"), false);
    }

    @Test
    void isValidDate_zeroDate_returnsFalse() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/gokschat.txt");
        List<Task> listOfTasks = new ArrayList<>();
        InputProcessor inputProcessor = new InputProcessor(ui, storage, listOfTasks);
        assertEquals(inputProcessor.isValidDate("2025-02-00"), false);
    }

    @Test
    void isValidDate_invalidEndMonthDate_returnsFalse() {
        Ui ui = new Ui();
        Storage storage = new Storage("src/data/gokschat.txt");
        List<Task> listOfTasks = new ArrayList<>();
        InputProcessor inputProcessor = new InputProcessor(ui, storage, listOfTasks);
        assertEquals(inputProcessor.isValidDate("2025-02-31"), false);
    }
}

