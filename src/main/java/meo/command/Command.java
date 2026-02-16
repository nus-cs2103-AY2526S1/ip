package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.data.TextList;
import meo.ui.Ui;

/** 
 * Command parent class for specific commands to inherit as child class 
 */
public class Command {
    protected String commandContent;
    String[] tags;

    public Command(String commandContent, String[] tags) {
        this.commandContent = commandContent;
        this.tags = tags;
    }

    public Command() {
    }

    /**
     * Checks if the command is the Exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     * 
     * @param ui UI of the bot.
     * @param textList Current list of tasks.
     * @param fileHandler To save and load task list into file.
     * @throws MeoException If encounter error while executing command.
     * @return Return Meo's message.
     */
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        return "";
    }
}
