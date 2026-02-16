package alice;

import alice.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTest {

    @Test
    public void customExceptions_haveMessages() {
        AliceException e = new MissingParameterException("Missing!");
        assertEquals("Missing!", e.getMessage());

        e = new InvalidParameterException("Invalid!");
        assertEquals("Invalid!", e.getMessage());
    }
}
