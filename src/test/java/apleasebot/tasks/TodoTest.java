package apleasebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private final Todo taskOne = new Todo("Test Todo class", false);
    private final Todo taskDone = new Todo("Complete", true);

    @Test
    public void toString_newTask_success() {
        assertEquals("[T] [ ] Test Todo class", taskOne.toString());
    }

    @Test
    public void translate_storeTask_success() {
        assertEquals("T,1,Complete", taskDone.translateTaskToText());
    }
}
