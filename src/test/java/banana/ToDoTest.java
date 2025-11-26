package banana;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import banana.task.ToDo;

public class ToDoTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest() {
        assertEquals(4, 4);
    }

    @Test
    public void testToStringWithEmptyDescription() {
        // Test with an empty description
        ToDo toDo = new ToDo("");
        String expectedOutput = "[T][ ] ";
        assertEquals(expectedOutput, toDo.toString(), "The toString should handle empty description");
    }
}
