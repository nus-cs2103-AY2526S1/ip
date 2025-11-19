package Coffee;

public class UnknownCommand extends Command {
    private final String input;

    public UnknownCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "Your input was: " + input);
    }
}
