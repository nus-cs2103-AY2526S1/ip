package shaduke.clientcommands;

import shaduke.clients.Client;
import shaduke.clients.ClientList;
import shaduke.tasks.Task;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

public class DeleteClientCommand extends ClientCommand{
    private int index;

    public DeleteClientCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasklist, ClientList clients, Ui ui) {
        Client client = clients.get(index);
        for (Task t : tasklist.getTasks()) {
            if (t.getClient() != null && t.getClient().equals(client)) {
                t.deleteClient();
            }
        }
        clients.delete(index);
        ui.showDeleteClient(client);
    }
}
