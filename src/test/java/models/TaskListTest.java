package models;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FakeTask extends Task {
    public FakeTask(int index) {
        super(Integer.toString(index));
    }

    @Override
    public String toString() {
        return "Fake task " + description;
    }
}

public class TaskListTest {
    ArrayList<Task> tasks;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>(List.of(new FakeTask(1), new FakeTask(2), new FakeTask(3)));
    }

    @Test
    public void initializationTest() {
        assertEquals(tasks.size(), 3);
        for(int i = 0; i < 3; i++) {
            assertEquals(tasks.get(i).toString(), String.format("Fake task %d", i+1));
        }   
    }

    @Test
    public void getTest_invalidIndex_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.get(4));
    }

    @Test
    public void addTest() {
        tasks.add(new FakeTask(5));

        assertEquals(tasks.size(), 4);
        assertEquals(tasks.get(3).toString(), "Fake task 5");
    }

    @Test
    public void removeTest() {
        tasks.remove(0);
        assertEquals(tasks.size(), 2);
        assertEquals(tasks.get(0).toString(), "Fake task 2");
    }
}
