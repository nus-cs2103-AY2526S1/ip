package chatbot.command;

import java.io.IOException;

import chatbot.client.Client;
import chatbot.client.ClientList;
import chatbot.exception.BotException;
import chatbot.storage.ClientStorage;
import chatbot.ui.UI;

/**
 * AdcClientCommand implements the CommandExecutor Interface and handles client add command
 */
public class AddClientCommand implements CommandExecutor {
    private final ClientList clientList;
    private final UI ui;
    private final ClientStorage clientStorage;

    /**
     * Constructor, initializes class variables.
     *
     * @param clientList List of clients the user has added; must not be null;
     * @param ui User interface where the responses to commands are displayed; must not be null.
     * @param clientStorage Persistent storage for user's clients.
     */
    public AddClientCommand(ClientList clientList, UI ui, ClientStorage clientStorage) {
        this.clientList = clientList;
        assert clientList != null : "TaskList must not be null";
        assert ui != null : "UI must not be null";
        this.ui = ui;
        // Storage might be null (problem is handled later in the code), no assertions needed
        this.clientStorage = clientStorage;
    }

    @Override
    public String execute(String addCommand) throws BotException {
        Client newClient = this.clientList.addClient(addCommand);
        String response;

        if (this.clientStorage == null) {
            return "I can't find my storage so I basically forgot what you just said";
        }

        try {
            clientStorage.updateStorage(clientList.getAllClients());
        } catch (IOException e) {
            response = "I didn't quite catch that, less work for me I guess";
            return response;
        }

        response = ui.addClientResponse(newClient);
        return response;
    }
}
