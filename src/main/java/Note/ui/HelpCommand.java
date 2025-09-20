package Note.ui;

public class HelpCommand {
    public static String getHelpMessage() {
        return "Here are the available commands:\n"
                + "todo DESCRIPTION\n"
                + "deadline DESCRIPTION /by DATE TIME\n"
                + "event DESCRIPTION /from DATE TIME /to DATE TIME\n"
                + "list\n"
                + "mark INDEX\n"
                + "unmark INDEX\n"
                + "delete INDEX\n"
                + "find KEYWORD\n"
                + "bye\n"
                + "help";
    }
}
