package sheares;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

//these tests were generated with the help of DeepSeek
public class ShearesTest {

    @TempDir
    Path tempDir;
    private Path testFilePath;
    private Sheares sheares;

    @BeforeEach
    public void setUp() throws IOException {
        testFilePath = tempDir.resolve("test.txt");
        Files.createFile(testFilePath);
        sheares = new Sheares(testFilePath.toString());
    }

    @Test
    public void testConstructorWithValidFilePath() {
        assertNotNull(sheares);
    }

    @Test
    public void testConstructorWithInvalidFilePath() {
        // Should not throw exception even with invalid path
        Sheares invalidPathSheares = new Sheares("/invalid/path/that/does/not/exist.txt");
        assertNotNull(invalidPathSheares);
    }

    @Test
    public void testGetResponseWithEmptyInput() {
        String response = sheares.getResponse("");
        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    public void testGetResponseWithListCommand() {
        String response = sheares.getResponse("list");
        assertNotNull(response);
        assertTrue(response.contains("Here are the tasks in your list")
                || response.contains("tasks in your list"));
    }

    @Test
    public void testGetResponseWithTodoCommand() {
        String response = sheares.getResponse("todo Test task");
        assertNotNull(response);
        assertTrue(response.contains("Got it") || response.contains("added"));
        assertTrue(response.contains("Test task"));
    }

    @Test
    public void testGetResponseWithMarkCommand() {
        // First add a task
        sheares.getResponse("todo Task to mark");
        // Then mark it
        String response = sheares.getResponse("mark 1");
        assertNotNull(response);
        assertTrue(response.contains("marked") || response.contains("done"));
    }

    @Test
    public void testGetResponseWithUnmarkCommand() {
        // First add and mark a task
        sheares.getResponse("todo Task to unmark");
        sheares.getResponse("mark 1");
        // Then unmark it
        String response = sheares.getResponse("unmark 1");
        assertNotNull(response);
        assertTrue(response.contains("unmark") || response.contains("not done"));
    }

    @Test
    public void testGetResponseWithDeleteCommand() {
        // First add a task
        sheares.getResponse("todo Task to delete");
        // Then delete it
        String response = sheares.getResponse("delete 1");
        assertNotNull(response);
        assertTrue(response.contains("remove") || response.contains("delete"));
    }

    @Test
    public void testGetResponseWithFindCommand() {
        // First add a task with specific keyword
        sheares.getResponse("todo Find this specific task");
        // Then search for it
        String response = sheares.getResponse("find specific");
        assertNotNull(response);
        assertTrue(response.contains("Find") || response.contains("match"));
    }

    @Test
    public void testGetResponseWithByeCommand() {
        String response = sheares.getResponse("bye");
        assertNotNull(response);
        assertTrue(response.contains("Bye") || response.contains("see you"));
    }

    @Test
    public void testMultipleCommandsPersistence() {
        // Test that tasks persist between commands
        sheares.getResponse("todo Task 1");
        sheares.getResponse("todo Task 2");

        String listResponse = sheares.getResponse("list");
        assertTrue(listResponse.contains("Task 1") || listResponse.contains("Task 2"));
    }

    @Test
    public void testErrorHandlingForInvalidCommands() {
        String response = sheares.getResponse("invalid command that doesn't exist");
        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    public void testGetResponseReturnsString() {
        String response = sheares.getResponse("help");
        assertNotNull(response);
        assertInstanceOf(String.class, response);
    }

    @Test
    public void testTaskCountingInList() {
        sheares.getResponse("todo Task A");
        sheares.getResponse("todo Task B");
        sheares.getResponse("todo Task C");

        String response = sheares.getResponse("list");
        // Should contain some indication of 3 tasks
        assertTrue(response.contains("3") || response.toLowerCase().contains("three"));
    }
}
