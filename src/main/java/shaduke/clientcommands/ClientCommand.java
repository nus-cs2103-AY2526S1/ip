package shaduke.clientcommands;

import shaduke.ShadukeException;
import shaduke.Storage;
import shaduke.clients.ClientList;
import shaduke.tasks.TaskList;
import shaduke.ui.GuiUi;
import shaduke.ui.Ui;

public abstract class ClientCommand {
    public abstract void execute(TaskList tasklist, ClientList clients, Ui ui);

    public String executeAndReturn(TaskList tasks, ClientList clients) throws ShadukeException {
        GuiUi tempUi = new GuiUi(); // e.g. UI that buffers instead of printing
        this.execute(tasks, clients, tempUi);
        return tempUi.getOutput();
    }
}
