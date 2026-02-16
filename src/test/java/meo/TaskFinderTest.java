package meo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import meo.command.Command;
import meo.data.TextList;
import meo.parser.CommandParser;
import meo.ui.Ui;

public class TaskFinderTest {

    private static String[] commands = {"deadline eat floor /by 5pm",
        "event eat grass /from mond 10am /to 2pm",
        "todo hangout"
        };
    private static String[] keywords = {"hangout"};
    private static Ui ui = new Ui();

    public TextList createTaskList() {
        TextList taskList = new TextList();
        for (String c : commands) {
            try {
                Command command = new CommandParser().parser(c);
                command.execute(ui, taskList, null);
            } catch (MeoException e) {
                e.printStackTrace();
            }
        }
        return taskList;
    }

    @Test
    public void findTasks_byOneKeyword_success() {
        TextList taskList = createTaskList();
        TaskFinder taskFinder = new TaskFinder(taskList);
        assertEquals(1, taskFinder.find(keywords).getSize());
    }
}
