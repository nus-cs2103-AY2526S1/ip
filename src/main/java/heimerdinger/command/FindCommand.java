package heimerdinger.command;

import heimerdinger.HeimerdingerException;
import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.Task;
import heimerdinger.task.TaskList;

/**
 * Finds tasks whose descriptions contain a matching keyword.
 *
 * <p><b>Usage:</b> {@code find &lt;text&gt;}</p>
 *
 * <p>This command validates that the search query is not empty and consists of only one word.
 * If the query is blank or contains multiple words, a {@link HeimerdingerException} is thrown.
 * Matching is performed by splitting each task description into words and checking for
 * exact equality with the query.</p>
 */
public class FindCommand extends Command {
    private final String matchingWord;

    /**
     * Constructs a {@code FindCommand} with the given search word.
     *
     * @param matchingWord the keyword to search for; may not be blank
     */
    public FindCommand(String matchingWord) {
        this.matchingWord = matchingWord;
    }


    /**
     * Searches through the {@code tasks}, collects those whose descriptions contain the given
     * {@code matchingWord}, and displays the results via {@code ui}.
     *
     * <p>Validation rules:</p>
     * <ul>
     *   <li>The query must not be empty.</li>
     *   <li>The query must consist of a single word (no spaces).</li>
     * </ul>
     *
     * @param tasks   the live task list to search; must not be {@code null}
     * @param ui      the UI sink used to display found tasks; must not be {@code null}
     * @param storage the persistence layer (unused in this command)
     * @throws HeimerdingerException if the query is blank or contains multiple words
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeimerdingerException {
        if (matchingWord.isEmpty()) {
            throw new HeimerdingerException("I can't read your mind great scientist. "
                    + "Let me know what you are trying to find!");
        } else {
            String[] split = matchingWord.split(" ");
            if (split.length > 1) {
                throw new HeimerdingerException("You should only enter 1 keyword for me to look for!");
            }
        }
        TaskList displayList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription();
            String[] split = description.split(" ");
            for (int j = 0; j < split.length; j++) {
                if (split[j].trim().equals(matchingWord)) {
                    displayList.add(task);
                    break;
                }
            }
        }
        ui.showFoundTasks(displayList, matchingWord);
    }
}
