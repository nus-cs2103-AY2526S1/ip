package LunarBot.Command;

import LunarBot.Tasks.Deadline;
import LunarBot.TaskList;
import LunarBot.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.add(new Deadline(description, false, by));
        return ui.showMessage("Okay! I'll add this to your deadlines!");
    }
}
