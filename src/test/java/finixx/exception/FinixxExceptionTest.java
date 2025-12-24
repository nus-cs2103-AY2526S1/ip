package finixx.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the FinixxException class.
 */
class FinixxExceptionTest {
    @Test
    void finixxException_messageStoredCorrectly() {
        FinixxException e = new FinixxException("Error occurred");
        assertEquals("Error occurred", e.getMessage());
    }
}
