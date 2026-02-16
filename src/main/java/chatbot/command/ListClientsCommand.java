package chatbot.command;

import chatbot.client.ClientList;
import chatbot.ui.UI;

/**
 * ListClientsCommand implements the CommandExecutor interface and handles client list command.
 */
public class ListClientsCommand implements CommandExecutor {
    private final ClientList clientList;
    private final UI ui;

    /**
     * Constructor, initializes class constant variables.
     *
     * @param clientList List of clients the user has added; must not be null.
     * @param ui User interface where the responses to commands are displayed; must not be null.
     */
    public ListClientsCommand(ClientList clientList, UI ui) {
        assert clientList != null : "ClientList must not be null";
        assert ui != null : "UI must not be null";
        this.clientList = clientList;
        this.ui = ui;
    }

    @Override
    public String execute(String command) {
        return ui.listClientResponse(this.clientList);
    }
}
