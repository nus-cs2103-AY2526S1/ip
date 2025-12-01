package tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

//learnt about ordering with chatGPT
@TestMethodOrder(OrderAnnotation.class)
public class DeadlineTaskTest {
    private TaskList list = new TaskList("./data/deadlineTestStorage.txt");

    @Test
    public void deadlineFormatStringTest() {
        TaskInformation info = new TaskInformation("deadline read book /by 02-06-2025 1800", "deadline");
        DeadlineTask deadline = new DeadlineTask(info);
        String expected = "[D][ ] read book (by: 2 Jun 2025 18:00)";
        String actual = deadline.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void deadlineFormatStorageTest() {
        TaskInformation info = new TaskInformation("deadline read book /by 02-06-2025 1800", "deadline");
        DeadlineTask deadline = new DeadlineTask(info);
        String expected = "D |   | read book | 02-06-2025 1800";
        String actual = deadline.toSave();
        assertEquals(expected, actual);
    }

    @Test
    @Order(1)
    public void addDeadlineTaskTest() {
        String actual = list.addTask("deadline read book /by 15-08-2025 1800", "deadline");
        String expected = "Candy successfully made this sweet: \n    "
                + "[D][ ] read book (by: 15 Aug 2025 18:00)"
                + "\nNow you have 1 sweet(s) in your list.";
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void markDeadlineTaskTest() {
        String actual = list.doMark("mark 1", true);
        String expected = "Candy marked this sweet edible ^-^ 😋\n"
                + "    [D][X] read book (by: 15 Aug 2025 18:00)";
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    public void updateDeadlineTaskTest() {
        String actual = list.updateTask("edit 1 /return book /by 20-08-2025 1800");
        String expected = "Candy successfully remade this sweet: \n"
                + "    [D][X] return book (by: 20 Aug 2025 18:00)";
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    public void unmarkDeadlineTaskTest() {
        String actual = list.doMark("mark 1", false);
        String expected = "Candy marked this sweet inedible ^-^ 😝\n"
                + "    [D][ ] return book (by: 20 Aug 2025 18:00)";
        assertEquals(expected, actual);
    }

    @Test
    @Order(5)
    public void findDeadlineTaskTest() {
        String actual = list.findTask("find book");
        String expected = "Candy found these sweets \uD83C\uDF6C !\n"
                + "1. [D][ ] return book (by: 20 Aug 2025 18:00)\n"
                + "You have 1 sweet(s) here!";
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    public void deleteDeadlineTaskTest() {
        String actual = list.delete("delete 1");
        String expected = "Candy ate this sweet 😋"
                + "\n    [D][ ] return book (by: 20 Aug 2025 18:00)"
                + "\nNow you have 0 sweet(s) left";
        assertEquals(expected, actual);
    }

    @Test
    @Order(7)
    public void failFindDeadlineTaskTest() {
        String actual = list.findTask("find book");
        String expected = "Candy didn't find matching sweets 😢";
        assertEquals(expected, actual);
    }
}
