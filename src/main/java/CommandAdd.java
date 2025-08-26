import java.text.ParseException;

public class CommandAdd extends Command {
    @Override
    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(Task.parseTask(input));
            ui.printAdd(taskList.get(taskList.size() - 1));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
