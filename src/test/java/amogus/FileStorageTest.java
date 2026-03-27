package amogus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FileStorageTest {

    @Test
    public void parseInvalidDeadline_exceptionThrow() {
        try {
            FileStorage fs = new FileStorage("./data/Tasks.TaskList.txt");
            fs.parseTask("D | 0 | return book");
        } catch (Exception e) {
            assertEquals("Invalid deadline format", e.getMessage());
        }
    }
}
