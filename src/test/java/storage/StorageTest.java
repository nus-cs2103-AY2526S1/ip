package storage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.Test;

import task.TaskList;

public class StorageTest {
    private String normalFilePath = "test_data/test_frenny_normal.txt"; // Use a test file path
    private Storage normalStorage = new Storage(normalFilePath);

    @Test
    public void correctFileFormat_success() {
        // Redirect standard output to capture the print statements if needed
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out; // Save the original System.out
        System.setOut(ps);

        // Implement test for correct file format
        TaskList taskList = new TaskList();
        normalStorage.readFile(taskList);

        // Assert no string printed to console
        assert baos.toString().isEmpty();

        // Restore original System.out
        System.out.flush();
        System.setOut(oldOut); // Restore original System.out
    }

    @Test
    public void corruptedFileFormat_handleCorruptedFile() {
        // Implement test for corrupted file format
        Path corruptedFilePath = Paths.get("test_data/test_frenny_corrupted.txt"); // Use a corrupted test file path

        // Redirect standard output to capture the print statements if needed
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out; // Save the original System.out
        System.setOut(ps);

        // Make sure the corrupted file exists
        if (!Files.exists(corruptedFilePath)) {
            System.out.println("Corrupted file does not exist: " + corruptedFilePath.toString());
        }
        Path tempFilePath = Paths.get("test_data/temp_corrupted.txt");

        // Copy the corrupted content to the test file before running this test
        try {
            Files.copy(corruptedFilePath, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Error setting up corrupted file for test: " + e.getMessage());
        }
        Storage corruptedStorage = new Storage(tempFilePath.toString());
        TaskList taskList = new TaskList();
        corruptedStorage.readFile(taskList);
        assert baos.toString().contains("The history file was corrupted and has been reinitialized.");

        // Restore original System.out
        System.out.flush();
        System.setOut(oldOut); // Restore original System.out

        // Clean up the temporary file after the test
        try {
            Files.deleteIfExists(tempFilePath);
        } catch (Exception e) {
            System.out.println("Error cleaning up temporary file after test: " + e.getMessage());
        }
    }

    @Test
    public void readFile_nullTaskList_exceptionThrown() {
        // Implement test for null TaskList
        try {
            normalStorage.readFile(null);
        } catch (AssertionError e) {
            assert e.getMessage().equals("TaskList cannot be null");
        }
    }

    @Test
    public void writeFile_nullTaskList_exceptionThrown() {
        // Implement test for null TaskList
        try {
            normalStorage.writeFile(null);
        } catch (AssertionError e) {
            assert e.getMessage().equals("TaskList cannot be null");
        }
    }
}
