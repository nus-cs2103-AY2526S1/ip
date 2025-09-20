package command;

import task.Event;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;
import java.time.LocalDate;
import exception.EventException;

public class EventCommand extends Command {
    private LocalDate start;
    private LocalDate end;

    public EventCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) throws Exception {
        try {
            Event td = new Event(input.substring(6, input.indexOf("/from") - 1),
                    input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1),
                    input.substring(input.indexOf("/to") + 4));
            t.add(td);
            u.chatbotPrint("Got it. I've added this task:\n      " + td);
            u.chatbotPrint("Now you have " + t.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new EventException("To create a new event item, the command is: event /from [start (yyyy-mm-dd)] /to [end (yyyy-mm-dd)]");
        }

        s.saveToFile(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
