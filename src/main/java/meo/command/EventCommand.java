package meo.command;

import meo.FileHandler;
import meo.data.TextList;
import meo.task.Event;
import meo.task.Task;
import meo.ui.Ui;

/** 
 * Command that add a new Event task.
 */
public class EventCommand extends Command {

    public EventCommand(String commandContent, String[] tags) {
        super(commandContent, tags);
    }

    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) {
        Task newTask = new Event(commandContent, tags[0], tags[1]);
        textList.add(newTask);
        return ui.showAddedTask(newTask.toString());
    }
}
