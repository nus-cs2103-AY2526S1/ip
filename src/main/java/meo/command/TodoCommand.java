package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.data.TextList;
import meo.task.Task;
import meo.task.ToDo;
import meo.ui.Ui;

/** 
 * Command that add a new Todo task.
 */
public class TodoCommand extends Command {

    public TodoCommand(String commandContent) {
        super(commandContent, null);
    }

    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        Task newTask = new ToDo(commandContent);
        textList.add(newTask);
        return ui.showAddedTask(newTask.toString());
    }
}
