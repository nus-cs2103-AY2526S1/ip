package app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoopTest {
    private Boop boop;

    @BeforeEach
    void setUp() throws IOException {
        Path tempTaskFile = Files.createTempFile("test-tasks", ".txt");
        // Build a Boop instance with test doubles
        boop = new Boop(tempTaskFile.toString());
    }

    @Test
    void boop_greet_sendGreeting() {
        String greeting = boop.initialize();
        assertTrue(greeting.contains("Boop"),
                "Should greet user. Printed: " + greeting);
    }

    @Test
    void boop_wrongCommands_errorMessage() {
        Boop.BoopResponse out = boop.getResponse("blah");
        assertTrue(out.getMessage().contains("Error"),
                "Should print error for invalid command. Printed: " + out.getMessage());
    }

    @Test
    void boop_addTodo_todoAdded() {
        Boop.BoopResponse out = boop.getResponse("todo read book");
        assertTrue(out.getMessage().contains("read book"),
                "Should confirm task added. Printed: " + out.getMessage());
        assertTrue(out.getMessage().contains("[ ]"),
                "Should show uncompleted. Printed: " + out.getMessage());
    }

    @Test
    void boop_addDeadline_deadlineAdded() {
        Boop.BoopResponse out = boop.getResponse("deadline return book /by 2025-09-30");
        assertTrue(out.getMessage().contains("return book (by: Sep 30 2025)"),
                "Should confirm deadline added. Printed: " + out.getMessage());
        assertTrue(out.getMessage().contains("[ ]"),
                "Should show uncompleted. Printed: " + out.getMessage());
    }

    @Test
    void boop_addEvent_eventAdded() {
        Boop.BoopResponse out = boop.getResponse("event party /from dawn /to dusk");
        assertTrue(out.getMessage().contains("party (from: dawn to: dusk)"),
                "Should confirm deadline added. Printed: " + out.getMessage());
        assertTrue(out.getMessage().contains("[ ]"),
                "Should show uncompleted. Printed: " + out.getMessage());
    }

    @Test
    void boop_listTasks_showTasks() {
        boop.getResponse("todo activityA");
        boop.getResponse("todo activityB");
        Boop.BoopResponse out = boop.getResponse("list");
        assertTrue(out.getMessage().contains("activityA") && out.getMessage().contains("activityB"),
                "Should list tasks. Printed: " + out.getMessage());
    }

    @Test
    void boop_markUnmarkTasks_markCompleteIncomplete() {
        boop.getResponse("todo write essay");
        Boop.BoopResponse out1 = boop.getResponse("mark 1");
        assertTrue(out1.getMessage().contains("[X]"),
                "Should show task completed. Printed: " + out1.getMessage());
        Boop.BoopResponse out2 = boop.getResponse("unmark 1");
        assertTrue(out2.getMessage().contains("[ ]"),
                "Should show task uncompleted. Printed: " + out2.getMessage());
    }

    @Test
    void boop_deleteTasks_deleteTasks() {
        boop.getResponse("todo activityA");
        boop.getResponse("todo activityB");
        boop.getResponse("delete 1");
        Boop.BoopResponse out = boop.getResponse("list");
        assertTrue(!out.getMessage().contains("activityA") && out.getMessage().contains("activityB"),
                "Should no longer have deleted tasks. Printed: " + out.getMessage());
    }

    @Test
    void boop_findTasks_showFilteredTasks() {
        boop.getResponse("todo FILTER activityA");
        boop.getResponse("todo activityB");
        boop.getResponse("todo activityC");
        boop.getResponse("todo FILTER activityD");
        boop.getResponse("todo activityE");
        boop.getResponse("todo FILTER activityF");
        Boop.BoopResponse out = boop.getResponse("find filter");
        assertTrue(out.getMessage().contains("activityA")
                && !out.getMessage().contains("activityB")
                && !out.getMessage().contains("activityC")
                && out.getMessage().contains("activityD")
                && !out.getMessage().contains("activityE")
                && out.getMessage().contains("activityF"),
                "Should contain only filtered tasks. Printed: " + out.getMessage());
        assertTrue(out.getMessage().contains("1")
                && !out.getMessage().contains("2")
                && !out.getMessage().contains("3")
                && out.getMessage().contains("4")
                && !out.getMessage().contains("5")
                && out.getMessage().contains("6"),
                "Numbers remain in sequence. Printed: " + out.getMessage());
    }

    @Test
    void boop_undo_singleTaskUndoBehavior() {
        Boop.BoopResponse undoBefore = boop.getResponse("undo");
        assertTrue(undoBefore.getMessage().contains("Nothing to undo"),
                "Should give error when undoing without previous action. Printed: " + undoBefore.getMessage());

        Boop.BoopResponse out = boop.getResponse("todo singleTask");
        assertTrue(out.getMessage().contains("singleTask"),
                "Should confirm task added. Printed: " + out.getMessage());

        Boop.BoopResponse undoAfterAdd = boop.getResponse("undo");
        assertTrue(undoAfterAdd.getMessage().contains("todo singleTask"),
                "Should include the undone command. Printed: " + undoAfterAdd.getMessage());

        Boop.BoopResponse undoAfterUndo = boop.getResponse("undo");
        assertTrue(undoAfterUndo.getMessage().contains("Nothing to undo"),
                "Should give error when undoing again. Printed: " + undoAfterUndo.getMessage());
    }

    @Test
    void boop_exitCommand_setsExitFlag() {
        Boop.BoopResponse out = boop.getResponse("bye");
        assertTrue(out.isExit(),
                "Exit command should set exit flag.");
    }
}
