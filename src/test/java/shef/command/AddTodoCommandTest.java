package shef.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import shef.exception.InvalidArgumentException;

public class AddTodoCommandTest {
    @Test
    public void isExitTest() {
        assertFalse(new AddTodoCommand("").isExit());
    }

    @Test
    public void invalidCommandTest() {
        AddTodoCommand invalidArgs = new AddTodoCommand("");
        assertThrows(InvalidArgumentException.class, () -> invalidArgs.execute(null, null));
    }
}
