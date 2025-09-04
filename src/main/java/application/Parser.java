package application;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import exception.InvalidIndexException;
import exception.RomidasException;
import tasks.DeadlineTask;
import tasks.Event;
import tasks.Task;
import tasks.TodoTask;

/**
 *Takes in user input that has been collected by ui and processes it to select the appropriate
 * commands to take
 */
public class Parser {

    /**
     * Parses user input to decide which command should be used
     *
     * @param input
     * @param taskList
     * @param ui
     * @param storage
     * @param dataPath
     * @return Specific command to execute
     * @throws RomidasException
     */
    public static Command parse(String input, TaskList taskList, Ui ui, Storage storage, 
            String dataPath) throws RomidasException {
        String[] words = input.trim().split("\\s+");
        String cmdWord = words[0].toUpperCase();
        
        try {
            Command.CommandType cmd = Command.CommandType.valueOf(cmdWord);
            
            switch (cmd) {
            case LIST:
                return new ListCommand();

            case MARK:
                if (words.length != 2) {
                    throw new RomidasException("Should follow the format: mark <tasks.Task Number>");
                }
                int indexMark = Integer.parseInt(words[1]) - 1;
                if (indexMark < 0 || indexMark >= taskList.size()) {
                    throw new InvalidIndexException();
                }
                return new MarkCommand(indexMark, true);

            case UNMARK:
                if (words.length != 2) {
                    throw new RomidasException("Should follow the format: unmark <tasks.Task Number>");
                }
                int indexUnmark = Integer.parseInt(words[1]) - 1;
                if (indexUnmark < 0 || indexUnmark >= taskList.size()) {
                    throw new InvalidIndexException();
                }
                return new MarkCommand(indexUnmark, false);

            case TODO:
                if (words.length < 2) {
                    throw new RomidasException("todo requires a description");
                }
                if (input.length() < 6) {
                    throw new RomidasException("todo requires a description");
                }
                String description = input.trim().substring(5);
                return new AddCommand(new TodoTask(description));

            case DEADLINE:
                if (words.length < 2) {
                    throw new RomidasException("deadline tasks should follow the format: "
                            + "deadline <task> /by <date/time>");
                }
                if (input.length() <= 9) {
                    throw new RomidasException("deadline tasks should follow the format: "
                            + "deadline <task> /by <date/time>");
                }
                String subDeadline = input.substring(9);
                String[] partsDeadline = subDeadline.split(" /by ");
                if (partsDeadline.length < 2 || partsDeadline[0].isBlank() 
                        || partsDeadline[1].isBlank()) {
                    throw new RomidasException("deadline tasks should follow the format: "
                            + "deadline <task> /by <date/time>");
                }
                DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
                try {
                    LocalDate.parse(partsDeadline[1], fmt);
                } catch (DateTimeParseException e) {
                    throw new RomidasException("deadline should follow the format: yyyy-MM-dd");
                }
                Task deadlineTask = new DeadlineTask(partsDeadline[0], partsDeadline[1]);
                return new AddCommand(deadlineTask);

            case EVENT:
                if (words.length < 2) {
                    throw new RomidasException("event tasks should follow the format: "
                            + "event <event name> /from <date/time> /to <date/time>");
                }
                if (input.length() <= 6) {
                    throw new RomidasException("event tasks should follow the format: "
                            + "event <event name> /from <date/time> /to <date/time>");
                }
                String subEvent = input.substring(6);
                String[] partsEvent = subEvent.split(" /from ");
                if (partsEvent.length < 2 || partsEvent[0].isBlank()) {
                    throw new RomidasException("event tasks should follow the format: "
                            + "event <event name> /from <date/time> /to <date/time>");
                }
                String fromAndTo = partsEvent[1];
                String[] timeParts = fromAndTo.split(" /to ");
                if (timeParts.length < 2 || timeParts[0].isBlank() || timeParts[1].isBlank()) {
                    throw new RomidasException("event tasks should follow the format: "
                            + "event <event name> /from <date/time> /to <date/time>");
                }
                Task eventTask = new Event(partsEvent[0], timeParts[0], timeParts[1]);
                return new AddCommand(eventTask);

            case DELETE:
                if (words.length < 2) {
                    throw new RomidasException("Should follow the format: delete <tasks.Task Number>");
                }
                int index = Integer.parseInt(words[1]) - 1;
                if (index < 0 || index >= taskList.size()) {
                    throw new InvalidIndexException();
                }
                return new DeleteCommand(index);

            case FIND:
                if (words.length < 2) {
                    throw new RomidasException("find requires a keyword");
                }
                String keyword = input.trim().substring(5); // Remove "find " prefix
                return new FindCommand(keyword);

            case BYE:
                return new ExitCommand();

            default:
                throw new RomidasException("I'm sorry, I don't recognise that command. "
                        + "Try one of list, event, todo, deadline, mark, unmark, delete, find");
            }
        } catch (IllegalArgumentException e) {
            throw new RomidasException("I'm sorry, I don't recognise that command. "
                    + "Try one of list, event, todo, deadline, mark, unmark, delete, find");
        }
    }
}
