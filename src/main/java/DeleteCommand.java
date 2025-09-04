import java.io.IOException;

public class DeleteCommand extends Command {
    private final String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        try {
            //Delete task
            Task task = tasks.remove(Integer.parseInt(this.index) - 1);

            //Write DB changes
            db.writeDb(tasks.getAll());

            ui.printMsg(String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task,
                tasks.size()
            ));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            ui.printErr("Invalid index: " + this.index);
        } catch (IOException ex) {
            ui.printErr("Error accessing CHASHDB, all data in memory will be lost on exit");
        }
    }
}
