package phuc.errorhandle;

import org.junit.jupiter.api.Test;
import phuc.exception.PhucException;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorHandlerTest {

    @Test
    public void testValidateTaskIndex_valid() {
        assertDoesNotThrow(() -> {
            ErrorHandler.validateTaskIndex("1", 5);
        });
    }

    @Test
    public void testValidateTaskIndex_invalid() {
        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateTaskIndex("0", 5);
        });

        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateTaskIndex("6", 5);
        });

        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateTaskIndex("abc", 5);
        });
    }

    @Test
    public void testValidateDescription_valid() {
        assertDoesNotThrow(() -> {
            ErrorHandler.validateDescription("valid description", "todo", "todo");
        });
    }

    @Test
    public void testValidateDescription_empty() {
        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateDescription("", "todo", "todo");
        });

        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateDescription("   ", "todo", "todo");
        });
    }
}