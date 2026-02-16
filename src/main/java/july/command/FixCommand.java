package july.command;

import java.util.ArrayList;

import july.data.Storage;
import july.error.JulyException;
import july.tasks.Fix;
import july.tasks.Task;
import july.ui.Ui;

public class FixCommand extends Command {
    private final String argument;

    public FixCommand(String argument) {
        super();
        this.argument = argument;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws JulyException {
        try {
            Fix fix = Fix.process(argument);
            tasks.add(fix);
            storage.save(tasks);

            addResponses("Got it. I've added this event:",
                    "  " + fix.toString(),
                    String.format("Now you have %d %s in the list.",
                            tasks.size(), tasks.size() == 1 ? "task" : "tasks")
            );
        } catch (JulyException e) {
            throw new JulyException(e.getMessage());
        }
    }
}
