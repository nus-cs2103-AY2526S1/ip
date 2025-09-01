public class AddCommand extends Command {
    private final String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showMessage("added: " + description);
        taskList.add(new Task(description, false));
    }
}
