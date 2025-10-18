package peppa.command;

public class UnknownCommand implements Command {
    @Override
    public String execute() {
        return "Oopsies, I don't know what that means!";
    }
}
