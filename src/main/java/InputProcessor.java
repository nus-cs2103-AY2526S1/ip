/**
 * The purpose of this class is to process inputs
 */

import java.util.List;
import java.util.ArrayList;

public class InputProcessor {
    // Declare fields
    private List<String> listOfTasks;

    // Constructor
    public InputProcessor() {
        this.listOfTasks = new ArrayList<>();
    }

    // Function to add input to list
    public void addInput(String input) {
        listOfTasks.add(input);
        System.out.println("    ____________________________________________________________");
        System.out.println("    added: " + input);
        System.out.println("    ____________________________________________________________");
    }

    // Function to display items in list
    public void displayList() {
        int len = listOfTasks.size();

        System.out.println("    ____________________________________________________________");
        for (int i = 1; i <= len; i++) {
            System.out.println("    " + i + ". " + listOfTasks.get(i - 1));
        }
        System.out.println("    ____________________________________________________________");
    }

    public void processInput(String input) {
        if (input.equals("list")) {
            displayList();
        } else {
            addInput(input);
        }
    }
}
