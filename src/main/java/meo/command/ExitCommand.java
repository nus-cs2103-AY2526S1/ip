package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.data.TextList;
import meo.ui.Ui;

/** 
 * Command that exit the bot.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
    }

    /**
     * {@inheritDoc}
     * 
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        textList.saveList();
        return ui.showExitMessage();
    }
}
