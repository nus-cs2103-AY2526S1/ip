package bobby;

import bobby.bobbyexception.BobbyException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BobbyExceptionTest {
    @Test
    void constructor_message_setsMessage() {
        BobbyException e = new BobbyException("error!");
        assertEquals("error!", e.getMessage());
    }

    @Test
    void constructor_nullMessage_usesDefault() {
        BobbyException e = new BobbyException((String) null);
        assertEquals("An error occurred in Bobby.", e.getMessage());
    }

    @Test
    void constructor_cause_setsCause() {
        Throwable cause = new RuntimeException("cause");
        BobbyException e = new BobbyException(cause);
        assertEquals(cause, e.getCause());
        assertEquals("An error occurred in Bobby.", e.getMessage());
    }

    @Test
    void constructor_messageAndCause_setsBoth() {
        Throwable cause = new RuntimeException("cause");
        BobbyException e = new BobbyException("msg", cause);
        assertEquals("msg", e.getMessage());
        assertEquals(cause, e.getCause());
    }
}

