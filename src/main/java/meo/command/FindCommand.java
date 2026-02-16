package meo.command;

import meo.FileHandler;
import meo.MeoException;
import meo.TaskFinder;
import meo.data.TextList;
import meo.ui.Ui;

public class FindCommand extends Command {

    public FindCommand(String commandContent) {
        super(commandContent, null);
    }

    @Override
    public String execute(Ui ui, TextList textList, FileHandler fileHandler) throws MeoException {
        TaskFinder taskFinder = new TaskFinder(textList);
        String[] keywords = {commandContent};
        TextList newTaskList = taskFinder.find(keywords);
        return ui.showFindResultMessage(newTaskList);
    }
}
