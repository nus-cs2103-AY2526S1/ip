package jaiden.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import jaiden.exception.JaidenException;
import jaiden.task.Deadline;
import jaiden.task.Event;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;

public class StorageTest {
    @Test
    void load_validFormat_success() throws Exception {
        new File("./data").mkdir();
        FileWriter testWriter = new FileWriter("data/test.txt");
        testWriter.write("T | 0 | test\nD | 0 | test | 2025-08-22\nE | 0 | test | 2025-08-22 | 2025-08-22\n"
                + "T | 1 | test\nD | 1 | test | 2025-08-22\nE | 1 | test | 2025-08-22 | 2025-08-22");
        testWriter.close();
        ArrayList<Task> test = new ArrayList<>();
        test.add(new Todo("test"));
        test.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        test.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        test.add(new Todo("test", true));
        test.add(new Deadline("test", true, LocalDate.parse("2025-08-22")));
        test.add(new Event("test", true, LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        assertEquals(test, new Storage("data/test.txt").load());
    }

    @Test
    void load_invalidFormat_exceptionThrown() {
        try {
            new File("./data").mkdir();
            FileWriter testWriter = new FileWriter("data/test.txt");
            testWriter.write(" | 0 | test");
            testWriter.close();
            new Storage("data/test.txt").load();
            fail();
        } catch (Exception e) {
            assertEquals(new JaidenException("The data file is corrupted (Content not in the expected format).")
                    .getMessage(), e.getMessage());
        }
    }

    @Test
    void saveTest_validFile_success() throws Exception {
        Storage storage = new Storage("data/test.txt");
        ArrayList<Task> test = new ArrayList<>();
        test.add(new Todo("test"));
        test.add(new Deadline("test", LocalDate.parse("2025-08-22")));
        test.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        test.add(new Todo("test", true));
        test.add(new Deadline("test", true, LocalDate.parse("2025-08-22")));
        test.add(new Event("test", true, LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
        storage.save(new TaskList(test));
        assertEquals("T | 0 | test\nD | 0 | test | 2025-08-22\nE | 0 | test | 2025-08-22 | 2025-08-22\n"
                + "T | 1 | test\nD | 1 | test | 2025-08-22\nE | 1 | test | 2025-08-22 | 2025-08-22",
                new Scanner(new File("data/test.txt")).useDelimiter("\\Z").next());
    }

    @Test
    void saveTest_invalidFile_exceptionThrown() {
        try {
            Storage storage = new Storage("");
            ArrayList<Task> test = new ArrayList<>();
            test.add(new Todo("test"));
            test.add(new Deadline("test", LocalDate.parse("2025-08-22")));
            test.add(new Event("test", LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
            test.add(new Todo("test", true));
            test.add(new Deadline("test", true, LocalDate.parse("2025-08-22")));
            test.add(new Event("test", true, LocalDate.parse("2025-08-22"), LocalDate.parse("2025-08-22")));
            storage.save(new TaskList(test));
            fail();
        } catch (Exception e) {
            assertEquals(new JaidenException("Save failed.").getMessage(), e.getMessage());
        }
    }
}
