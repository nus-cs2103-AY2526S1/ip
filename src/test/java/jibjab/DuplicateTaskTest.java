package jibjab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for duplicate detection through JibJab.getResponse command handling.
 */
public class DuplicateTaskTest {

    @Test
    public void testPreventToDoDuplicate() {
        JibJab bot = new JibJab("test");
        String first = bot.getResponse("todo read book");

        String second = bot.getResponse("todo read book");
        assertEquals("This task already exists in your list!", second);

    }

    @Test
    public void testPreventDeadlineDuplicate() {
        JibJab bot = new JibJab("test");
        String first = bot.getResponse("deadline submit report /by Jan 01 2077 18:00");

        String dup = bot.getResponse("deadline submit report /by Jan 01 2077 18:00");
        assertEquals("This task already exists in your list!", dup);
    }

    @Test
    public void testPreventEventDuplicate() {
        JibJab bot = new JibJab("test");
        String first = bot.getResponse("event party /from Jan 01 2077 18:00 /to Feb 01 2077 19:00");

        String dup = bot.getResponse("event party /from Jan 01 2077 18:00 /to Feb 01 2077 19:00");
        assertEquals("This task already exists in your list!", dup);
    }
}
