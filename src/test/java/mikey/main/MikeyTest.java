package mikey.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

//Claude AI was used for implementing these tests
class MikeyTest {
    @TempDir
    Path tempDir;

    private Mikey mikey;

    @BeforeEach
    void setUp() {
        Path testFile = tempDir.resolve("test_mikey.txt");
        mikey = new Mikey(testFile.toString());
    }

    @Test
    @DisplayName("Should process todo command")
    void testTodoCommand() {
        String response = mikey.getResponse("todo buy milk");
        assertFalse(response.contains("Invalid"));
        assertTrue(response.contains("added"));
    }

    @Test
    @DisplayName("Should process list command")
    void testListCommand() {
        mikey.getResponse("todo test task");
        String response = mikey.getResponse("list");
        assertTrue(response.contains("test task"));
    }

    @Test
    @DisplayName("Should handle invalid commands")
    void testInvalidCommand() {
        String response = mikey.getResponse("invalid command");
        assertTrue(response.contains("Unknown command") || response.contains("Invalid"));
    }

    @Test
    @DisplayName("Should handle mark command")
    void testMarkCommand() {
        mikey.getResponse("todo test task");
        String response = mikey.getResponse("mark 1");
        assertTrue(response.contains("marked") && response.contains("done"));
    }

    @Test
    @DisplayName("Should handle delete command")
    void testDeleteCommand() {
        mikey.getResponse("todo test task");
        String response = mikey.getResponse("delete 1");
        assertTrue(response.contains("removed"));
    }

    @Test
    @DisplayName("Should set exit flag on bye command")
    void testByeCommand() {
        assertFalse(mikey.isExit());
        mikey.getResponse("bye");
        assertTrue(mikey.isExit());
    }

    @Test
    @DisplayName("Should handle deadline command")
    void testDeadlineCommand() {
        String response = mikey.getResponse("deadline assignment /by 1/1/2024 1200");
        assertTrue(response.contains("added"));
        assertTrue(response.contains("assignment"));
    }

    @Test
    @DisplayName("Should handle event command")
    void testEventCommand() {
        String response = mikey.getResponse("event meeting /from 1/1/2024 1000 /to 1/1/2024 1200");
        assertTrue(response.contains("added"));
        assertTrue(response.contains("meeting"));
    }
}