package bubbles;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to create a new event task.
 */
public class EventCommand extends Command{
    private String command;
    private String description;

    /**
     * Constructs a {@code EventCommand} with the given command string.
     * This command creates a {@code Event} task when executed.
     *
     * @param command the user input for the event task
     */
    public EventCommand (String command) {
        super(command);
        this.command = command;
    }



    /**
     * Executes the event command which splits the descriptions and from and to date/time.
     * It also adds the task to the hard disk.
     *
     * @param tasks List of all the tasks.
     * @param ui The UI.
     * @param storage The list of tasks stored in the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] words = command.split("event", 2);
            Event event = new Event(words[1].trim());
            tasks.add(event);

            String body = event.toString().split("] ")[1];
            this.description = body.split("\\(from")[0].trim();
            String rawFrom = body.split("\\(from: ")[1].trim().split("to:")[0].trim();
            String rawTo = body.substring(body.indexOf("to:") + 4, body.length() - 1).trim();

            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            LocalDateTime fromTime = LocalDateTime.parse(rawFrom, inputFormat);
            LocalDateTime toTime = LocalDateTime.parse(rawTo, inputFormat);

            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String from = fromTime.format(outputFormat);
            String to = toTime.format(outputFormat);

            Storage.append("\t[E][ ] " + description + " " + "(from: " + from + " to: " + to+ ")" + "\n");

            return ui.showAdd(event, tasks.size());

        } catch (IOException e){
            return "Error : Something went wrong!";
        } catch (BubblesException e){
            return e.getMessage();
        }
    }

    public String getDescription(){
        return description;
    }
}
