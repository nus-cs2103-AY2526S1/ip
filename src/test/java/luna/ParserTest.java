package luna;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import luna.ui.Ui;
import luna.tasks.Todo;


class ParserTest {

    @Test
    void testParseTodo() throws LunaException {
        Command cmd = Parser.parse("todo Read book");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void testParseMarkCommand() throws LunaException {
        TaskList taskList = new TaskList(new java.util.ArrayList<>(), new Storage("data/luna.txt"));
        taskList.addTask(new Todo("Test task", false));

        Command cmd = Parser.parse("mark 1");
        cmd.execute(taskList, new Ui());

        assertTrue(taskList.getTaskList().get(0).isDone());
    }

    @Test
    void testParseInvalidCommand() throws LunaException {
        Command cmd = Parser.parse("invalid command");
        assertThrows(LunaException.InvalidCommandException.class, () -> {
            cmd.execute(new TaskList(new java.util.ArrayList<>(), new Storage("data/luna.txt")), new Ui());
        });
    }

}
