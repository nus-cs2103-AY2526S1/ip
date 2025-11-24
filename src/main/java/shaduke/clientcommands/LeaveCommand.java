package shaduke.clientcommands;

import shaduke.clients.Client;
import shaduke.clients.ClientList;
import shaduke.tasks.Task;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

public class LeaveCommand extends ClientCommand {
    private int taskIndex;

    public LeaveCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList tasklist, ClientList clients, Ui ui) {
        Task task = tasklist.get(taskIndex);
        Client client = task.getClient();
        ui.showLeave(taskIndex, client);
        task.deleteClient();
    }
}
