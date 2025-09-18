package chatowo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import chatowo.task.Deadline;
import chatowo.task.TaskList;
import chatowo.task.ToDo;

public class StorageTest {

    @TempDir
    Path tempDir;

    private File createTestFile(String content) throws IOException {
        File file = tempDir.resolve("tasks.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
        return file;
    }

    @Test
    public void readTaskList_emptyFile_returnsEmptyList() throws IOException {
        File testFile = createTestFile("");
        Storage storage = new Storage(testFile.getAbsolutePath());
        TaskList list = storage.readTaskList();
        assertEquals(0, list.size());
    }

    @Test
    public void removeFromTaskList_validIndex_removesTask() throws IOException {
        String content = "T | 0 | task1\nT | 0 | task2\n";
        File testFile = createTestFile(content);
        Storage storage = new Storage(testFile.getAbsolutePath());
        storage.removeFromTaskList(0);
        BufferedReader reader = new BufferedReader(new FileReader(testFile));
        assertEquals("T | 0 | task2", reader.readLine());
        assertNull(reader.readLine());
        reader.close();
    }
}
