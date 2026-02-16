package mon.command;

import mon.storage.Storage;
import mon.task.TaskList;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    private static final String INDENT = "    ";
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < taskList.size(); i++) {
            result.append(INDENT)
                  .append(i + 1)
                  .append(". ")
                  .append(taskList.getTask(i).toString());
            
            if (i < taskList.size() - 1) {
                result.append("\n");
            }
        }
        
        return result.toString();
    }
}
