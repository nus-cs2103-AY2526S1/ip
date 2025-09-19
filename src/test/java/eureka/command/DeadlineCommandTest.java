package eureka.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import eureka.Parser;

public class DeadlineCommandTest {

    @Test
    public void testExit() {
        assertFalse(Parser.parse("deadline Test /by 2025-09-01 18:00").isExit());
    }

    @Test
    public void parse_validDeadline_returnsDeadlineCommand() {
        var cmd = Parser.parse("deadline Finish report /by 2025-09-01 18:00");
        assertInstanceOf(DeadlineCommand.class, cmd);
    }

    @Test
    public void parse_validDeadline_setsFieldsCorrectly() throws Exception {
        var cmd = Parser.parse("deadline Finish report /by 2025-09-01 18:00");
        assertInstanceOf(DeadlineCommand.class, cmd);

        // reflectively verify private fields
        Field taskNameF = DeadlineCommand.class.getDeclaredField("taskName");
        taskNameF.setAccessible(true);
        Field byF = DeadlineCommand.class.getDeclaredField("by");
        byF.setAccessible(true);

        assertEquals("Finish report", taskNameF.get(cmd));
        assertEquals(LocalDateTime.of(2025, 9, 1, 18, 0), byF.get(cmd));
    }
}
