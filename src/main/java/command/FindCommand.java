package command;

import java.util.StringJoiner;

import javafx.util.Pair;
import misc.PepeException;
import state.Storage;
import state.TaskList;
import state.Ui;


/**
 * Command to find a task in the list given an exact match on the provided phrase.
 */
public class FindCommand implements Command {
    private final String matchPhrase;

    public FindCommand(String matchPhrase) {
        this.matchPhrase = matchPhrase;
    }

    /**
     * Factory method to construct a FindCommand from the user input.
     * @param args A list of user-input strings which form a spaced phrase
     * @return An instance of the FindCommand
     * @throws PepeException if an exception occurred while parsing user input or constructing FindCommand class
     */
    public static FindCommand fromInput(String[] args) throws PepeException {
        if (args.length == 0) {
            throw new PepeException("Please provide a search term");
        }
        StringJoiner matchPhraseJoiner = new StringJoiner(" ");
        for (String arg : args) {
            matchPhraseJoiner.add(arg);
        }
        return new FindCommand(matchPhraseJoiner.toString());
    }

    @Override
    public Pair<String, Boolean> execute(Ui ui, Storage storage, TaskList tasks) throws PepeException {
        TaskList matchedTaskList = tasks.filter(matchPhrase);
        String message;
        if (matchedTaskList.isEmpty()) {
            message = ui.formatMessage("No tasks match the provided search term: " + matchPhrase);
        } else {
            message = ui.displayTaskList("Here are the matching tasks in your list:", matchedTaskList);
        }
        return new Pair<>(message, true);
    }
}
