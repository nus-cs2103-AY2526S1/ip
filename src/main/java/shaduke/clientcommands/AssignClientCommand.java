package shaduke.clientcommands;

import shaduke.clients.Client;
import shaduke.clients.ClientList;
import shaduke.tasks.Task;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

public class AssignClientCommand extends ClientCommand {
    int taskIndex;
    int clientIndex;

    public AssignClientCommand(int taskIndex, int clientIndex) {
        this.taskIndex = taskIndex - 1;
        this.clientIndex = clientIndex - 1;
    }

    @Override
    public void execute(TaskList tasklist, ClientList clients, Ui ui) {
        Client client = clients.get(clientIndex);
        Task task = tasklist.get(taskIndex);
        ui.showAssignClient(task, client);
        task.addClient(client);
    }
}
