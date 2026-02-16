package command;

import java.util.ArrayList;
import java.util.List;

import ui.Lmbd;

/**
 * Represents a command to display all tasks currently stored in the Lmbd
 * application's task list. It presents tasks as a numbered list.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("list", 0, "Displays the list of tasks");
    }

    @Override
    String run(Lmbd lmbd, String[] args) {
        List<String> lines = new ArrayList<>();
        lines.add(String.format("You have %d tasks:", lmbd.tasks.getTaskSize()));
        for (int i = 0; i < lmbd.tasks.getTaskSize(); i++) {
            lines.add(String.format("%d. %s", i + 1, lmbd.tasks.getTaskToString(i)));
        }

        return String.join("\n", lines);
    }
}
