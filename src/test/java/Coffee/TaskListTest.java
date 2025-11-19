package Coffee;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList tasks;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
    }

    // ---------- add / size / view ----------

    @Test
    void addTask_increasesSize_andAppearsInView() {
        assertEquals(0, tasks.size());

        ToDo t1 = new ToDo("read book");
        ToDo t2 = new ToDo("write notes");
        tasks.addTask(t1);
        tasks.addTask(t2);

        assertEquals(2, tasks.size());
        List<Task> snapshot = tasks.view();
        assertEquals(2, snapshot.size());
        assertTrue(snapshot.get(0).toString().contains("read book"));
        assertTrue(snapshot.get(1).toString().contains("write notes"));
    }

    @Test
    void view_isUnmodifiable() {
        tasks.addTask(new ToDo("alpha"));
        List<Task> snapshot = tasks.view();
        assertThrows(UnsupportedOperationException.class, () -> snapshot.add(new ToDo("beta")));
    }

    // ---------- delete ----------

    @Test
    void deleteTask_validIndex_removesAndReturnsCorrectTask() {
        tasks.addTask(new ToDo("a"));
        tasks.addTask(new ToDo("b"));
        tasks.addTask(new ToDo("c"));

        Task removed = tasks.deleteTask(2); // removes "b"
        assertTrue(removed.toString().contains("b"));
        assertEquals(2, tasks.size());

        // Remaining order should be a, c
        List<Task> left = tasks.view();
        assertTrue(left.get(0).toString().contains("a"));
        assertTrue(left.get(1).toString().contains("c"));
    }

    @Test
    void deleteTask_invalidIndex_throws() {
        tasks.addTask(new ToDo("only"));
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.deleteTask(0)); // 1-based
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.deleteTask(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.deleteTask(2));
    }

    // ---------- mark / unmark ----------

    @Test
    void markAsDone_thenNotDone_affectsStatus() {
        tasks.addTask(new ToDo("toggle me"));
        // initially not done
        assertTrue(tasks.view().get(0).toString().contains("[ ]"));
        assertEquals("T |   | toggle me", ((ToDo) tasks.view().get(0)).toFileString());

        tasks.markAsDone(1);
        assertTrue(tasks.view().get(0).toString().contains("[X]"));
        assertEquals("T | X | toggle me", ((ToDo) tasks.view().get(0)).toFileString());

        tasks.markAsNotDone(1);
        assertTrue(tasks.view().get(0).toString().contains("[ ]"));
        assertEquals("T |   | toggle me", ((ToDo) tasks.view().get(0)).toFileString());
    }

    // ---------- find ----------

    @Test
    void find_isCaseInsensitive_andMatchesSubstring() {
        tasks.addTask(new ToDo("Read Book"));
        tasks.addTask(new ToDo("submit report"));
        tasks.addTask(new ToDo("book tickets"));

        List<Task> r1 = tasks.find("book");
        assertEquals(2, r1.size());
        assertTrue(r1.get(0).toString().toLowerCase().contains("book"));
        assertTrue(r1.get(1).toString().toLowerCase().contains("book"));

        List<Task> r2 = tasks.find("REPORT");
        assertEquals(1, r2.size());
        assertTrue(r2.get(0).toString().toLowerCase().contains("report"));

        List<Task> r3 = tasks.find("xyz");
        assertTrue(r3.isEmpty());
    }

    // ---------- list() output ----------

    @Test
    void list_whenEmpty_printsHeaderAndNothingMessage() {
        String out = captureStdout(() -> tasks.list());
        assertTrue(out.contains("Here are the tasks in your list:"));
        assertTrue(out.contains("Nothing =)"));
    }

    @Test
    void list_whenNonEmpty_printsIndexedTasks() {
        tasks.addTask(new ToDo("first"));
        tasks.addTask(new ToDo("second"));

        String out = captureStdout(() -> tasks.list());
        assertTrue(out.contains("Here are the tasks in your list:"));
        assertTrue(out.contains("1. [T][ ] first"));
        assertTrue(out.contains("2. [T][ ] second"));
    }

    // ---------- helper to capture System.out ----------

    private String captureStdout(Runnable r) {
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(baos)) {
            System.setOut(ps);
            r.run();
        } finally {
            System.setOut(original);
        }
        return baos.toString();
    }
}
