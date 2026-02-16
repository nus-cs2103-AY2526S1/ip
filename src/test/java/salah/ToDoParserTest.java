package salah;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ToDoParserTest {

    @Test
    @DisplayName("Parses a valid todo")
    void parsesValidTodo() throws Exception {
        ToDos todo = ToDos.parser("read book");
        assertEquals("read book", todo.getDescription());
        assertFalse(todo.getIsComplete());
    }

    @Test
    @DisplayName("Rejects empty todo description")
    void rejectsEmptyTodo() {
        EmptyDescriptionException ex = assertThrows(
                EmptyDescriptionException.class,
                () -> ToDos.parser("")   // no description
        );
        assertTrue(ex.getMessage().toLowerCase().contains("description"));
    }
}
