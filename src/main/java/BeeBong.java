import java.util.Scanner;

public class BeeBong {
    private final String newLine = "____________________________________________________________";
//    private final String genericErrorMsg = "Bong! Something went boom in B. Bong’s circuits. Try again!";
    private int currTask = 0;
    private final Task[] tasklist = new Task[100];

    private void botMessage(String message) {
        System.out.println(this.newLine);
        System.out.println(message);
        System.out.println(this.newLine);
    }

    private void botErrorMessage(String errorMessage) {
        botMessage("Bong Alert! "+errorMessage);
    }

    private void greetingMessage() {
        botMessage("Ding Dong! Guess who? It’s B. Bong!\nHow can I bong your day brighter?");
    }

    private void exitMessage() {
        botMessage("Ding ding! Time to go. See you soon!");
    }

    private void listTasks() {
        // If there are no Tasks to list
        if (this.currTask == 0) {
            botMessage("Bong! I searched high and low… still nothing to show right now.");
            return;
        }
        System.out.println(this.newLine);
        System.out.println("Bing! Here’s what’s buzzing in your list, courtesy of B. Bong:");
        for (int i = 0; i < this.currTask; i++) {
            System.out.println((i + 1) + ". " + this.tasklist[i]);
        }
        System.out.println(this.newLine);
    }

    private void markTaskAs(String params, boolean status) {
        // Check if params were provided
        if (params == null) {
            botErrorMessage("You forgot the task number!");
            return;
        }
        try {
            int taskNum = Integer.parseInt(params) - 1;
            //Check for valid task number
            if (taskNum < 0 || taskNum >= this.currTask) {
                botErrorMessage("That task number doesn’t exist. Try a real one!");
                return;
            }
            //Mark Task as Completed/Incomplete
            if (status) this.tasklist[taskNum].markCompleted();
            else this.tasklist[taskNum].markIncomplete();
            botMessage("Bing! Task #" + (taskNum + 1) + " marked as " + (status ? "complete" : "incomplete") + "!");
        } catch (NumberFormatException e) {
            botErrorMessage("That task number doesn’t exist. Try a real one!");
        }
    }

    public void Start() {
        greetingMessage();

        boolean running = true;
        while (running) {
            // Ask for user input
            String commandList = """
                    List - lists out previous inputs
                    Mark [task no.] - mark the task with the given number as completed
                    Unmark [task no.] - mark the task with the given number as incomplete
                    Bye / Q - exit
                    Enter a new Task name or Command""";
            botMessage(commandList);
            System.out.print(">>> ");
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();

            // Check for Commands
            String[] commandParts = input.toLowerCase().split(" ", 2);
            String command = commandParts[0];
            String params = commandParts.length > 1 ? commandParts[1] : null;
            switch (command) {
                case "bye":
                case "q":
                    running = false;
                    break;
                case "list":
                    // List all Tasks
                    listTasks();
                    break;
                case "mark":
                case "unmark":
                    // Mark the task as complete or incomplete
                    markTaskAs(params, command.equals("mark"));
                    break;
                default:
                    // Add the input as a new Task to Task List
                    this.tasklist[currTask] = new Task(input);
                    botMessage("Added New Task: "+input);
                    this.currTask += 1;
            }
        }

        exitMessage();
    }
}
