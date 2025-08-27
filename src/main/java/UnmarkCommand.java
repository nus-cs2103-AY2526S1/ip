public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RainyException {
        tasks.unmarkTask(index);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        System.out.println("oki, i've marked this task as not done yet:\n  " + tasks.getTask(index));
        ui.showLine();
    }
}
