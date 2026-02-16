package bubbles;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to create a new deadline task.
 */

public class DeadlineCommand extends Command {
    private String command;

    /**
     * Constructs a {@code DeadlineCommand} with the given command string.
     * This command creates a {@code Deadline} task when executed.
     *
     * @param command the user input for the deadline task
     */
    public DeadlineCommand (String command) {
        super(command);
        this.command = command;
    }

    /**
     * Executes the deadline command which splits the descriptions and deadline.
     * It also adds the task to the hard disk.
     *
     * @param tasks List of all the tasks.
     * @param ui The UI.
     * @param storage The list of tasks stored in the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if(!command.contains("/by")){
                throw new BubblesException("Woops! You forgot to add the deadline!");
            }
            String[] words = command.split("deadline", 2);
            Deadline deadline = new Deadline(words[1].trim());
            tasks.add(deadline);

            String body = deadline.toString().split("] ")[1];
            String description = body.split("\\(by")[0].trim();
            String rawDate = body.substring(body.indexOf("(by:") + 5, body.length() - 1).trim();

            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            LocalDateTime dateTime = LocalDateTime.parse(rawDate, inputFormat);

            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String by = dateTime.format(outputFormat);

            Storage.append("\t[D][ ] " + description + " " + "(by: " + by + ")" + "\n");

            return ui.showAdd(deadline, tasks.size());
        } catch(BubblesException e){
            return e.getMessage();
        } catch (IOException e){
            return "Error : Something went wrong!";
        }
    }
}
