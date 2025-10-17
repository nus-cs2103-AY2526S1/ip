package iris;

/** Handles user interactions and messages **/
public class Ui {
    private String lastMessage = "";

    /** Returns the last message shown to the user **/
    public String getLastMessage() {
        return lastMessage;
    }

    /** Displays the welcome message with the given logo **/
    public void showWelcome(String logo) {
        lastMessage = "Hello! I'm\n" + logo + "\nWhat can I do for you?";
    }

    /** Displays the exit message **/
    public void showExit() {
        lastMessage = "Bye. Hope to see you again soon!";
    }

    /** Displays an error message **/
    public void showError(String message) {
        lastMessage = "Error! " + message;
    }

    /** Displays a general message **/
    public void showMessage(String msg) {
        lastMessage = msg;
    }
}
