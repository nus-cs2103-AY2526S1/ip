package aries;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AriesTest {

    private static final Path STORE = Path.of("data", "aries_tasks.ser");

    @BeforeEach
    void cleanStorage() throws Exception {
        Files.createDirectories(STORE.getParent());
        Files.deleteIfExists(STORE);
    }

    private String run(String... script) {
        Aries app = new Aries();

        StringBuilder out = new StringBuilder();
        out.append(app.getWelcomeMessage()).append("\n");

        for (String line : script) {
            String reply = app.getResponse(line);
            out.append(reply).append("\n");
        }
        return out.toString();
    }

    @Test
    void greetTest() throws Exception {
        String out = run("bye");
        assertTrue(out.contains("Hello! I'm Aries."));
    }

    @Test
    void addTest() throws Exception {
        String out = run("todo read book\nbye");
        assertTrue(out.contains("Got it. I've added this task:") || out.contains("Duplicate task detected"));
    }

    @Test
    void printsTodoTest() throws Exception {
        String out = run("todo read book\nbye");
        assertTrue(out.contains("[T] [ ] read book"));
    }

    @Test
    void listsAddedTask() throws Exception {
        String out = run("todo read book\nlist\nbye");
        assertTrue(out.contains("[T] [ ] read book"));
    }
}
