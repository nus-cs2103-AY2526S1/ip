package watermelon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import watermelon.task.Task;
import watermelon.task.Todo;

// Used Claude to generate the following test cases for Storage class
public class StorageTest {

    @Test
    public void getFilePath_returnCorrectPath() {
        Storage storage = new Storage("test.txt");

        assertEquals("test.txt", storage.getFilePath());
    }

    @Test
    public void fileExists_nonExistentFile_returnsFalse() {
        Storage storage = new Storage("nonexistent.txt");

        assertFalse(storage.fileExists());
    }

    @Test
    public void fileExists_existingFile_returnsTrue() throws IOException {
        File testFile = new File("existing.txt");
        testFile.createNewFile();
        Storage storage = new Storage("existing.txt");

        assertTrue(storage.fileExists());

        testFile.delete(); // cleanup
    }

    @Test
    public void loadTasks_nonExistentFile_returnsEmptyList() {
        Storage storage = new Storage("nonexistent.txt");

        ArrayList<Task> tasks = storage.loadTasks();

        assertEquals(0, tasks.size());
    }

    @Test
    public void loadTasks_emptyFile_returnsEmptyList() throws IOException {
        File testFile = new File("empty.txt");
        testFile.createNewFile();
        Storage storage = new Storage("empty.txt");

        ArrayList<Task> tasks = storage.loadTasks();

        assertEquals(0, tasks.size());
        testFile.delete(); // cleanup
    }

    @Test
    public void loadTasks_multipleTasks_allTasksLoaded() throws IOException {
        File testFile = new File("multiple_test.txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write("T | 0 | buy milk\n");
        writer.write("D | 1 | submit assignment | 11112025 2359\n");
        writer.write("E | 0 | team meeting | 12122025 1700 | 12122025 1900\n");
        writer.close();
        Storage storage = new Storage("multiple_test.txt");

        ArrayList<Task> tasks = storage.loadTasks();

        assertEquals(3, tasks.size());
        testFile.delete(); // cleanup
    }

    @Test
    public void loadTasks_invalidFormat_skipsBadLines() throws IOException {
        File testFile = new File("invalid_test.txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write("T | 0 | buy milk\n");
        writer.write("invalid line\n");
        writer.write("D | 1 | submit assignment | 11112025 2359\n");
        writer.close();
        Storage storage = new Storage("invalid_test.txt");

        ArrayList<Task> tasks = storage.loadTasks();

        assertEquals(2, tasks.size());
        testFile.delete(); // cleanup
    }

    @Test
    public void saveTasks_emptyList_fileCreated() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new Storage("save_empty.txt");

        storage.saveTasks(taskList);

        assertTrue(storage.fileExists());
        new File("save_empty.txt").delete(); // cleanup
    }

    @Test
    public void saveTasks_withTasks_tasksSaved() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("buy milk"));
        TaskList taskList = new TaskList(tasks);
        Storage storage = new Storage("save_tasks.txt");

        storage.saveTasks(taskList);

        assertTrue(storage.fileExists());
        ArrayList<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size());
        new File("save_tasks.txt").delete(); // cleanup
    }

    @Test
    public void saveTasks_withDirectory_directoryCreated() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new Storage("testdir/save_test.txt");

        storage.saveTasks(taskList);

        assertTrue(storage.fileExists());
        new File("testdir/save_test.txt").delete(); // cleanup
        new File("testdir").delete(); // cleanup directory
    }

    @Test
    public void loadTasks_unknownTaskType_skipsBadTask() throws IOException {
        File testFile = new File("unknown_type.txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write("T | 0 | buy milk\n");
        writer.write("X | 1 | unknown task\n");
        writer.write("D | 1 | submit assignment | 01122025 1400\n");
        writer.close();
        Storage storage = new Storage("unknown_type.txt");

        ArrayList<Task> tasks = storage.loadTasks();

        assertEquals(2, tasks.size());
        testFile.delete(); // cleanup
    }
}
