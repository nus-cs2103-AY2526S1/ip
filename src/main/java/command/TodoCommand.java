package command;

import exception.TodoException;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import task.ToDo;

public class TodoCommand extends Command {
    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList t, Ui u, Storage s) throws Exception {
        try {
            if (input.substring(5).isEmpty()) throw new Exception();
            ToDo td = new ToDo(input.substring(5));
            t.add(td);
            u.chatbotPrint("Got it. I've added this task:\n      " + td);
            u.chatbotPrint("Now you have " + t.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new TodoException("To create a new to-do item, the command is: todo [name of to-do]");
        }

        s.saveToFile(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
