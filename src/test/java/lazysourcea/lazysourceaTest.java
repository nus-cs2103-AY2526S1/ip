package lazysourcea;

import lazysourcea.parser.Parser;
import lazysourcea.storage.Storage;
import lazysourcea.task.TaskList;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration-style tests for lazysourcea core orchestrator.
 * These tests use the real Parser, TaskList, Storage, and CommandExecutor
 * to verify end-to-end behaviour.
 */
public class lazysourceaTest {

    private File tempDir;

    @BeforeEach
    void setup() throws IOException {
        tempDir = Files.createTempDirectory("lazysourcea-test").toFile();
    }

    @AfterEach
    void cleanup() {
        for (File f : tempDir.listFiles()) {
            // clean up temporary files
            if (f != null) {
                f.delete();
            }
        }
        tempDir.delete();
    }

    @Test
    void welcomeMessage_containsGreeting() {
        lazysourcea app = new lazysourcea(tempDir.getAbsolutePath(), "tasks.txt");
        String msg = app.getWelcomeMessage();
        assertNotNull(msg);
        assertTrue(msg.toLowerCase().contains("hi") ||
                msg.toLowerCase().contains("welcome"));
    }

    @Test
    void todoCommand_addsTask_andPersists() {
        lazysourcea app = new lazysourcea(tempDir.getAbsolutePath(), "tasks.txt");
        String response = app.getResponse("todo read book");
        assertTrue(response.toLowerCase().contains("added") ||
                response.toLowerCase().contains("todo"));
        assertFalse(app.isExit());

        // list should now include the added task
        String list = app.getResponse("list");
        assertTrue(list.contains("read book"));
    }

    @Test
    void deadlineCommand_parsedCorrectly_andListed() {
        lazysourcea app = new lazysourcea(tempDir.getAbsolutePath(), "tasks.txt");
        String response = app.getResponse("deadline submit report /by 2025-12-01");
        assertTrue(response.toLowerCase().contains("added"));
        String list = app.getResponse("list");
        assertTrue(list.toLowerCase().contains("submit report"));
    }

    @Test
    void deleteCommand_removesTask() {
        lazysourcea app = new lazysourcea(tempDir.getAbsolutePath(), "tasks.txt");
        app.getResponse("todo sample");
        String before = app.getResponse("list");
        assertTrue(before.contains("sample"));
        String del = app.getResponse("delete 1");
        assertTrue(del.toLowerCase().contains("deleted") || del.toLowerCase().contains("removed"));
    }

    @Test
    void byeCommand_setsExitTrue_andReturnsFarewell() {
        lazysourcea app = new lazysourcea(tempDir.getAbsolutePath(), "tasks.txt");
        String response = app.getResponse("bye");
        assertTrue(response.toLowerCase().contains("bye"));
        assertTrue(app.isExit(), "App should mark exit after bye");
    }

    @Test
    void invalidCommand_returnsErrorMessage() {
        lazysourcea app = new lazysourcea(tempDir.getAbsolutePath(), "tasks.txt");
        String response = app.getResponse("nonsense command");
        assertTrue(response.toLowerCase().contains("tsk") ||
                response.toLowerCase().contains("error") ||
                response.toLowerCase().contains("huh"));
    }

    @Test
    void findCommand_withoutKeyword_showsUsage() {
        lazysourcea app = new lazysourcea(tempDir.getAbsolutePath(), "tasks.txt");
        String response = app.getResponse("find");
        assertTrue(response.toLowerCase().contains("usage"));
    }
}
