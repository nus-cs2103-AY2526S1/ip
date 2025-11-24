package shaduke.ui;

import shaduke.clients.Client;
import shaduke.clients.ClientList;
import shaduke.tasks.Task;
import shaduke.tasks.TaskList;

import java.util.Scanner;

/**
 * Class that deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object with a scanner to handle input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showGreeting() {
        printDashes();
        System.out.println("Hark! I am Shaduke, thy humble servant.");
        System.out.println("Pray, what task dost thou command me to perform?");
        printDashes();
    }

    /**
     * Displays the goodbye message when the application is closed.
     */
    public void showAdios() {
        System.out.println("Fare thee well, noble soul. May Morpheus grant thee sweet visions and respite.");
        System.out.println("Till we meet anon, upon the morrow's stage of deeds and tasks.");
    }

    /**
     * Displays the message confirming that a task has been added.
     *
     * @param task the task added to the list.
     * @param size the current number of tasks.
     */
    public void showAdded(Task task, int size) {
        System.out.println("Lo! A task is wrought and placed upon thy list:");
        System.out.println("  " + task);
        System.out.println("Thou now possesseth " + size + " noble charges to fulfill.");
    }

    /**
     * Displays all current tasks.
     *
     * @param tasks The current list of tasks.
     */
    public void showList(TaskList tasks) {
        System.out.println("Behold, the labours thou hast undertaken:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays the message confirming that a task has been marked.
     *
     * @param task task that has been marked as done.
     */
    public void showMarked(Task task) {
        System.out.println("Huzzah! This task hath been vanquished:");
        System.out.println("  " + task);
    }

    /**
     * Displays the message confirming that a task has been unmarked.
     *
     * @param task task that has been marked as undone.
     */
    public void showUnmarked(Task task) {
        System.out.println("Soft! this task hath been vanquished:");
        System.out.println("  " + task);
    }

    /**
     * Displays the message confirming that a task has been deleted.
     *
     * @param task task that has been deleted.
     * @param size the current number of tasks.
     */
    public void showDeleted(Task task, int size) {
        System.out.println("Alas! This task hath met its end, consigned to oblivion:");
        System.out.println("  " + task);
        System.out.println("Yet thou hast " + size + " tasks still in thy ledger.");
    }

    /**
     * Shows the divider line.
     */
    public void printDashes() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the error message.
     *
     * @param errorMessage the message given by the exception thrown.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays tasks in the list with the string inputted.
     */
    public void showFind(TaskList tasks, String toSearch) {
        System.out.println("Here are the tasks with " + toSearch + ":");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(toSearch)) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * The message that confirms a client has been added.
     *
     * @param client The client being added.
     * @param clients The current list of clients.
     */
    public void showAddClient(Client client, ClientList clients) {
        System.out.println("Hail! " + client + " hath entered thy service.");
        System.out.println("Thou now commandest " + clients.size() + " loyal clients.");
    }

    /**
     * The message shown when a client is assigned to a task.
     * @param task the task the client is to join.
     * @param client the client we are assigning.
     */
    public void showAssignClient(Task task, Client client) {
        System.out.println(client + " added to " + task);
    }

    /**
     * Displays all added clients.
     *
     * @param clients The current list of clients.
     */
    public void showClients(ClientList clients) {
        System.out.println("Behold, thy valiant clients:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + ". " + clients.get(i));
        }
    }

    /**
     * Displays a message confirming a client has been deleted.
     *
     * @param client The client to be deleted.
     */
    public void showDeleteClient(Client client) {
        System.out.println("Alas! " + client + " hath departed from thy fold.");
    }

    /**
     * Displays the message when a client leaves a selected task.
     *
     * @param index the index of the task the client is leaving.
     * @param client the client that is leaving the task.
     */
    public void showLeave(int index, Client client) {
        System.out.println(client + " hath forsaken the task numbered " + (index + 1));
    }

    /**
     * Lists the tasks a client is part of.
     *
     * @param tasks the current list of tasks.
     * @param client the client we are interested in.
     */
    public void showClientTasks(TaskList tasks, Client client) {
        System.out.println("Tasks wherein " + client + " doth partake:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getClient() != null && task.getClient().equals(client)) {
                task.deleteClient();
                System.out.println((i + 1) + ". " + tasks.get(i));
                task.addClient(client);
            }
        }
    }

    /**
     * Retrieves the instruction to be parsed.
     *
     * @return return the next input from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
