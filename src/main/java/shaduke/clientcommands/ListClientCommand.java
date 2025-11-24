package shaduke.clientcommands;

import shaduke.clients.ClientList;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

public class ListClientCommand extends ClientCommand{
    @Override
    public void execute(TaskList tasklist, ClientList clients, Ui ui) {
        ui.showClients(clients);
    }
}
