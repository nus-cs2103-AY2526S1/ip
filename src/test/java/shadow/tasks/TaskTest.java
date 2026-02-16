package shadow.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class TaskTest {

    @Test
    void constructor_validName_setsNameAndUnmarked() {
        Task task = new Task("Read a book");
        assertEquals("[ ] Read a book", task.toString());
    }

    @Test
    void mark_unmarkedTask_setsMarkedTrueAndUpdatesToString() {
        Task task = new Task("Submit report");
        task.mark();
        assertEquals("[X] Submit report", task.toString());
    }

    @Test
    void mark_alreadyMarkedTask_keepsMarkedTrue() {
        Task task = new Task("Complete homework");
        task.mark(); // First mark
        task.mark(); // Second mark
        assertEquals("[X] Complete homework", task.toString());
    }

    @Test
    void unmark_markedTask_setsMarkedFalseAndUpdatesToString() {
        Task task = new Task("Clean room");
        task.mark();
        task.unmark();
        assertEquals("[ ] Clean room", task.toString());
    }

    @Test
    void unmark_unmarkedTask_keepsMarkedFalse() {
        Task task = new Task("Wash dishes");
        task.unmark(); // Call without marking first
        assertEquals("[ ] Wash dishes", task.toString());
    }

    @Test
    void toString_newTask_hasEmptyCheckboxAndCorrectName() {
        Task task = new Task("Feed cat");
        assertEquals("[ ] Feed cat", task.toString());
    }

    @Test
    void toString_markedTask_hasXCheckboxAndCorrectName() {
        Task task = new Task("Water plants");
        task.mark();
        assertEquals("[X] Water plants", task.toString());
    }

    @Test
    void toString_unmarkedAfterMark_hasEmptyCheckbox() {
        Task task = new Task("Do laundry");
        task.mark();
        task.unmark();
        assertEquals("[ ] Do laundry", task.toString());
    }

    @Test
    void toString_nameWithSpaces_displaysProperly() {
        Task task = new Task("   Task with spaces   ");
        assertEquals("[ ]    Task with spaces   ", task.toString());
    }

    @Test
    void mark_thenUnmarkAndMarkAgain_hasCorrectState() {
        Task task = new Task("Cycle state");
        task.mark();
        assertEquals("[X] Cycle state", task.toString());

        task.unmark();
        assertEquals("[ ] Cycle state", task.toString());

        task.mark();
        assertEquals("[X] Cycle state", task.toString());
    }

    @Test
    void toString_emptyName_displaysBracketsOnly() {
        Task task = new Task("");
        assertEquals("[ ] ", task.toString());
    }
}
