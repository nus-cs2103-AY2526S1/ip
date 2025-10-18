package uy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserMissingDescriptionTest {
    @Test
    public void testTodoMissingDescriptionThrows() throws Exception {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage("data");

        assertThrows(MissingInputError.class, () -> {
            parser.parseAndRun("todo", tasks, ui, storage);
        });
    }
}
