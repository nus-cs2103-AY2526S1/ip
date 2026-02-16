package angus.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class AngusExceptionTest {
    @Test
    void constructor_storesMessageCorrectly() {
        String message = "This is an error!";
        AngusException e = new AngusException(message);

        assertEquals(message, e.getMessage(),
                "AngusException should store and return the message correctly");
        assertInstanceOf(Exception.class, e, "AngusException should be a subclass of Exception");
    }
}
