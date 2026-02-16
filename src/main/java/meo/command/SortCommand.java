
package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.Sorter;
import meo.data.TextList;
import meo.ui.Ui;


/** 
 * Command that print the task list.
 */
public class SortCommand extends Command{
    private static final int ASCENDING = 0;
    private static final int DESCENDING = 1;
    int order = 0;

    public SortCommand(int order) {
        this.order = order;
    }
    
    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        Sorter sorter = new Sorter();
        if (order == ASCENDING) {
            sorter.sortAscByName(textList);
        } else if (order == DESCENDING) {
            sorter.sortDescByName(textList);
        }
        return textList.getList();
    }
}

