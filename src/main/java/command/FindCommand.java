package command;

import exception.FindException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) throws FindException {
        try {
            String filterText = input.substring(5);
            if (filterText.isEmpty()) throw new Exception();

            TaskList filtered = t.filter(filterText);

            u.chatbotPrint("Here are the matching tasks in your list:" + filtered.toString());
        } catch (Exception e) {
            throw new FindException("I'm not sure what you're trying to find. Try again?");
        }
    }

    /**
     * Returns the help message associated with the find command
     *
     * @return the corresponding help message
     */
    @Override
    public String getHelpMessage() {
        return """
                find:
                finds all tasks that contain the search query in their title.
                format: find [search query]""";
    }
}
