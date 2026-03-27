package phuc.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void testValidateTaskIndexValid() {
        assertDoesNotThrow(() -> {
            ErrorHandler.validateTaskIndex("1", 5);
        });
    }

    @Test
    void testValidateTaskIndexInvalid() {
        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateTaskIndex("10", 5);
        });

        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateTaskIndex("abc", 5);
        });
    }

    @Test
    void testValidateDescriptionValid() {
        assertDoesNotThrow(() -> {
            ErrorHandler.validateDescription("Valid description");
        });
    }

    @Test
    void testValidateDescriptionInvalid() {
        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateDescription("");
        });

        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateDescription("   ");
        });
    }

    @Test
    void testValidateEventFormatValid() throws PhucException {
        String[] result = ErrorHandler.validateEventFormat(
                "Meeting /from 25/12/2024 1000 /to 25/12/2024 1200"
        );
        assertEquals("Meeting", result[0]);
        assertEquals("25/12/2024 1000", result[1]);
        assertEquals("25/12/2024 1200", result[2]);
    }

    @Test
    void testValidateEventFormatInvalid() {
        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateEventFormat("");
        });

        assertThrows(PhucException.class, () -> {
            ErrorHandler.validateEventFormat("Meeting /from");
        });
    }
}
