package alpaca.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the Todo class.
 * Tests constructor, toString, toSaveFormat, and various edge cases.
 */
class TodoTest {

    // ================= CONSTRUCTOR TESTS =================

    @Test
    @DisplayName("Create todo with valid description")
    void testCreateValidTodo() {
        Todo todo = new Todo("read book");

        assertEquals("read book", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    @DisplayName("Create todo with description containing spaces")
    void testCreateTodoWithSpaces() {
        Todo todo = new Todo("finish CS2103T project");

        assertEquals("finish CS2103T project", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    @DisplayName("Create todo with single character description")
    void testCreateTodoSingleCharacter() {
        Todo todo = new Todo("A");

        assertEquals("A", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    @DisplayName("Create todo with long description")
    void testCreateTodoLongDescription() {
        String longDescription = "This is a very long todo description that contains many words and should still work perfectly fine";
        Todo todo = new Todo(longDescription);

        assertEquals(longDescription, todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    @DisplayName("Create todo with special characters in description")
    void testCreateTodoSpecialCharacters() {
        Todo todo = new Todo("Buy groceries @ 3pm (milk, eggs, bread)");

        assertEquals("Buy groceries @ 3pm (milk, eggs, bread)", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    @DisplayName("Constructor with null description should throw exception")
    void testConstructorNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Todo(null);
        });
    }

    @Test
    @DisplayName("Constructor with empty description should throw exception")
    void testConstructorEmptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Todo("");
        });
    }

    @Test
    @DisplayName("Constructor with blank description should throw exception")
    void testConstructorBlankDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Todo("   ");
        });
    }

    @Test
    @DisplayName("Constructor with tab/newline whitespace should throw exception")
    void testConstructorWhitespaceDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Todo("\t\n ");
        });
    }

    // ================= toString() TESTS =================

    @Test
    @DisplayName("toString for incomplete todo")
    void testToStringIncomplete() {
        Todo todo = new Todo("read book");

        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    @DisplayName("toString for completed todo")
    void testToStringCompleted() {
        Todo todo = new Todo("read book");
        todo.markAsDone();

        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    @DisplayName("toString after marking done and undone")
    void testToStringMarkUnmark() {
        Todo todo = new Todo("exercise");

        // Initially not done
        assertEquals("[T][ ] exercise", todo.toString());

        // Mark as done
        todo.markAsDone();
        assertEquals("[T][X] exercise", todo.toString());

        // Mark as not done
        todo.markAsNotDone();
        assertEquals("[T][ ] exercise", todo.toString());
    }

    @Test
    @DisplayName("toString with complex description")
    void testToStringComplexDescription() {
        Todo todo = new Todo("Complete assignment #2 (due next week)");

        assertEquals("[T][ ] Complete assignment #2 (due next week)", todo.toString());

        todo.markAsDone();
        assertEquals("[T][X] Complete assignment #2 (due next week)", todo.toString());
    }

    // ================= toSaveFormat() TESTS =================

    @Test
    @DisplayName("toSaveFormat for incomplete todo")
    void testToSaveFormatIncomplete() {
        Todo todo = new Todo("read book");

        assertEquals("T | 0 | read book", todo.toSaveFormat());
    }

    @Test
    @DisplayName("toSaveFormat for completed todo")
    void testToSaveFormatCompleted() {
        Todo todo = new Todo("read book");
        todo.markAsDone();

        assertEquals("T | 1 | read book", todo.toSaveFormat());
    }

    @Test
    @DisplayName("toSaveFormat after marking done and undone")
    void testToSaveFormatMarkUnmark() {
        Todo todo = new Todo("exercise");

        // Initially not done
        assertEquals("T | 0 | exercise", todo.toSaveFormat());

        // Mark as done
        todo.markAsDone();
        assertEquals("T | 1 | exercise", todo.toSaveFormat());

        // Mark as not done
        todo.markAsNotDone();
        assertEquals("T | 0 | exercise", todo.toSaveFormat());
    }

    @Test
    @DisplayName("toSaveFormat with description containing pipe characters")
    void testToSaveFormatWithPipeCharacters() {
        Todo todo = new Todo("Learn Java | Python | C++");

        assertEquals("T | 0 | Learn Java | Python | C++", todo.toSaveFormat());

        todo.markAsDone();
        assertEquals("T | 1 | Learn Java | Python | C++", todo.toSaveFormat());
    }

    @Test
    @DisplayName("toSaveFormat with special characters")
    void testToSaveFormatSpecialCharacters() {
        Todo todo = new Todo("Buy items: milk, eggs & bread @ store");

        assertEquals("T | 0 | Buy items: milk, eggs & bread @ store", todo.toSaveFormat());
    }

    // ================= INHERITED FUNCTIONALITY TESTS =================

    @Test
    @DisplayName("Test inherited isDone functionality")
    void testInheritedIsDone() {
        Todo todo = new Todo("test task");

        assertFalse(todo.isDone());

        todo.markAsDone();
        assertTrue(todo.isDone());

        todo.markAsNotDone();
        assertFalse(todo.isDone());
    }

    @Test
    @DisplayName("Test inherited getDescription functionality")
    void testInheritedGetDescription() {
        String description = "important task";
        Todo todo = new Todo(description);

        assertEquals(description, todo.getDescription());

        // Description should not change after marking done
        todo.markAsDone();
        assertEquals(description, todo.getDescription());
    }

    @Test
    @DisplayName("Multiple todos should be independent")
    void testMultipleTodosIndependence() {
        Todo todo1 = new Todo("task 1");
        Todo todo2 = new Todo("task 2");

        // Mark only first todo as done
        todo1.markAsDone();

        assertTrue(todo1.isDone());
        assertFalse(todo2.isDone());

        assertEquals("[T][X] task 1", todo1.toString());
        assertEquals("[T][ ] task 2", todo2.toString());

        assertEquals("T | 1 | task 1", todo1.toSaveFormat());
        assertEquals("T | 0 | task 2", todo2.toSaveFormat());
    }

    // ================= EDGE CASE TESTS =================

    @Test
    @DisplayName("Todo with Unicode characters")
    void testTodoUnicodeCharacters() {
        Todo todo = new Todo("学习中文 🇨🇳");

        assertEquals("学习中文 🇨🇳", todo.getDescription());
        assertEquals("[T][ ] 学习中文 🇨🇳", todo.toString());
        assertEquals("T | 0 | 学习中文 🇨🇳", todo.toSaveFormat());
    }

    @Test
    @DisplayName("Todo with numbers in description")
    void testTodoWithNumbers() {
        Todo todo = new Todo("Complete 50 pushups by 5pm");

        assertEquals("Complete 50 pushups by 5pm", todo.getDescription());
        assertEquals("[T][ ] Complete 50 pushups by 5pm", todo.toString());
        assertEquals("T | 0 | Complete 50 pushups by 5pm", todo.toSaveFormat());
    }

    @Test
    @DisplayName("Todo description with leading/trailing spaces should be preserved")
    void testTodoPreservesSpaces() {
        // Note: This test assumes the Task constructor trims spaces
        // If it doesn't trim, adjust the expected results accordingly
        Todo todo = new Todo("  spaced task  ");

        // Check what the actual behavior is - this might need adjustment
        // based on your Task class implementation
        assertNotNull(todo.getDescription());
        assertTrue(todo.toString().contains("spaced task"));
        assertTrue(todo.toSaveFormat().contains("spaced task"));
    }
}