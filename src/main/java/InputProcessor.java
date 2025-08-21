/**
 * The purpose of this class is to process inputs
 */

import java.util.List;
import java.util.ArrayList;

public class InputProcessor {
    // Declare fields
    private List<Task> listOfTasks;

    // Constructor
    public InputProcessor() {
        this.listOfTasks = new ArrayList<>();
    }

    // Function to add input to list
    public void addInput(String input) {
        Task newTask = new Task(input);
        listOfTasks.add(newTask);
        System.out.println("    ____________________________________________________________");
        System.out.println("    added: " + input);
        System.out.println("    ____________________________________________________________");
    }

    // Function to display items in list
    public void displayList() {
        int len = listOfTasks.size();

        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list!");
        for (int i = 1; i <= len; i++) {
            System.out.println("    " + i + "." + listOfTasks.get(i - 1));
        }
        System.out.println("    ____________________________________________________________");
    }

    public void processInput(String input) {
        String[] words = input.split(" ");
        if (input.equals("list")) {
            displayList();
        } else if (words[0].equals("mark")) {
            int index = Integer.parseInt(words[1]);
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
        } else if (words[0].equals("unmark")) {
            int index = Integer.parseInt(words[1]);
            Task task = listOfTasks.get(index - 1);
            task.unmarkTask();
        } else {
            addInput(input);
        }
    }
}
