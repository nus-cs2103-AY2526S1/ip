package jimbot.command;

import java.time.LocalDateTime;

import jimbot.exception.InvalidEventException;
import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.Event;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;


public class AddEventCommand implements Command {
    private final String userInput;

    public AddEventCommand(String input) {
        this.userInput = input;
    }

    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new InvalidEventException();
        }

        String[] event = userInput.substring(6)
                .trim()
                .split("/from", 2);
        String description = event[0].trim();
        String[] timings = event[1]
                .trim()
                .split("/to", 2);
        String from = timings[0].trim();
        String to = timings[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidEventException();
        }

        LocalDateTime dateTime1 = Parser.parseDateTime(from);
        LocalDateTime dateTime2 = Parser.parseDateTime(to);
        Event userEvent = new Event(description, dateTime1, dateTime2);
        userList.addToList(userEvent);
        userStorage.update(userList);

        return user.addTask(userEvent, userList.getTaskCount() + 1);
    }
}
