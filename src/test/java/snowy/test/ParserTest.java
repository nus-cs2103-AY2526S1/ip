package snowy;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void toDoFormatCheck_success() throws SnowyException, IOException {
        Path temp = Files.createTempFile("snowy-test", ".txt");
        TaskList tasks = new TaskList();
        Storage storage = new Storage(temp.toString());

        String output = Parser.parse("todo return book").execute(tasks, storage);

        assertEquals("Got it. I've added this task: [T][ ] return book. Now you have 1 task in the list.", output);
    }

    @Test
    public void toDoFormatCheck_emptyDescription_throwsException() {
        Exception exception = assertThrows(SnowyException.class, () -> {
            TaskList tasks = new TaskList();
            Storage storage = new Storage("../data/snowy.txt");
            Parser.parse("todo").execute(tasks, storage);
        });

        assertEquals("OOPS! The description of a todo cannot be empty! " +
                "Use this format: todo <description>", exception.getMessage());
    }

    @Test
    public void toDoFormatCheck_emptyInput_throwsException() {
        Exception exception = assertThrows(SnowyException.class, () -> {
            TaskList tasks = new TaskList();
            Storage storage = new Storage("../data/snowy.txt");
            Parser.parse("").execute(tasks, storage);
        });

        assertEquals("Input command should not be empty", exception.getMessage());
    }

    @Test
    public void toDoFormatCheck_invalidCommand_throwsException() {
        Exception exception = assertThrows(SnowyException.class, () -> {
            TaskList tasks = new TaskList();
            Storage storage = new Storage("../data/snowy.txt");
            Parser.parse("hi").execute(tasks, storage);
        });

        assertEquals("Sorry I don't understand this...", exception.getMessage());
    }
}
