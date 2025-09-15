package bobby.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bobby.exception.BobbyException;
import bobby.task.TaskList;

public class ParserTest {
    private TaskList taskList;
    private Parser parser;

    @BeforeEach
    void setUp() throws BobbyException {
        taskList = new TaskList(List.of());
        parser = new Parser(taskList);
    }

    @Test
    void testProcessCommandBye() throws BobbyException {
        assertEquals(false, parser.processCommand("bye"));
    }

    @Test
    void testProcessCommandList() throws BobbyException {
        assertEquals(true, parser.processCommand("list"));
    }
}
