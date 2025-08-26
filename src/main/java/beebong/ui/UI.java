package beebong.ui;

public class UI {

    public void printBorder() {
        String BORDER = "____________________________________________________________";
        System.out.println(BORDER);
    }

    public void botMessage(String message) {
        printBorder();
        System.out.println(message);
        printBorder();
    }

    public void botErrorMessage(String errorMessage) {
        botMessage("Bong Alert! - " + errorMessage);
    }

    public void greetingMessage() {
        botMessage("Ding Dong! Guess who? It’s B. Bong!\nHow can I bong your day brighter?");
    }

    public void exitMessage() {
        botMessage("Ding ding! Time to go. See you soon!");
    }

    public void showCommands() {
        String commandList = """
                    List - lists out all tasks
                    Mark [task no.] - mark the task with the given number as completed
                    Unmark [task no.] - mark the task with the given number as incomplete
                    Delete - removes a task from the list
                    Help - shows full command list
                    Bye - exit
                    (Enter Dates using this format: DD/MM/YYYY hh:mm, time is optional)
                    Enter a new Task name or Command""";
        botMessage(commandList);
    }
}
