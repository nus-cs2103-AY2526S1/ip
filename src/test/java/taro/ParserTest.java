package taro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import taro.command.Command;
import taro.corecomp.Parser;
import taro.corecomp.Storage;
import taro.corecomp.TaskList;
import taro.task.Deadline;
import taro.task.Task;
import taro.task.Todo;
import taro.ui.Ui;

public class ParserTest {
    static class TestUi extends Ui {
        private final StringBuilder buf = new StringBuilder();
        @Override public void show(String msg) {
            buf.append(msg).append('\n');
        }
        @Override public void showLine() {
            buf.append("LINE\n");
        }

    }


    static class TestStorage extends Storage {
        TestStorage() {
            super("teststore.txt");
        }
        @Override
        public void save(TaskList tasks) { }
    }


    @Test
    public void parseTask_deadlineCorrect() {
        String line = "D | 0 | return book | 2025-08-30";
        Task t = Parser.parseTask(line);
        assertTrue(t instanceof Deadline);
        String s = t.toString();
        assertTrue(s.contains("return book"));
        assertTrue(s.contains("2025"));
    }

    @Test
    public void parseCommand_todo_generatesCommand() throws Exception {
        Command c = Parser.parseCommand("todo read book");
        TestUi ui = new TestUi();
        TestStorage storage = new TestStorage();
        TaskList tasks = new TaskList();

        c.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Todo);
        assertEquals("[T][ ] read book", tasks.get(0).toString());
    }

}
