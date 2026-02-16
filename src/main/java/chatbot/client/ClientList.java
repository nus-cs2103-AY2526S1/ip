package chatbot.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chatbot.exception.BotException;
import chatbot.exception.InvalidArgumentException;
import chatbot.exception.ListIndexOutOfBoundException;

/**
 * ClientList handles temporary storage of clients the user added.
 * It handles client addition, deletion, updating mobile number,
 * updating last contacted date, storing and loading Client from ClientStorage.
 */
public class ClientList {
    private final List<Client> myClients = new ArrayList<>();

    /**
     * Returns a Client given its ID (1-based index).
     */
    public Client getClient(int clientId) {
        assert clientId > 0 && clientId <= myClients.size() : "Client ID out of bounds";
        int clientIdx = clientId - 1;
        return myClients.get(clientIdx);
    }

    public List<Client> getAllClients() {
        System.out.println("MEEEEE" + this.myClients + "\n");
        return this.myClients;
    }

    /**
     * Adds a new Client.
     *
     * @param addCommand add client command inputs
     * @return The newly added Client.
     */
    public Client addClient(String addCommand) throws BotException {
        String[] input = addCommand.split(" ");

        if (input.length != 3) {
            throw new InvalidArgumentException("Usage: client add <name> <mobile number> <last contacted date>\n");
        }

        String name = input[0].strip();
        String mobileNumber = input[1].strip();
        String lastContactedDate = input[2].strip();

        Client newClient = new Client(name, mobileNumber, lastContactedDate);
        myClients.add(newClient);

        return newClient;
    }

    /**
     * Deletes a client given its ID.
     *
     * @param clientId target client ID.
     * @return The deleted Client.
     * @throws BotException If the task is empty or the taskId is invalid.
     */
    public Client deleteClient(String clientId) throws BotException {
        int clientIdInteger = Integer.parseInt(clientId.trim());
        int clientIdx = clientIdInteger - 1;
        if (myClients.isEmpty()) {
            throw new ListIndexOutOfBoundException("Your list is literally empty\n");
        }

        if (clientIdInteger > myClients.size()) {
            throw new InvalidArgumentException("That client don't exist, do you even know what you added??\n");
        } else if (clientIdInteger < 1) {
            throw new InvalidArgumentException("Are you drunk? Client " + clientId + "?\n");
        } else {
            return myClients.remove(clientIdx);
        }
    }

    /**
     * Returns a formatted string representation of all clients in the client list.
     *
     * @return A string that lists all clients with their indices (1-indexed).
     */
    public String showClientList() {
        String clients = IntStream
                .range(0, myClients.size())
                .mapToObj(i -> (i + 1) + ". " + myClients.get(i))
                .collect(Collectors.joining("\n"));

        if (clients.isEmpty()) {
            return "You have no clients in your list.";
        }

        return "Here are your clients:\n" + clients;
    }

    /**
     * Updates the mobile number of a client.
     *
     * @param clientId ID of the client.
     * @param newMobileNumber New mobile number.
     * @return True if update was successful.
     */
    public boolean updateMobileNumber(int clientId, String newMobileNumber) throws BotException {
        if (clientId < 1 || clientId > myClients.size()) {
            throw new ListIndexOutOfBoundException(
                    "Invalid client ID: " + clientId + ". Valid range is 1 to " + myClients.size() + ".\n"
            );
        }

        Client client = getClient(clientId);
        return client.updateMobileNumber(newMobileNumber);
    }

    /**
     * Updates the last contacted date of a client.
     *
     * @param clientId ID of the client.
     * @param newLastContactedDate New last contacted date.
     * @return True if update was successful.
     */
    public boolean updateLastContactedDate(int clientId, String newLastContactedDate) throws BotException {
        if (clientId < 1 || clientId > myClients.size()) {
            throw new ListIndexOutOfBoundException(
                    "Invalid client ID: " + clientId + ". Valid range is 1 to " + myClients.size() + ".\n"
            );
        }

        Client client = getClient(clientId);
        return client.updateLastContactedDate(newLastContactedDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Client List:\n");
        for (int i = 0; i < myClients.size(); i++) {
            sb.append(i + 1).append(". ").append(myClients.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Load clients from Storage into a ClientList instance.
     *
     * @param file txt file that stores user tasks.
     * @throws IOException If data is corrupted.
     */
    public void loadClients(File file) throws IOException {
        if (!file.exists()) {
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Client client = parseClientFromStorage(line);
                myClients.add(client);
            }
        } catch (BotException e) {
            System.out.println(e.getMessage());
        }
    }

    private Client parseClientFromStorage(String line) throws BotException {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new BotException("Corrupted task in file: " + line);
        }

        String name = parts[0].strip();
        String mobileNumber = parts[1].strip();
        String lastContactedDate = parts[2].strip();

        return new Client(name, mobileNumber, lastContactedDate);
    }
}
