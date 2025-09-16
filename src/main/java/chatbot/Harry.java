package chatbot;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

import chatbot.command.Parser;
import chatbot.command.Response;
import chatbot.command.Ui;
import chatbot.io.TaskList;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDo;

/**
 * Represents the main chatbot logic and command handler for Harry.
 *
 * The {@code Harry} class manages tasks using a {@link TaskList}, processes user
 * commands via a {@link Parser}, and interacts with the user through {@link Ui}.
 * It supports operations such as adding, marking, unmarking, deleting,
 * listing, and finding tasks. The chatbot maintains an {@code isExit} flag
 * to indicate when the session should end.
 */
public class Harry {
    protected TaskList data;
    protected Ui userInput;
    protected Parser parser;
    private boolean isExit = false;
    private final Map<String, Function<String[], String>> commands = Map.ofEntries(
            Map.entry("mark", this::commandMark),
            Map.entry("unmark", this::commandUnmark),
            Map.entry("delete", this::commandDelete),
            Map.entry("list", this::commandList),
            Map.entry("todo", this::commandTodo),
            Map.entry("deadline", this::commandDeadline),
            Map.entry("event", this::commandEvent),
            Map.entry("bye", this::commandBye),
            Map.entry("find", this::commandFind),
            Map.entry("snooze", this::commandSnooze),
            Map.entry("", this::commandNone)
    );

    /**
     * Creates a new instance of {@code Harry}.
     * Initializes the task list, user interface, and parser
     * with the available commands.
     */
    public Harry() {
        data = new TaskList();
        userInput = new Ui(System.in);
        parser = new Parser(commands);
    }

    /**
     * Begins the interaction with the user.
     * Resets the exit flag and displays the welcome message.
     * This method is typically called at the start of the program.
     */
    public void begin_interact() {
        String input = "";
        isExit = false;
        sayHello();
    }

    public boolean getExit() {
        return isExit;
    }

    public static void main(String[] args) {
        Harry chatbot = new Harry();
        chatbot.begin_interact();
    }


    public String getResponse(String input) {
        String[] parts = input.split(" ", 2);
        assert !isExit : "isExit should still be false";
        String response = parser.getCommand(parts).apply(parts);
        assert response != null : "Response should never be null";
        return response;
    }

    private String sayHello() {
        return getLine() + "Hello from\n" + getName() + "\n"
                + "Is there anything I can do for you?\n"
                + getLine();
    }
    private String sayGoodbye() {
        return getLine() + "Nice talking to you! I hope to see you again.\n" + getLine();
    }

    private String commandMark(String[] input) {
        StringBuilder response = new StringBuilder();
        try {
            int item = Integer.parseInt(input[1]) - 1;
            data.complete(item);
            response.append(getLine());
            response.append(Response.MARK_SUCCESS.getMessage()).append(data.print(item)).append("\n");
        } catch (NumberFormatException e) {
            response.append(Response.NUMBER_FAILURE.getMessage()).append("\n");
        } catch (Exception e) {
            response.append(Response.MARK_FAILURE.getMessage()).append("\n");
        } finally {
            response.append(getLine());
        }
        return response.toString();
    }

    private String commandUnmark(String[] input) {
        StringBuilder response = new StringBuilder();
        try {
            int item = Integer.parseInt(input[1]) - 1;
            data.uncomplete(item);
            response.append(getLine());
            response.append(Response.UNMARK_SUCCESS.getMessage()).append(data.print(item)).append("\n");
        } catch (NumberFormatException e) {
            response.append(Response.NUMBER_FAILURE.getMessage()).append("\n");
        } catch (Exception e) {
            response.append(Response.UNMARK_FAILURE.getMessage()).append("\n");
        } finally {
            response.append(getLine());
        }
        return response.toString();
    }

    private String commandDelete(String[] input) {
        StringBuilder response = new StringBuilder();
        try {
            int item = Integer.parseInt(input[1]) - 1;
            response.append(getLine());
            response.append(Response.REMOVE_TASK.getMessage()).append(data.print(item)).append("\n");
            data.remove(item);
            response.append("You have ").append(data.getSize()).append(" tasks remaining.").append("\n");
        } catch (NumberFormatException e) {
            response.append(Response.NUMBER_FAILURE.getMessage()).append("\n");
        } catch (Exception e) {
            response.append(Response.DELETE_FAILURE.getMessage()).append("\n");
        } finally {
            response.append(getLine());
        }
        return response.toString();
    }

    private String commandList(String[] input) {
        StringBuilder response = new StringBuilder(getLine());
        response.append(Response.LIST_TASKS.getMessage()).append("\n");
        for (int index = 1; index < data.getSize() + 1; index++) {
            response.append(index).append(". ").append(data.print(index - 1)).append("\n");
        }
        if (data.getSize() == 0) {
            response.append(Response.LIST_FAILURE.getMessage()).append("\n");
        }
        return response.append(getLine()).toString();
    }

    private String commandTodo(String[] input) {
        try {
            data.add(new ToDo(input[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            return "HEY! What are we chatbot.task.ToDo??\n" + getLine();
        }
        return getLine()
                + Response.TASK_SUCCESS.getMessage() + "\n"
                + data.printLast() + "\n"
                + "Now you have " + data.getSize() + " tasks in the list." + "\n"
                + getLine();
    }

    private String commandDeadline(String[] input) {
        try {
            String[] arguments = input[1].split(" /by ");
            data.add(new Deadline(arguments[0], arguments[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            return "HEY! What deadline??\n" + getLine();
        }
        return getLine()
                + Response.TASK_SUCCESS.getMessage() + "\n"
                + data.printLast() + "\n"
                + "Now you have " + data.getSize() + " tasks in the list." + "\n"
                + getLine();
    }

    private String commandEvent(String[] input) {
        try {
            String[] arguments = input[1].split(" /(from |to )");
            data.add(new Event(arguments[0], arguments[1], arguments[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            return "HEY! What event??\n" + getLine();
        }
        return getLine()
                + Response.TASK_SUCCESS.getMessage() + "\n"
                + data.printLast() + "\n"
                + "Now you have " + data.getSize() + " tasks in the list." + "\n"
                + getLine();
    }

    private String commandBye(String[] input) {
        isExit = true;
        return sayGoodbye();
    }

    private String commandFind(String[] input) {
        ArrayList<Task> filteredList = data.filter((Task t) -> t.toString().matches(".*" + input[1] + ".*"));
        StringBuilder response = new StringBuilder(getLine());
        response.append(Response.LIST_TASKS.getMessage()).append(" (filtered)\n");
        for (int index = 1; index < filteredList.size() + 1; index++) {
            response.append(index).append(". ").append(filteredList.get(index - 1).toString()).append("\n");
        }
        if (filteredList.isEmpty()) {
            response.append(Response.LIST_FAILURE.getMessage()).append("\n");
        }
        return response.append(getLine()).toString();
    }

    private String commandSnooze(String[] input) {
        StringBuilder response = new StringBuilder();
        try {
            int item = Integer.parseInt(input[1]) - 1;
            response.append(getLine());
            data.snooze(item);
            response.append(Response.SNOOZE_SUCCESS.getMessage()).append(data.print(item)).append("\n");
        } catch (NumberFormatException e) {
            response.append(Response.NUMBER_FAILURE.getMessage()).append("\n");
        } catch (Exception e) {
            response.append(Response.SNOOZE_FAILURE.getMessage()).append("\n");
        } finally {
            response.append(getLine());
        }
        return response.toString();
    }

    private String commandNone(String[] input) {
        return input[0] + "? I have no clue what you're talking about buddy." + "\n"
                + ">:(" + "\n"
                + getLine();
    }

    private String getLine() {
        return "______________________________________________________\n";
    }

    private String getName() {
        return " _    _\n"
                + "| |  | |                  @\n"
                + "| |__| | ____ ____ ____   _\n"
                + "|  __  |/ _  |  __|  __| | |\n"
                + "| |  | | (_| | |  | |    | |\n"
                + "|_|  |_|\\__/_|_|  |_|    |_|\n";
    }
}
