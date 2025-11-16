package tuesday.ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private final String HELLO_MESSAGE = """
            Hello! I'm Tuesday
            What can I do for you?
            """;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("______________________");
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Tuesday");
        System.out.println("What can I do for you?");
        System.out.println("Start with 'todo', 'deadline' or 'event' + 'task_description' + 'time' to create respective task");
        System.out.println("Start with 'mark' or 'unmark' + 'task_index' to change status of the task ");
        System.out.println("Time format should be: dd-MM-yyyy HHmm" );
    }





    public void showDataSaved() {
        System.out.println("Data saved successfully!");
    }

    public void showDataSaving() {
        System.out.println("Saving data...");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String error) {
        System.out.println("Error: " + error);
    }

}
