package sam.ui;

import sam.task.Task;

public class Ui {
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Sam");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(final String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows an error message with multiple lines using varargs.
     * 
     * @param messages Variable number of message lines to display
     */
    public void showError(final String... messages) {
        System.out.println("____________________________________________________________");
        for (String message : messages) {
            System.out.println(" " + message);
        }
        System.out.println("____________________________________________________________");
    }

    public void printAdded(final Task t, final int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + t);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            return scanner.nextLine();
        }
    }
}
