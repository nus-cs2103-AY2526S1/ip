package chatowo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private Parser parser = new Parser(new Chatowo());

    @Test
    public void addTodoTask_noName_exceptionThrown() {
        try {
            parser.addTodoTask(new String[]{"todo"}, "todo");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Oopsies... Add a name for your todo task pwease... >w<", e.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_noName_exceptionThrown() {
        try {
            parser.addDeadlineTask(new String[]{"deadline", "/by", "2025-08-29"}, "deadline /by 2025-08-29");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Oopsies... Add a name for your deadline task pwease... >w<", e.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_invalidDate_exceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            parser.addDeadlineTask(new String[]{"deadline", "task", "/by", "today"}, "deadline task /by today");
        });
    }
}
