package task;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    // Minimal concrete Task for testing
    static class SimpleTask extends Task {
        private LocalDate date;

        public SimpleTask(String name, LocalDate date) {
            super(name);
            this.date = date;
        }

        public SimpleTask(String name, boolean isDone, LocalDate date) {
            super(name, isDone);
            this.date = date;
        }

        @Override public String toStorage() {
            return "S | " + (isDone ? "1" : "0") + " | " + taskName;
        }

        @Override
        public LocalDate getDate() {
            return date;
        }
    }

    private static final LocalDate DEFAULT_DATE = LocalDate.of(2025, 12, 31);

    @Test
    void newTaskList_startsEmpty() {
        TaskList tl = new TaskList();
        assertEquals(0, tl.size());
        assertTrue(tl.getTasks().isEmpty());
    }

    @Test
    void add_thenGet_preservesInsertionOrder() {
        TaskList tl = new TaskList();
        SimpleTask t1 = new SimpleTask("A", DEFAULT_DATE);
        SimpleTask t2 = new SimpleTask("B", DEFAULT_DATE);
        SimpleTask t3 = new SimpleTask("C", DEFAULT_DATE);

        tl.add(t1);
        tl.add(t2);
        tl.add(t3);

        assertEquals(3, tl.size());
        assertSame(t1, tl.get(0), "First added should be at index 0");
        assertSame(t2, tl.get(1), "Second added should be at index 1");
        assertSame(t3, tl.get(2), "Third added should be at index 2");
    }

    @Test
    void delete_middleItem_shiftsLaterItemsLeft() {
        TaskList tl = new TaskList();
        SimpleTask t1 = new SimpleTask("A", DEFAULT_DATE);
        SimpleTask t2 = new SimpleTask("B", DEFAULT_DATE);
        SimpleTask t3 = new SimpleTask("C", DEFAULT_DATE);
        tl.add(t1);
        tl.add(t2);
        tl.add(t3);

        tl.delete(1); // remove "B"

        assertEquals(2, tl.size());
        assertSame(t1, tl.get(0));
        assertSame(t3, tl.get(1), "Item after deleted one should shift left");
    }

    @Test
    void get_outOfBounds_throwsIndexOutOfBoundsException() {
        TaskList tl = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> tl.get(0));
        tl.add(new SimpleTask("A", DEFAULT_DATE));
        assertThrows(IndexOutOfBoundsException.class, () -> tl.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> tl.get(-1));
    }

    @Test
    void delete_outOfBounds_throwsIndexOutOfBoundsException() {
        TaskList tl = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> tl.delete(0));
        tl.add(new SimpleTask("A", DEFAULT_DATE));
        assertThrows(IndexOutOfBoundsException.class, () -> tl.delete(1));
        assertThrows(IndexOutOfBoundsException.class, () -> tl.delete(-1));
    }

    @Test
    void size_reflectsAddsAndDeletes() {
        TaskList tl = new TaskList();
        assertEquals(0, tl.size());
        tl.add(new SimpleTask("A", DEFAULT_DATE));
        assertEquals(1, tl.size());
        tl.add(new SimpleTask("B", DEFAULT_DATE));
        assertEquals(2, tl.size());
        tl.delete(0);
        assertEquals(1, tl.size());
        tl.delete(0);
        assertEquals(0, tl.size());
    }

    @Test
    void constructor_usesProvidedBackingList_andSharesMutations() {
        ArrayList<Task> backing = new ArrayList<>();
        SimpleTask t1 = new SimpleTask("A", DEFAULT_DATE);
        backing.add(t1);

        TaskList tl = new TaskList(backing);
        assertEquals(1, tl.size());
        assertSame(t1, tl.get(0));

        // mutate via TaskList
        SimpleTask t2 = new SimpleTask("B", DEFAULT_DATE);
        tl.add(t2);
        assertEquals(2, backing.size(), "Mutations via TaskList should reflect in backing list");

        // mutate via backing list
        SimpleTask t3 = new SimpleTask("C", DEFAULT_DATE);
        backing.add(t3);
        assertEquals(3, tl.size(), "Mutations via backing list should reflect in TaskList");
        assertSame(t3, tl.get(2));
    }

    @Test
    void getTasks_returnsLiveBackingList() {
        TaskList tl = new TaskList();
        ArrayList<Task> ref = tl.getTasks();
        assertSame(ref, tl.getTasks(), "Should return the same backing list reference");

        // Mutating the returned list should affect the TaskList
        ref.add(new SimpleTask("A", DEFAULT_DATE));
        assertEquals(1, tl.size());
        ref.remove(0);
        assertEquals(0, tl.size());
    }

    @Test
    void sortTasks_sortsTasksByDate() {
        TaskList tl = new TaskList();
        LocalDate date1 = LocalDate.of(2025, 12, 1);
        LocalDate date2 = LocalDate.of(2025, 11, 15);
        LocalDate date3 = LocalDate.of(2025, 12, 31);

        SimpleTask t1 = new SimpleTask("Task 1", date1);
        SimpleTask t2 = new SimpleTask("Task 2", date2);
        SimpleTask t3 = new SimpleTask("Task 3", date3);

        // Add tasks in random order
        tl.add(t3);  // Dec 31
        tl.add(t1);  // Dec 1
        tl.add(t2);  // Nov 15

        tl.sortTasks();

        // Should be sorted by date: Nov 15, Dec 1, Dec 31
        assertSame(t2, tl.get(0), "First task should be Nov 15");
        assertSame(t1, tl.get(1), "Second task should be Dec 1");
        assertSame(t3, tl.get(2), "Third task should be Dec 31");
    }

    @Test
    void sortTasks_maintainsOrderForSameDates() {
        TaskList tl = new TaskList();
        LocalDate sameDate = LocalDate.of(2025, 12, 1);

        SimpleTask t1 = new SimpleTask("A", sameDate);
        SimpleTask t2 = new SimpleTask("B", sameDate);

        tl.add(t1);
        tl.add(t2);

        tl.sortTasks();

        // Tasks with same date should maintain their original order
        assertSame(t1, tl.get(0), "First added task should remain first");
        assertSame(t2, tl.get(1), "Second added task should remain second");
    }

    @Test
    void sortTasks_emptyList_doesNotThrowException() {
        TaskList tl = new TaskList();
        assertDoesNotThrow(() -> tl.sortTasks(), "Sorting empty list should not throw exception");
    }
}
