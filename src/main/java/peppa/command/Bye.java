package peppa.command;

public class Bye implements Command {
    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!";
    }
}
