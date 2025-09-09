package phuc.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import phuc.exception.PhucException;

public class ProcessInputTest {
    private ProcessInput processInput;

    @BeforeEach
    void setUp() {
        processInput = new ProcessInput();
    }

    @Test
    void testStartDoesNotThrow() {
        assertDoesNotThrow(() -> processInput.sayGreetingMessage());
    }

    @Test
    void testEndDoesNotThrow() {
        assertDoesNotThrow(() -> processInput.sayGoodbyeMessage());
    }

    @Test
    void testLoadDoesNotThrow() {
        assertDoesNotThrow(() -> processInput.loadDataFromStorage());
    }

    @Test
    void testProcessUnknownCommandThrows() {
        assertThrows(PhucException.class, () -> processInput.process("unknowncommand"));
    }
}
