package jamal.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toDoOngoingTest() {
        assertEquals((new ToDo("test").isOngoing()),true);
    }

    @Test
    public void toDoOverdueTest() {
        assertEquals((new ToDo("test").isOverdue()),false);
    }

    @Test
    public void toDoUpcomingTest() {
        assertEquals((new ToDo("test").isUpcoming()),false);
    }
}