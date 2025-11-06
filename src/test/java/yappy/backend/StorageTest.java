package yappy.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yappy.exception.YappyException;
import yappy.task.Deadline;
import yappy.task.Event;
import yappy.task.Task;
import yappy.task.ToDo;



public class StorageTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private File tempFile;
    private Storage storage;

    /**
     * Helper method to test loadTask method
     * @throws IOException If there is an error creating the temp file
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file
        tempFile = File.createTempFile("Yappy", ".txt");
        tempFile.deleteOnExit();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("T|false|Buy milk\n");
            writer.write("D|true|Submit report|2025-08-31 23:59\n");
            writer.write("E|false|Meeting|2025-08-30 10:00|2025-08-30 11:00\n");
        }

        try {
            storage = new Storage(tempFile.getAbsolutePath());
        } catch (YappyException e) {
            throw new IOException("Failed to initialize Storage: " + e.getMessage());
        }
    }

    @Test
    public void testLoadTask_fileExists_success() throws YappyException {
        ArrayList<Task> tasks = storage.loadTask();

        // Verify size
        assertEquals(3, tasks.size());

        // Verify first task is a ToDo
        Task t1 = tasks.get(0);
        assertInstanceOf(ToDo.class, t1);
        assertEquals("Buy milk", t1.getDescription());
        assertFalse(t1.isCompleted());

        // Verify second task is a Deadline
        Task t2 = tasks.get(1);
        assertInstanceOf(Deadline.class, t2);
        assertEquals("Submit report", t2.getDescription());
        assertTrue(t2.isCompleted());
        assertEquals(LocalDateTime.parse("2025-08-31 23:59", FORMATTER), ((Deadline) t2).getByDate());

        // Verify third task is an Event
        Task t3 = tasks.get(2);
        assertInstanceOf(Event.class, t3);
        assertEquals("Meeting", t3.getDescription());
        assertFalse(t3.isCompleted());
        assertEquals(LocalDateTime.parse("2025-08-30 10:00", FORMATTER), ((Event) t3).getFromDate());
        assertEquals(LocalDateTime.parse("2025-08-30 11:00", FORMATTER), ((Event) t3).getToDate());
    }

    @Test
    public void testSave_emptyList_createsEmptyFile() throws YappyException {
        ArrayList<Task> emptyTasks = new ArrayList<>();
        storage.save(emptyTasks);

        ArrayList<Task> loaded = storage.loadTask();
        assertEquals(0, loaded.size());
    }

    @Test
    public void testLoadTask_incorrectFormat_throwsException() throws IOException, YappyException {
        // Write incorrect format to temp file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("X|false|Invalid\n");
        }
        Storage badStorage = new Storage(tempFile.getAbsolutePath());
        YappyException exception = assertThrows(YappyException.class, badStorage::loadTask);
        assertEquals("Incorrect format in Yappy.txt file!", exception.getMessage());
    }
}
