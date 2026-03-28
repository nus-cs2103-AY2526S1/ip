package cattis.command;

import cattis.CattisInterface;
import cattis.exception.CattisException;

/**
 * Represents the command to find the <code>Task</code>
 * with specific keyword <code>taskName</code> from <code>cattis.getTaskList()</code>.
 */
public class FindTaskCommand extends Command {
    private final String taskName;

    public FindTaskCommand(String taskName) {
        this.taskName = taskName.trim();
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        cattis.getUi().showMessage(cattis.getTaskList().listByName(this.taskName));
    }
}
