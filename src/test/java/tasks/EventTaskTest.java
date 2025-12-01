package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class EventTaskTest {
    private TaskList list = new TaskList("./data/eventTestStorage");
    @Test
    public void eventFormatStringTest() {
        TaskInformation info = new TaskInformation("event read book /from 02-06-2025 1800 /to 12-06-2025 1800",
                "event");
        EventTask event = new EventTask(info);
        String expected = "[E][ ] read book (from: 2 Jun 2025 18:00 to: 12 Jun 2025 18:00)";
        String actual = event.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void eventFormatStorageTest() {
        TaskInformation info = new TaskInformation("event read book /from 02-06-2025 1800 /to 12-06-2025 1800",
                "event");
        EventTask event = new EventTask(info);
        String expected = "E |   | read book | 02-06-2025 1800 | 12-06-2025 1800";
        String actual = event.toSave();
        assertEquals(expected, actual);
    }

    @Test
    public void invalidAddInputTest() {
        String actual = list.addTask("event", "event");
        String expected = "Aiyo! Wrong recipe! No sweet is made "
                + "\nFollow the following recipes: "
                + "\n1. To add sweets (Tasks): "
                + "\n     - 'todo <task>'"
                + "\n     - 'deadline <task> /by <dd-mm-yyyy HHmm>'"
                + "\n     - 'events <task> /from <dd-mm-yyyy HHmm> /to <dd-mm-yyyy HHmm>'"
                + "\n2. To edit sweets: "
                + "\n     - 'mark <task number>' "
                + "\n     - 'unmark <task number>'."
                + "\n     - 'delete <task number>' "
                + "\n     - 'edit <task number> /[same as adding task but without 'todo', 'deadline' or 'event']'"
                + "\n3. To view/find sweets:"
                + "\n     - 'list'"
                + "\n     - 'find <keyword>'"
                + "\n4. To close:"
                + "\n   - 'bye'";
        assertEquals(actual, expected);
    }

    @Test
    public void addNoStartTest() {
        String actual = list.addTask("event task /from /to 18-05-2025 1800", "event");
        String expected = "Oh no! Candy doesn't know when to start \n"
                + "Please input a start time by '/from (start)";
        assertEquals(actual, expected);
    }

    @Test
    public void addNoEndTest() {
        String actual = list.addTask("event task /from 18-05-2025 1800/to ", "event");
        String expected = "Oh no! Candy doesn't know when to stop!\n"
                + "Please specify an end time by inputting"
                + " '/by (end)' for deadline task or '/to (end)' for event task";
        assertEquals(actual, expected);
    }

    @Test
    public void addNoTaskTest() {
        String actual = list.addTask("event /from 18-05-2025 1800/to 28-05-2025 1800", "event");
        String expected = "Yikes! Candy don't know what to make.\n"
                + "Input your task!";
        assertEquals(actual, expected);
    }

    @Test
    public void invalidFindInputTest() {
        String actual = list.findTask("find");
        String expected = "Candy is lost. What sweet 🍬 are you searching for?";
        assertEquals(expected,actual);
    }

    @Test
    public void invalidMarkTestOne() {
        String actual = list.doMark("mark", true);
        String expected = "Candy can't tell which sweet. \n"
                + "Give candy a number after your command";
        assertEquals(expected, actual);
    }

    @Test
    public void invalidMarkTestTwo() {
        String actual = list.doMark("mark a", true);
        String expected = "Candy can't tell which sweet. \n"
                + "Give candy a number after your command";
        assertEquals(expected, actual);
    }

    @Test
    public void invalidMarkTestThree() {
        String actual = list.doMark("mark -1", true);
        String expected = "Oh no! Candy don't have that sweet \uD83D\uDE22"
                + "\nPlease give candy a valid number";
        assertEquals(expected, actual);
    }

    @Test
    public void invalidUpdateTaskTestOne() {
        String actual =  list.updateTask("edit /test /from 18-01-2020 1800 /to 15-02-2020 1800");
        String expected = "Candy can't tell which sweet. \n"
                + "Give candy a number after your command";
        assertEquals(expected, actual);
    }

    @Test
    public void invalidUpdateTaskTestTwo() {
        String actual =  list.updateTask("edit 1 /test /from 18-01-2020 1800 /to 15-02-2020 1800");
        String expected = "Oh no! Candy don't have that sweet \uD83D\uDE22"
                + "\nPlease give candy a valid number";
        assertEquals(expected, actual);
    }
}
