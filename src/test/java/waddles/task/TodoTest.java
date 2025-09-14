package waddles.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void fromSerializedString_valid_success() {
        Todo actualTodo = Todo.fromSerializedString("T | 0 | return book | ");
        Todo expectedTodo = new Todo("return book", false, new Tags());
        assertEquals(actualTodo.toString(), expectedTodo.toString());
    }

    @Test
    public void toSerializedString_valid_success() {
        Todo todo = new Todo("return book", true, new Tags());
        String serialized = todo.toSerializedString();
        assertEquals("T | 1 | return book | ", serialized);
    }

    @Test
    public void fromSerializedString_withTags_success() {
        Todo actualTodo = Todo.fromSerializedString("T | 0 | return book | #a,#b");
        Todo expectedTodo = new Todo("return book", false, new Tags(new ArrayList<>(Arrays.asList("#a", "#b"))));
        assertEquals(actualTodo.toString(), expectedTodo.toString());
    }

    @Test
    public void toSerializedString_withTags_success() {
        Todo todo = new Todo("return book", true, new Tags(new ArrayList<>(Arrays.asList("#a", "#c"))));
        String serialized = todo.toSerializedString();
        assertEquals("T | 1 | return book | #a,#c", serialized);
    }
}
