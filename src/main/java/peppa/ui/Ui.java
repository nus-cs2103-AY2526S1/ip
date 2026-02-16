package peppa.ui;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String NAME = "peppa.command.Peppa";

    private static final String LOGO = ".______    _______ .______   .______      ___      \n"
            + "|   _  \\  |   ____||   _  \\  |   _  \\    /   \\     \n"
            + "|  |_)  | |  |__   |  |_)  | |  |_)  |  /  ^  \\    \n"
            + "|   ___/  |   __|  |   ___/  |   ___/  /  /_\\  \\   \n"
            + "|  |      |  |____ |  |      |  |     /  _____  \\  \n"
            + "| _|      |_______|| _|      | _|    /__/     \\__\\\n";


    public void printGreetingMsg() {
        String str = "Hello! I'm " + NAME + "!\nWhat can I do for you?";
        System.out.println(str);
    }
    public void printline() {
        System.out.println(LINE);
    }

    public void printLogo() {
        System.out.println(LOGO);
    }
    public static void printExitMsg() {
        String str = "Bye. Hope to see you again soon!";
        System.out.println(str);
    }
}
