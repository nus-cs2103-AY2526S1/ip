package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.data.TextList;
import meo.task.Deadline;
import meo.task.Task;
import meo.ui.Ui;

/** 
 * Command that add a new Deadline task.
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String commandContent, String[] tags) {
        super(commandContent, tags);
    }

    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        Task newTask = new Deadline(commandContent, tags[0]);
        textList.add(newTask);
        return ui.showAddedTask(newTask.toString());
    }
}
