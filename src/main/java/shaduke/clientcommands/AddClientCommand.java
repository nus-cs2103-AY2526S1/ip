package shaduke.clientcommands;

import shaduke.clients.Client;
import shaduke.clients.ClientList;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

/**
 * Represents the addition of a client.
 */
public class AddClientCommand extends ClientCommand {
    private Client client;

    public AddClientCommand(String name, String email, String phone) {
        this.client = new Client(name, email, phone);
    }

    @Override
    public void execute(TaskList tasklist, ClientList clients, Ui ui) {
        clients.add(client);
        ui.showAddClient(client, clients);
    }
}
