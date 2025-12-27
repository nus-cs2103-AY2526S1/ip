package tension.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import tension.task.Task;

public class StorageTest {

    private String path = "src/test/sample.txt";
    private String originalContent = "T|true|1\n"
            + "E|false|meeting|1pm|2pm\n"
            + "D|false|meeting|2019-10-15\n";

    @Test
    public void dummyWriteTest() throws Exception {
        Files.deleteIfExists(Path.of(path));
        Files.write(Path.of(path), originalContent.getBytes());
        Storage storage = new Storage(path);
        Task task = Task.makeTask("todo sample command");
        storage.writeFile(task);
        String fileContent = Files.readString(Path.of(path));
        assertEquals(originalContent + "T|false|sample command|[]\n", fileContent);
    }

    @Test
    public void dummyDeleteTest() throws Exception {
        Files.deleteIfExists(Path.of(path));
        Files.write(Path.of(path), originalContent.getBytes());
        Storage storage = new Storage(path);
        storage.deleteLine(2);
        String fileContent = Files.readString(Path.of(path));
        assertEquals("T|true|1\n"
                + "E|false|meeting|1pm|2pm\n", fileContent);
    }
}

