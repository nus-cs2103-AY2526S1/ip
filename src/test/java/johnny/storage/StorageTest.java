package johnny.storage;

import org.junit.jupiter.api.Test;

import johnny.exception.JohnnyException;
import johnny.tasklist.TaskList;
import johnny.tasks.DeadlineTask;
import johnny.tasks.EventTask;
import johnny.tasks.Task;
import johnny.tasks.TodoTask;
import johnny.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class StorageTest {
    private Path tempDir;
    private Ui ui = new Ui(); // assume Ui has no-arg constructor

    @BeforeEach
    void setUp() throws IOException {
        tempDir = Files.createTempDirectory("storagetest");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(tempDir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    void testLoad_createsFileIfNotExists() {
        Path filePath = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(filePath.toString());

        try {
            ArrayList<Task> tasks = storage.load(ui);
            assertTrue(tasks.isEmpty(), "File not present → should return empty list");
            assertTrue(Files.exists(filePath), "File should be created automatically");
        } catch (JohnnyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testLoad_withDeadlineTask() throws IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        Files.write(filePath, Arrays.asList("D|0|Submit report|25/12/2025"));

        Storage storage = new Storage(filePath.toString());

        try {
            ArrayList<Task> tasks = storage.load(ui);
            assertEquals(1, tasks.size());
            Task t = tasks.get(0);
            assertTrue(t instanceof DeadlineTask);
            assertEquals("Submit report", t.getName());
            assertFalse(t.isDone());
            assertEquals(LocalDate.of(2025, 12, 25), ((DeadlineTask) t).getDeadline());
        } catch (JohnnyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testLoad_withEventTask() throws IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        Files.write(filePath, Arrays.asList("E|0|Concert|25/12/2025 18:00|20:00"));

        Storage storage = new Storage(filePath.toString());

        try {
            ArrayList<Task> tasks = storage.load(ui);
            assertEquals(1, tasks.size());
            Task t = tasks.get(0);
            assertTrue(t instanceof EventTask);
            EventTask e = (EventTask) t;
            assertEquals("Concert", e.getName());
            assertEquals(LocalDateTime.of(2025, 12, 25, 18, 0), e.getStart());
            assertEquals(LocalTime.of(20, 0), e.getEnd());
        } catch (JohnnyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testSaveAndReload_tasksPersist() throws IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(filePath.toString());

        TaskList taskList = new TaskList();
        taskList.addTask(new TodoTask("Laundry", false));
        taskList.addTask(new DeadlineTask("Project", false, LocalDate.of(2025, 1, 1)));
        taskList.addTask(new EventTask("Party", true,
                LocalDateTime.of(2025, 2, 2, 18, 0),
                LocalTime.of(23, 0)));

        storage.save(taskList);

        try {
            ArrayList<Task> reloaded = storage.load(ui);
            assertEquals(3, reloaded.size());
            assertTrue(reloaded.get(0) instanceof TodoTask);
            assertTrue(reloaded.get(1) instanceof DeadlineTask);
            assertTrue(reloaded.get(2) instanceof EventTask);
        } catch (JohnnyException e) {
            System.out.println(e.getMessage());
        }
    }
}
