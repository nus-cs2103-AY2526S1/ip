package aries.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void isAbstract() {
        int modifiers = Task.class.getModifiers();
        assertEquals(true, java.lang.reflect.Modifier.isAbstract(modifiers));
    }
}
