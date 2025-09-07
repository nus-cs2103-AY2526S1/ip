package john.core.command;

public record CommandResult(String feedback, boolean exit) {
    public static CommandResult ok(String msg) {
        return new CommandResult(msg, false);
    }

    public static CommandResult exit(String msg) {
        return new CommandResult(msg, true);
    }
}