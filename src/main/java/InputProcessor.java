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

    // Function to add To-Do Task to list
    public void addInput(String taskName, String taskType) {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new Task(taskName);
        } else if (taskType.equals("deadline")) {
            int firstSlashIndex = taskName.indexOf("/");
            String actualName = taskName.substring(0, firstSlashIndex - 1);
            String deadline = taskName.substring(firstSlashIndex + 4);
            newTask = new DeadlineTask(actualName, deadline);
        } else {
            int firstSlashIndex = taskName.indexOf("/");
            String actualName = taskName.substring(0, firstSlashIndex - 1);
            String fromPlusTo = taskName.substring(firstSlashIndex + 1);
            firstSlashIndex = fromPlusTo.indexOf("/");
            String from = fromPlusTo.substring(5, firstSlashIndex - 1);
            String to = fromPlusTo.substring(firstSlashIndex + 4);
            newTask = new EventTask(actualName, from, to);
        }

        listOfTasks.add(newTask);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Aights. I have added this task:");
        System.out.println("        " + newTask);
        System.out.println("    Now you have " + listOfTasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    // Function to delete task
    public void deleteTask(int taskNumber) {
        Task task = listOfTasks.get(taskNumber);
        listOfTasks.remove(taskNumber);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Aights. I have deleted this task:");
        System.out.println("        " + task);
        System.out.println("    Now you have " + listOfTasks.size() + " tasks in the list.");
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

    public void processInput(String input) throws InvalidPromptException, TodoException {
        String[] words = input.split(" ");
        int firstSpaceIndex = input.indexOf(" ");
        String restOfinput = input.substring(firstSpaceIndex + 1);

        try {
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
            } else if (words[0].equals("delete")) {
                int index = Integer.parseInt(words[1]);
                deleteTask(index - 1);
            } else if (words[0].equals("todo") || words[0].equals("deadline") || words[0].equals("event")){
                if (words[0].equals("todo") && words.length == 1) {
                    throw new TodoException("      YIKES!!! You need to enter a description for a task!!!");
                }
                addInput(restOfinput, words[0]);
            } else {
                throw new InvalidPromptException("     YIKES!!! I do not quite understand what you just said :(");
            }
        } catch (TodoException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("    ____________________________________________________________");
        } catch (InvalidPromptException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("    ____________________________________________________________");
        }
    }
}
