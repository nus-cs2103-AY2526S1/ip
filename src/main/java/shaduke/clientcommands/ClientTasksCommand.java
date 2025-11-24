package shaduke.clientcommands;

import shaduke.clients.Client;
import shaduke.clients.ClientList;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

public class ClientTasksCommand extends ClientCommand {
    private int index;

    public ClientTasksCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasklist, ClientList clients, Ui ui) {
        Client client = clients.get(index);
        ui.showClientTasks(tasklist, client);
    }
}
