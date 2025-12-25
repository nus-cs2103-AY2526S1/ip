package leo;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether the command is the exit command or not
     * @return False since all the other commands are not exit commands
     */
    public boolean isExit() {
        return false;
    }
}
