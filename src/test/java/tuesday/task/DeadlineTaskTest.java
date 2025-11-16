package tuesday.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTaskTest {
    @Test
    public void toStringTest() {
        DeadlineTask deadline = new DeadlineTask("submit homework", "02-03-2025 1615");
        String result = deadline.toString();
        assertTrue(result.contains("submit homework"));
        assertTrue(result.contains("02 Mar 2025 - 4:15PM"));
    }

    @Test
    public void markDoneTest() {
        DeadlineTask deadline = new DeadlineTask("submit homework", "02-03-2025 1615");
        deadline.markDone();
        assertTrue(deadline.toString().contains("[X]"));
    }
}
