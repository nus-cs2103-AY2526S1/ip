package kane;

import java.util.ArrayList;

public class Kane {

    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for the application.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Kane(String filePath) {
        Ui ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) { // Catching a broad exception for file loading issues.
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes a single user command and returns a response string for the GUI.
     *
     * @param input The full command from the user.
     * @return A string containing the chatbot's response.
     */
    public String getResponse(String input) {
        try {
            String commandWord = Parser.getCommandWord(input);

            return switch (commandWord) {
                case "bye" -> "Goodbye. Hope to see you again soon!";
                case "list" -> listTasks();
                case "mark" -> markTask(input);
                case "unmark" -> unmarkTask(input);
                case "delete" -> deleteTask(input);
                case "todo" -> addTodo(input);
                case "deadline" -> addDeadline(input);
                case "event" -> addEvent(input);
                case "find" -> findTasks(input);
                default -> throw new KaneException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            };
        } catch (KaneException e) {
            return e.getMessage();
        }
    }

    private String listTasks() {
        StringBuilder listBuilder = new StringBuilder("Here are the tasks in your list:\n");
        formatTaskList(tasks.getTasks(), listBuilder);
        return listBuilder.toString();
    }

    private String markTask(String input) throws KaneException {
        int markIndex = Parser.parseIndex(input);
        Task markedTask = tasks.markTask(markIndex);
        storage.save(tasks.getTasks());
        return "Nice! I've marked this task as done:\n  " + markedTask;
    }

    private String unmarkTask(String input) throws KaneException {
        int unmarkIndex = Parser.parseIndex(input);
        Task unmarkedTask = tasks.unmarkTask(unmarkIndex);
        storage.save(tasks.getTasks());
        return "OK, I've marked this task as not done yet:\n  " + unmarkedTask;
    }

    private String deleteTask(String input) throws KaneException {
        int deleteIndex = Parser.parseIndex(input);
        Task deletedTask = tasks.deleteTask(deleteIndex);
        storage.save(tasks.getTasks());
        return "Noted. I've removed this task:\n  " + deletedTask
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    private String addTodo(String input) throws KaneException {
        Task newTodo = Parser.parseTodo(input);
        tasks.addTask(newTodo);
        storage.save(tasks.getTasks());
        return "Got it. I've added this task:\n  " + newTodo
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    // Used Gemini to add the try-catch block for error handling for invalid date formats
    private String addDeadline(String input) throws KaneException {
        try {
            Task newDeadline = Parser.parseDeadline(input);
            tasks.addTask(newDeadline);
            storage.save(tasks.getTasks());
            return "Got it. I've added this task:\n  " + newDeadline
                    + "\nNow you have " + tasks.getSize() + " tasks in the list.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    // Used Gemini to add the try-catch block for error handling for invalid date formats
    private String addEvent(String input) throws KaneException {
        try {
            Task newEvent = Parser.parseEvent(input);
            tasks.addTask(newEvent);
            storage.save(tasks.getTasks());
            return "Got it. I've added this task:\n  " + newEvent
                    + "\nNow you have " + tasks.getSize() + " tasks in the list.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    private String findTasks(String input) throws KaneException {
        String keyword = Parser.parseFind(input);
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            return "Sorry, I couldn't find any matching tasks in your list.";
        } else {
            StringBuilder findBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
            formatTaskList(foundTasks, findBuilder);
            return findBuilder.toString();
        }
    }

    private void formatTaskList(ArrayList<Task> taskList, StringBuilder builder) {
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        }

    }
}