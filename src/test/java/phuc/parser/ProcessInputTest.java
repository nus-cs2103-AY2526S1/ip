package phuc.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phuc.exception.PhucException;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessInputTest {
    private ProcessInput processInput;

    @BeforeEach
    void setUp() {
        processInput = new ProcessInput();
    }

    @Test
    void testStartDoesNotThrow() {
        assertDoesNotThrow(() -> processInput.start());
    }

    @Test
    void testEndDoesNotThrow() {
        assertDoesNotThrow(() -> processInput.end());
    }

    @Test
    void testLoadDoesNotThrow() {
        assertDoesNotThrow(() -> processInput.load());
    }

    @Test
    void testProcessUnknownCommandThrows() {
        assertThrows(PhucException.class, () -> processInput.process("unknowncommand"));
    }
}