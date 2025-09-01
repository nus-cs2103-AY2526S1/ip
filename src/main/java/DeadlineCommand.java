import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showMessage("Okay! I'll add this to your deadlines!");
        taskList.add(new Deadline(description, false, by));
    }
}
