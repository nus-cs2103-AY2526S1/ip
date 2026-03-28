package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class TaskTest {

    private Task task = new TodoTask("Test");

    @Test
    public void taskSave_nameWithDelimiter_canSave() {
        Task task = new TodoTask("hello##this#is a long compl#12309?>cated name");
        assertEquals("45#hello##this#is a long compl#12309?>cated name#false#T", task.save());
    }

    @Test
    public void mark_taskMarked_taskIsCompleted() {
        task.mark();
        assertTrue(task.getIsCompleted());
    }

    @Test
    public void unmark_taskUnmarked_taskIsNotCompleted() {
        task.mark();
        task.unmark();
        assertFalse(task.getIsCompleted());
    }

    @Test
    public void getName_taskNameProvided_taskNameIsReturned() {
        assertEquals("Test", task.getName());
    }

    @Test
    public void save_taskSaved_taskDataIsCorrect() {
        String savedData = task.save();
        assertTrue(savedData.contains("Test"));
        assertTrue(savedData.contains("false"));
    }

    @Test
    public void toString_taskNotCompleted_todoTaskStringIsCorrect() {
        String expectedString = "[T][ ] Test";
        assertEquals(expectedString, task.toString());
    }

    @Test
    public void toString_taskCompleted_todoTaskStringIsCorrect() {
        task.mark();
        String expectedString = "[T][x] Test";
        assertEquals(expectedString, task.toString());
    }

    @Test
    public void isUpcoming_taskWithNoDate_taskIsNotUpcoming() {
        ChronoLocalDate today = LocalDate.now();
        assertFalse(task.isUpcoming(today));
    }
}
