package command;

import exception.FindException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    /**
     * Constructs a {@link FindCommand} with the relevant user input.
     *
     * @param input the raw user input that triggered the command
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Executes the find command: finds all tasks that contain the search query in their title,
     * displays the matching tasks.
     *
     * @param t the {@link TaskList} where tasks are stored
     * @param u the {@link Ui} that the user interacts with
     * @param s the {@link Storage} that retrieves and updates the save file
     * @throws FindException
     */
    @Override
    public void execute(TaskList t, Ui u, Storage s) throws FindException {
        try {
            String filterText = input.substring(5);
            if (filterText.isEmpty()) throw new Exception();

            TaskList filtered = t.filter(filterText);

            u.chatbotPrint("Here are the matching task(s) in your list:" + filtered.toString());
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
