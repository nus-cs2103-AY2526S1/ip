package mikey.ui;

import mikey.task.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//Claude AI was used for implementing these tests
class UiTest {
    private Ui ui;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        taskList = new TaskList(null);
    }

    @Test
    @DisplayName("Should format greeting message")
    void testGreeting() {
        String greeting = ui.greet();
        assertNotNull(greeting);
        assertTrue(greeting.contains("Mikey"));
    }

    @Test
    @DisplayName("Should format bye message")
    void testBye() {
        String bye = ui.bye();
        assertNotNull(bye);
        assertTrue(bye.contains("Bye"));
    }

    @Test
    @DisplayName("Should format task list correctly")
    void testPrintTasks() {
        taskList.addTask(new Todo("Test task"));
        String output = ui.printTasks(taskList);

        assertTrue(output.contains("1. [T][ ] Test task"));
    }

    @Test
    @DisplayName("Should handle empty task list")
    void testPrintEmptyTasks() {
        String output = ui.printTasks(taskList);
        assertTrue(output.contains("No tasks yet!"));
    }

    @Test
    @DisplayName("Should format error messages")
    void testPrintError() {
        String error = ui.printError("Test error");
        assertTrue(error.contains("Test error"));
    }

    @Test
    @DisplayName("Should format found tasks")
    void testPrintFoundTasks() {
        Todo task = new Todo("Find me");
        String output = ui.printFoundTasks(Arrays.asList(task));
        assertTrue(output.contains("matching tasks"));
        assertTrue(output.contains("Find me"));
    }

    @Test
    @DisplayName("Should handle no found tasks")
    void testPrintNoFoundTasks() {
        String output = ui.printFoundTasks(Arrays.asList());
        assertTrue(output.contains("No matching tasks found!"));
    }
}