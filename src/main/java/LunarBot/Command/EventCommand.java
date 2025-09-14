package LunarBot.Command;

import LunarBot.Tasks.Event;
import LunarBot.TaskList;
import LunarBot.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.add(new Event(description, false, from, to));
        return ui.showMessage("Okay! I'll add this to your events!");
    }
}
