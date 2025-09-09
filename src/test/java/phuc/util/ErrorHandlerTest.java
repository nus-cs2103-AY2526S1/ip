package phuc.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import phuc.exception.PhucException;

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
            ErrorHandler.validateDescription("valid description");
        });
    }

    @Test
    public void testValidateDescription_empty() {
        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateDescription("");
        });

        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateDescription("   ");
        });
    }
}
