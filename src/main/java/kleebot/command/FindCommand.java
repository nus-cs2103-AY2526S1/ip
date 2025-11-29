package kleebot.command;

import java.util.ArrayList;
import java.util.List;

import kleebot.storage.Storage;
import kleebot.task.Task;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;


/**
 * A command to find a task that match a given pattern.
 */
public class FindCommand extends Command {
    private final String expression;


    public FindCommand(String expression) {
        this.expression = expression;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<String> tmp = new ArrayList<>();

        int i = 0;
        for (Task task: tasks.getTasks()){
            String desc = task.getDescription();
            if (desc.contains(expression)) {
                i++;
                tmp.add(i + ". " + task.toString());
            }
        }

        if (i > 0) {
            ui.showMessage("OKAY!! Klee worked really hard to find the matching tasks based on your request!! >_<");
            i = 0;
            for (String t: tmp) {
                ui.showMessage(t);
            }
        } else {
            ui.showMessage("AWWW man :( I couldn't find anything matching your request T_T");
        }
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        List<String> tmp2 = new ArrayList<>();

        int i = 0;
        for (Task task: tasks.getTasks()){
            String desc = task.getDescription();
            if (desc.contains(expression)) {
                i++;
                tmp2.add(i + ". " + task.toString());
            }
        }
        return ui.formatFind(tmp2);
    }
}
