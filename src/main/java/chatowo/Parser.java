package chatowo;

import java.time.format.DateTimeParseException;

import chatowo.task.Deadline;
import chatowo.task.Event;
import chatowo.task.Task;
import chatowo.task.ToDo;

/**
 * Handles parsing and interpretation of user input commands.
 * Converts raw input strings into actionable commands.
 */
public class Parser {
    private Chatowo chatowo;

    public Parser(Chatowo chatowo) {
        this.chatowo = chatowo;
    }

    /**
     * Parses user input and executes corresponding commands.
     *
     * @param input Input by user
     */
    public String parse(String input) {

        String[] words = input.split(" ");

        try {
            switch (words[0]) {
            case "bye":
                return "Byebye -w-";

            case "undo":
                return chatowo.undo();

            case "list":
                return chatowo.listTasks();

            case "mark":
                return this.mark(words);

            case "unmark":
                return this.unmark(words);

            case "delete":
                return this.delete(words);

            case "todo":
                return this.addTodoTask(words, input);

            case "deadline":
                return this.addDeadlineTask(words, input);

            case "event":
                return this.addEventTask(words, input);

            case "find":
                return this.findTask(words, input);

            default:
                throw new ChatowoException("Oh nyo... I don't know... what you're saying... ;w;");
            }
        } catch (Exception e) {
            if (e instanceof DateTimeParseException) {
                return chatowo.dateTimeError();
            } else if (e instanceof IndexOutOfBoundsException) {
                return "Oopsies... Pwease specify a valid task number! >w<";
            } else {
                return e.getMessage();
            }
        }
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param words Array of input words containing task index
     * @throws ChatowoException if index is missing
     */
    public String mark(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Pwease specify a task number... >w<");
        }

        int index = Integer.parseInt(words[1]) - 1;
        return chatowo.mark(index);
    }

    /**
     * Unmarks a previously completed task.
     *
     * @param words Array of input words containing task index
     * @throws ChatowoException if index is missing
     */
    public String unmark(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Pwease specify a task number... >w<");
        }

        int index = Integer.parseInt(words[1]) - 1;
        return chatowo.unmark(index);
    }

    /**
     * Deletes specified task from task list.
     *
     * @param words Array of input words
     * @throws ChatowoException if task number is missing
     */
    public String delete(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Pwease specify a task number... >w<");
        }

        int index = Integer.parseInt(words[1]) - 1;
        return chatowo.delete(index);
    }

    /**
     * Adds a new todo task from user input.
     *
     * @param words Array of input words
     * @param input Original input string
     * @throws ChatowoException if task description is missing
     */
    public String addTodoTask(String[] words, String input) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Add a name for your todo task pwease... >w<");
        }

        String name = input.substring(5);
        return chatowo.addTask(new ToDo(name));
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param words Array of input words containing task details
     * @param input Original input string
     * @throws Exception if task format is invalid
     */
    public String addDeadlineTask(String[] words, String input) throws Exception {
        int deadlineIndex = input.lastIndexOf(" /by ");

        // No task name specified
        if (words.length <= 1 || words[1].equals("/by")) {
            throw new ChatowoException("Oopsies... Add a name for your deadline task pwease... >w<");
        }

        // No deadline date specified
        if (deadlineIndex == -1 || words[words.length - 1].equals("/by")) {
            throw new ChatowoException("Oopsies... Add a /by for your deadline task pwease... >w<");
        }

        String name = input.substring(9, deadlineIndex);
        String deadline = input.substring(deadlineIndex + 5);
        Task d = new Deadline(name, deadline);
        return chatowo.addTask(d);
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param words Array of input words containing task details
     * @param input Original input string
     * @throws Exception if task format is invalid
     */
    public String addEventTask(String[] words, String input) throws Exception {
        int fromIndex = input.lastIndexOf(" /from ");
        int toIndex = input.lastIndexOf(" /to ");

        // No task name specified
        if (words.length <= 1 || words[1].equals("/from") || words[1].equals("/to")) {
            throw new ChatowoException("Oopsies... Add a name for your event task pwease... >w<");
        }

        // No /to or /from dates specified
        if (fromIndex == -1 || toIndex == -1
                || words[words.length - 1].equals("/from")
                || words[words.length - 1].equals("/to")) {
            throw new ChatowoException("Oopsies... Add a /to and /from for your event task pwease... >w<");
        }

        String name = input.substring(6, fromIndex);
        String from = input.substring(fromIndex + 7, toIndex);
        String to = input.substring(toIndex + 5);
        Task e = new Event(name, from, to);
        return chatowo.addTask(e);
    }

    /**
     * Finds tasks containing specified phrase within task list.
     *
     * @param words Array of input words containing search details
     * @param input Original input string
     * @throws ChatowoException if task format is invalid
     */
    public String findTask(String[] words, String input) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Say what you want to find pwease... >w<");
        }

        String phrase = input.substring(5);
        return chatowo.find(phrase);
    }
}
