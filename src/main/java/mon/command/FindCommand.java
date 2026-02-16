package mon.command;

public class FindCommand extends Command {
    private static final String INDENT = "    ";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(mon.task.TaskList taskList, mon.storage.Storage storage) throws Exception {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            mon.task.Task task = taskList.getTask(i);
            if (task.getTaskName().contains(keyword)) {
                result.append(INDENT)
                        .append(i + 1)
                        .append(". ")
                        .append(task.toString())
                        .append("\n");
            }
        }

        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1); // Delete the last newline character
        }

        return result.toString();
    }
}
