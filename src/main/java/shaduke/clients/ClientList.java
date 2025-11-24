package shaduke.clients;

import java.util.ArrayList;

/**
 * Represents a list of clients.
 */
public class ClientList {
    private ArrayList<Client> clients;

    public ClientList() {
        this.clients = new ArrayList<>();
    }

    public void add(Client client) {
        clients.add(client);
    }

    public void delete(int index) {
        clients.remove(index);
    }

    public int size() {
        return clients.size();
    }

    public Client get(int index) {
        return clients.get(index);
    }
}