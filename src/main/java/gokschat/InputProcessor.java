package gokschat;

import gokschat.commands.AddCommand;
import gokschat.commands.Command;
import gokschat.commands.DeleteCommand;
import gokschat.commands.DisplayCommand;
import gokschat.commands.FindCommand;
import gokschat.commands.MarkCommand;
import gokschat.commands.UnmarkCommand;

import gokschat.exceptions.DeadlineException;
import gokschat.exceptions.InvalidPromptException;
import gokschat.exceptions.TodoException;

import gokschat.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

/// The class processes user inputs
///
/// @author Ravichandran Gokul
public class InputProcessor {
    // Declare fields
    private List<Task> listOfTasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a new {@code gokschat.InputProcessor} object with the task list, the UI object and storage.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param ui
     * @param storage
     * @param listOfTasks
     */
    public InputProcessor(Ui ui, Storage storage, List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Checks if a string is a valid date in the "yyyy-MM-dd" format.
     * This method is strict and validates the date's existence (e.g., leap years).
     *
     * @param  deadline The string to validate.
     * @return boolean  If the string is a valid date in the specified format, return true; return false otherwise.
     */
    public static boolean isValidDate(String deadline) {
        // Use ResolverStyle.STRICT to ensure dates like "2025-02-29" are rejected.
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(deadline, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Processes the input of the user and returns a gokschat.commands.Command object.
     *
     * @param input
     * @return Command
     * @throws InvalidPromptException
     * @throws TodoException
     */
    public Command processInput(String input) throws InvalidPromptException, TodoException, DeadlineException {
        String[] words = input.split(" ");
        int firstSpaceIndex = input.indexOf(" ");
        String restOfinput = input.substring(firstSpaceIndex + 1);

        if (input.equals("list")) {
            return new DisplayCommand(this.listOfTasks, this.ui);
        } else if (words[0].equals("find")) {
            String keyword = words[1];
            return new FindCommand(keyword, this.listOfTasks, this.ui);
        } else if (words[0].equals("mark")) {
            int index = Integer.parseInt(words[1]);
            return new MarkCommand(index, this.listOfTasks, this.ui);
        } else if (words[0].equals("unmark")) {
            int index = Integer.parseInt(words[1]);
            return new UnmarkCommand(index, this.listOfTasks, this.ui);
        } else if (words[0].equals("delete")) {
            int index = Integer.parseInt(words[1]);
            return new DeleteCommand(index, this.listOfTasks, this.ui);
        } else if (words[0].equals("todo") || words[0].equals("deadline") || words[0].equals("event")) {
            if (words[0].equals("todo") && words.length == 1) {
                throw new TodoException("      YIKES!!! You need to enter a description for a task!!!");
            } else if (words[0].equals("deadline")) {
                int firstSlashIndex = restOfinput.indexOf("/");
                char c = restOfinput.charAt(firstSlashIndex);
                assert c == '/' : "c should be '/'";
                String deadline = restOfinput.substring(firstSlashIndex + 4);
                if (!isValidDate(deadline)) {
                    throw new DeadlineException("      Oh NO! This date is either invalid or incorrectly formatted!");
                }
            }
            return new AddCommand(this.listOfTasks, this.ui, restOfinput, words[0]);
        } else {
            throw new InvalidPromptException("     YIKES!!! I do not quite understand what you just said :(");
        }
    }
}
