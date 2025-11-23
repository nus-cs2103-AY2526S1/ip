package conversal.storage;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import conversal.task.Deadline;
import conversal.task.DoWithinPeriodTask;
import conversal.task.Event;
import conversal.task.Task;
import conversal.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link Storage}.
 *
 * Ensures save() and load() handle different cases:
 * - Missing files
 * - Task of all types can be saved and loaded
 * - Old content can be overwritten
 * - Done/Undone status is preserved during save and load operations
 */
class StorageTest {

    // JUnit provides us a temporary directory for safe file I/O.
    @TempDir
    File tmpDir;

    /** Create a Storage that reads/writes a file inside tmpDir. */
    private Storage newStorage(String name) {
        return new Storage(new File(tmpDir, name).getPath());
    }

    /** If file is missing, load() should return empty list */
    @Test
    void load_missingFile_returnsEmpty() {
        Storage s = newStorage("missing.txt");
        ArrayList<Task> loaded = s.load();
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    /** Save and load all task types. */
    @Test
    void saveAndLoadForallTypes() {
        Storage s = newStorage("tasks.txt");

        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("read"));
        list.add(new Deadline("submit", LocalDate.of(2025, 9, 30)));
        list.add(new Event("meet", "Mon 2pm", "4pm"));
        list.add(new DoWithinPeriodTask("finish", "2 days"));

        list.get(1).markAsComplete();
        s.save(list);
        ArrayList<Task> loaded = s.load();

        assertEquals(4, loaded.size());

        // To-do task
        assertTrue(loaded.get(0) instanceof Todo);
        assertEquals("read", loaded.get(0).getDescription());
        assertFalse(loaded.get(0).isDone());

        // Deadline task
        assertTrue(loaded.get(1) instanceof Deadline);
        assertEquals("submit", loaded.get(1).getDescription());
        assertTrue(loaded.get(1).isDone());
        assertEquals(LocalDate.of(2025, 9, 30), ((Deadline) loaded.get(1)).getDueDate());

        // Event task
        assertTrue(loaded.get(2) instanceof Event);
        assertEquals("meet", loaded.get(2).getDescription());
        assertEquals("Mon 2pm-4pm", ((Event) loaded.get(2)).getSchedule());

        // DoWithin task
        assertTrue(loaded.get(3) instanceof DoWithinPeriodTask);
        assertEquals("finish", loaded.get(3).getDescription());
        assertEquals("2 days", ((DoWithinPeriodTask) loaded.get(3)).getPeriod());
    }

    /** Old file contents can be rewritten. */
    @Test
    void oldFileContentRewrite() {
        Storage s = newStorage("overwrite.txt");

        ArrayList<Task> first = new ArrayList<>();
        first.add(new Todo("Task A"));
        s.save(first);

        ArrayList<Task> second = new ArrayList<>();
        second.add(new Todo("Task B"));
        second.add(new Todo("Task C"));
        s.save(second);

        ArrayList<Task> loaded = s.load();
        assertEquals(2, loaded.size());
        assertEquals("Task B", loaded.get(0).getDescription());
        assertEquals("Task C", loaded.get(1).getDescription());
    }

    /** Complete/Incomplete status should survive save/load. */
    @Test
    void saveAndLoadPreservedForAllTypes() {
        Storage s = newStorage("done-flags.txt");

        ArrayList<Task> list = new ArrayList<>();

        // To-do task
        Todo t = new Todo("t");
        t.markAsComplete();

        // Deadline task
        Deadline d = new Deadline("d", LocalDate.of(2030, 1, 1));
        d.markAsComplete();

        // Event task
        Event e = new Event("e", "1pm", "2pm");

        // DoWithin task
        DoWithinPeriodTask w = new DoWithinPeriodTask("w", "3h");
        w.markAsComplete();

        list.add(t);
        list.add(d);
        list.add(e);
        list.add(w);

        s.save(list);
        ArrayList<Task> loaded = s.load();

        assertEquals(4, loaded.size());
        assertTrue(loaded.get(0).isDone());
        assertTrue(loaded.get(1).isDone());
        assertFalse(loaded.get(2).isDone());
        assertTrue(loaded.get(3).isDone());
    }
}
