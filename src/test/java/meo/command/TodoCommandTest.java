package meo.command; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import meo.data.TextList;
import meo.parser.CommandParser;
import meo.ui.Ui;

public class TodoCommandTest {
    @Test
    public void execute_success() {
        try {
            TextList textList = new TextList();
            Ui ui = new Ui();
            CommandParser parser = new CommandParser();

            Command command = parser.parser("todo eat grass");
            command.execute(ui, textList, null);

            assertEquals(1, textList.getSize());
            assertEquals("[T][ ] eat grass", textList.printTask(0));
        } catch (Exception e) {
            fail();
        }
    }

}
