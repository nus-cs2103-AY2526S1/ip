package leo;


public class RemindCommand extends Command {
    private int daysAhead;

    public RemindCommand(int daysAhead) {
        this.daysAhead = daysAhead;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert daysAhead >= 0 : "daysAhead should be non-negative";
        try {
            String upcomingTasks = tasks.getUpcoming(daysAhead);
            return ui.showUpcoming(upcomingTasks, daysAhead);
        } catch (Exception e) {
            return ui.showError(e);
        }
    }
}
