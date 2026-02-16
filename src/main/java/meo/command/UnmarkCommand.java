package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.data.TextList;
import meo.ui.Ui;

/** 
 * Command that mark a task as not done.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(String commandContent) {
        super(commandContent,null);
    }

    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        int index = Integer.parseInt(commandContent);
        textList.unmarkTask(index - 1);
        return ui.showIncompletedMessage(textList.printTask(index - 1));
    }
}
