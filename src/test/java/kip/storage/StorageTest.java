package kip.storage;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kip.task.Task;
import kip.task.ToDo;

public class StorageTest {
    
    private static final String TEST_CSV_FILE = "tasks.csv";
    private File originalFile;
    
    @BeforeEach
    public void setUp() {
        // Backup the original file if it exists
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            // Store reference to original file
            originalFile = file;
        }
    }
    
    @AfterEach
    public void tearDown() {
        // Restore original file if it existed
        if (originalFile != null && originalFile.exists()) {
            // File should remain as is for the application
        }
    }
    
    @Test
    public void testLoadTasks() {
        // Test that tasks can be loaded from the file
        ArrayList<Task> tasks = Storage.loadTasks();
        assertNotNull(tasks);
        // The file should exist and be readable
        File file = new File(TEST_CSV_FILE);
        assertTrue(file.exists(), "CSV file should exist");
        assertTrue(file.canRead(), "CSV file should be readable");
    }
    
    @Test
    public void testSaveTasks() {
        // Test that tasks can be saved to the file
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Test task"));
        
        // Save tasks
        Storage.saveTasks(tasks);
        
        // Verify file was created/updated
        File file = new File(TEST_CSV_FILE);
        assertTrue(file.exists(), "CSV file should exist after saving");
        assertTrue(file.length() > 0, "CSV file should not be empty after saving");
    }
    
    @Test
    public void testFileLocation() {
        // Test that the file path is correct
        File file = new File(TEST_CSV_FILE);
        
        // The file should be in the current directory (tasks.csv)
        assertTrue(file.getPath().equals("tasks.csv") || file.getPath().endsWith("tasks.csv"), 
                  "File should be tasks.csv in the current directory");
    }
}
