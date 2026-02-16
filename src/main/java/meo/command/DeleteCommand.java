package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.data.TextList;
import meo.ui.Ui;

/** 
 * Command that delete a task.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String commandContent) {
        super(commandContent, null);
    }
    
    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        int index = Integer.parseInt(commandContent);
        textList.deleteTask(index - 1);
        return ui.showDeletedMessage();
    }
}
