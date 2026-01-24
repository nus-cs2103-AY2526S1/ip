package chathyt;

import chathyt.task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the {@link Todo} class.
 * Tests the string representation of a Todo task.
 */
public class TodoTest {

    /**
     * Tests that {@link Todo#toString()} correctly returns
     * the description of the Todo task.
     */
    @Test
    public void todo_toStringFormatsCorrectly() {
        Todo todoTest = new Todo("do 2103 ip");
        Assertions.assertEquals("[T][ ] do 2103 ip", todoTest.toString());
    }
}
