package chatbot.command;

import java.io.IOException;

import chatbot.client.Client;
import chatbot.client.ClientList;
import chatbot.exception.BotException;
import chatbot.storage.ClientStorage;
import chatbot.ui.UI;

/**
 * UpdateClientCommand implements the CommandExecutor interface and handles client update command.
 */
public class UpdateClientCommand implements CommandExecutor {
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
    public UpdateClientCommand(ClientList clientList, UI ui, ClientStorage clientStorage) {
        this.clientList = clientList;
        assert clientList != null : "TaskList must not be null";
        assert ui != null : "UI must not be null";
        this.ui = ui;
        // Storage might be null (problem is handled later in the code), no assertions needed
        this.clientStorage = clientStorage;
    }

    @Override
    public String execute(String taskDescription) throws BotException {
        // Expect format: <clientId> <prefix1> <value1> [<prefix2> <value2> ...]
        String[] parts = taskDescription.trim().split("\\s+");
        if (parts.length < 3 || parts.length % 2 == 0) {
            throw new BotException(
                    "Usage: client update <clientId> [/m <newMobile>] [/d <newDate>] (you can use both prefixes)"
            );
        }

        int clientId;
        try {
            clientId = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new BotException("Client ID must be a number.");
        }

        Client client = clientList.getClient(clientId); // we’ll return this client in the response
        boolean updated = false;

        for (int i = 1; i < parts.length; i += 2) {
            String prefix = parts[i];
            String value = parts[i + 1];

            switch (prefix.toLowerCase()) {
            case "/m":
                updated |= clientList.updateMobileNumber(clientId, value);
                break;
            case "/d":
                updated |= clientList.updateLastContactedDate(clientId, value);
                break;
            default:
                throw new BotException(
                        "Unknown prefix: " + prefix + ". Use /m for mobile, /d for date."
                );
            }
        }

        if (this.clientStorage == null) {
            return "I can't find my storage so I basically forgot what you just said";
        }

        try {
            clientStorage.updateStorage(clientList.getAllClients());
        } catch (IOException e) {
            return "I didn't quite catch that, less work for me I guess";
        }

        return updated
                ? ui.updateClientResponse(client)
                : ui.noClientUpdateResponse();
    }
}
