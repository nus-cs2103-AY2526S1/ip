package focus;

/**
 * Finds and lists tasks whose description contains a given keyword (case-insensitive).
 */
public class FindCommand extends FocusCommand {

    private final String searchKeyword;

    /**
     * Constructs a FindCommand.
     *
     * @param keyword Keyword to search for.
     */
    public FindCommand(String keyword) {
        this.searchKeyword = keyword.toLowerCase();
    }

    /**
     * Executes the command by printing the matching tasks.
     *
     * @param taskList Task list to search.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.printMatchingWords(this.searchKeyword);
    }

}
