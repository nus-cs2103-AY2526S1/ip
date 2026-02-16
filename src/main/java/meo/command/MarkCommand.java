package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.data.TextList;
import meo.ui.Ui;

/** 
 * Command that mark a task as done.
 */
public class MarkCommand extends Command {

    public MarkCommand(String commandContent) {
        super(commandContent,null);
    }

    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        int index = Integer.parseInt(commandContent);
        textList.markTask(index - 1);
        return ui.showCompletedMessage(textList.printTask(index - 1));
    }
}
