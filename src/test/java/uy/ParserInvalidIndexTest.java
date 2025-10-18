package uy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserInvalidIndexTest {
    @Test
    public void testMarkInvalidIndexThrows() throws Exception {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage("data");

        // no tasks in the list, index 1 should be out of range
        assertThrows(IndexOutOfBoundsException.class, () -> {
            parser.parseAndRun("mark 1", tasks, ui, storage);
        });
    }
}
