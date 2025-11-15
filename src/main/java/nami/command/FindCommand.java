package nami.command;
import nami.ui.Ui;
import nami.storage.Storage;
import nami.task.TaskList;
import nami.task.Tasks;
import nami.exception.DukeException;
import java.util.ArrayList;
public class FindCommand extends Command {
    private final String Keyword;

    public FindCommand(String Keyword) {
        this.Keyword = Keyword;
    }

    /**
     * Executes the command to find something in the list
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(Keyword == null || Keyword.isBlank()) {
            throw new DukeException("Please provide a keyword to find");
        }
        String word = Keyword.toLowerCase();

        ArrayList<Tasks> matches = new ArrayList<>();
        for(int i =0; i < tasks.size(); i++) {
            Tasks t = tasks.get(i);
            if(t.getDescription().toLowerCase().contains(word)) {
                matches.add(t);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________ \n");
        if(matches.isEmpty()) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("Here are the matching tasks in your list: \n");
            for(int i = 0; i < matches.size(); i++) {
                sb.append((i + 1));
                sb.append(". ");
                sb.append(matches.get(i).getList());
                sb.append("\n");
            }
        }
        sb.append("____________________________________");
        return sb.toString();
    }
}
