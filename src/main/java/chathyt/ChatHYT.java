package chathyt;

import java.util.Scanner;
import java.util.ArrayList;

import chathyt.command.*;
import chathyt.exception.InvalidCommandException;
import chathyt.storage.Storage;
import chathyt.task.Task;
import chathyt.ui.Ui;

/**
 * The main class of the ChatHYT application.
 * <p>
 * ChatHYT handles user interaction for adding, deleting, marking,
 * unmarking, and finding tasks. It uses {@link Storage} for
 * persistent storage and {@link Ui} for user interface messages.
 * </p>
 */
public class ChatHYT {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<Task> tasks_to_be_found = new ArrayList<>();
    static Storage storage = new Storage("./data/duke.txt");

    /**
     * The main entry point of the application.
     * <p>
     * Continuously reads user input and executes commands until the user
     * enters "bye". Supports commands like "todo", "deadline", "event",
     * "delete", "mark", "unmark", "find", and "view".
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        tasks = storage.load();
        Ui.showWelcome();

        while(true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                Ui.sayGoodbye();
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                Ui.showList(tasks);
                continue;
            }

            if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    MarkCommand markCommand = new MarkCommand(index);
                    markCommand.execute(tasks, storage, new Ui());
            }

            if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    UnmarkCommand unmarkCommand = new UnmarkCommand(index);
                    unmarkCommand.execute(tasks, storage, new Ui());
            }

            try {
                if (input.startsWith("todo")) {
                    String input_todo = input.substring(4);
                    AddTodoCommand todoCommand = new AddTodoCommand(input_todo);
                    todoCommand.execute(tasks,storage, new Ui());

                } else if (input.startsWith("deadline")) {
                    String input_deadline = input.substring(8);
                    AddDeadlineCommand deadlineCommand = new AddDeadlineCommand(input_deadline);
                    deadlineCommand.execute(tasks, storage, new Ui());

                } else if (input.startsWith("event")) {
                    String input_event = input.substring(5);
                    AddEventCommand eventCommand = new AddEventCommand(input_event);
                    eventCommand.execute(tasks, storage, new Ui());

                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    DeleteCommand deleteCommand = new DeleteCommand(index);
                    deleteCommand.execute(tasks, storage, new Ui());
                } else if (input.startsWith("find")) {
                    String keyword = input.substring(4);
                    FindCommand findCommand = new FindCommand(keyword);
                    findCommand.execute(tasks, storage, new Ui());
                } else {
                    throw new InvalidCommandException("What?");
                }
            } catch (InvalidCommandException e) {
                Ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }
}

