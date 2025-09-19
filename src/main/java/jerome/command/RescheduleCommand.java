package jerome.command;

import java.time.format.DateTimeParseException;

import jerome.JeromeException;
import jerome.Storage;
import jerome.TaskList;
import jerome.Ui;


public class RescheduleCommand extends Command {
    private String dates;
    private int index;

    public RescheduleCommand(int index, String dates) {
        this.index = index;
        this.dates = dates;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JeromeException {
        try {
            tasks.get(index).adjustDate(dates);
            storage.save(tasks.getAll());
            return ui.showUpdated(tasks.get(index));
        }
        catch (DateTimeParseException e){
            throw new JeromeException("Please provide valid dates in this format yyyy-mm-dd");
        }
    }
}

