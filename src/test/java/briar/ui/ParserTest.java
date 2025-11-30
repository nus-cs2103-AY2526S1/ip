package briar.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import briar.exception.BriarException;

public class ParserTest {
    @Test
    public void parse_exit_success() {
        try {
            assertEquals("Exit", Parser.parse("bye").toString());
        } catch (BriarException e) {
            fail();
        }
    }

    @Test
    public void parse_list_success() {
        try {
            assertEquals("List", Parser.parse("list").toString());
        } catch (BriarException e) {
            fail();
        }
    }

    @Test
    public void parse_mark_success() {
        try {
            assertEquals("Mark task 1", Parser.parse("mark 2").toString());
        } catch (BriarException e) {
            fail();
        }
    }

    @Test
    public void parse_unmark_success() {
        try {
            assertEquals("Unmark task 1", Parser.parse("unmark 2").toString());
        } catch (BriarException e) {
            fail();
        }
    }

    @Test
    public void parse_addTodo_success() {
        try {
            assertEquals("Add: [T][ ] test", Parser.parse("todo test").toString());
        } catch (BriarException e) {
            fail();
        }
    }

    @Test
    public void parse_delete_success() {
        try {
            assertEquals("Delete task 1", Parser.parse("delete 2").toString());
        } catch (BriarException e) {
            fail();
        }
    }
}
