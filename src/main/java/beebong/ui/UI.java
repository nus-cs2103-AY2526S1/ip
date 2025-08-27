package beebong.ui;

public class UI {
    public void printBorder() {
        String BORDER = "____________________________________________________________";
        System.out.println(BORDER);
    }

    public void showMessage(String message) {
        printBorder();
        System.out.println(message);
        printBorder();
    }

    public void showErrorMessage(String errorMessage) {
        showMessage("Bong Alert! - " + errorMessage);
    }

    public void showGreetingMessage() {
        showMessage("Ding Dong! Guess who? It’s B. Bong!\nHow can I bong your day brighter?");
    }

    public void showExitMessage() {
        showMessage("Ding ding! Time to go. See you soon!");
    }

    public void showCommands() {
        String commandList = """
                    List - lists out all tasks
                    Find [keyword] - lists out all tasks whose name contains the keyword
                    Mark [task no.] - mark the task with the given number as completed
                    Unmark [task no.] - mark the task with the given number as incomplete
                    Delete [task no.] - removes a task from the list
                    Help - shows full command list
                    Bye - exit
                    (Enter Dates using this format: DD/MM/YYYY hh:mm, time is optional)
                    Enter a new Task name or Command""";
        showMessage(commandList);
    }
}
