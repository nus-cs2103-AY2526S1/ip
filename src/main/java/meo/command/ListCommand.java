package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.data.TextList;
import meo.ui.Ui;

/** 
 * Command that print the task list.
 */
public class ListCommand extends Command{

    public ListCommand() {
    }
    
    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        return textList.getList();
    }

}
