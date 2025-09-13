package bobbywasabi.client;

import java.util.ArrayList;
import java.util.stream.IntStream;

import bobbywasabi.exceptions.BobbyWasabiException;
import bobbywasabi.parser.Parser;

/**
 * Represents a list of {@link Client} objects with utility methods for formatting and updating client data.
 * <p>
 * Provides functionality to convert clients to formatted strings and update specific client fields.
 */
public class ClientList extends ArrayList<Client> {

    /**
     * Constructs an empty ClientList.
     */
    public ClientList() {
        super();
    }

    /**
     * Converts a single client to a formatted string with its index.
     *
     * @param indx   The 1-based index of the client.
     * @param client The {@link Client} to convert.
     * @return A formatted string representing the client and its index.
     */
    public String convertClientToString(int indx, Client client) {
        return String.format("""
                %d.
                %s
                """, indx, client)
                + "\n";
    }

    /**
     * Converts all clients in the list to a single formatted string.
     *
     * @return A string containing all clients, each formatted with their index.
     */
    public String convertClientsToString() {
        StringBuilder output = new StringBuilder();

        IntStream.range(0, this.size())
                .forEach(i -> {
                    Client client = this.get(i);
                    output.append(this.convertClientToString(i + 1, client));
                });

        return output.toString();
    }

    /**
     * Updates a specific field of a client in the list.
     *
     * @param index           The zero-based index of the client in the list.
     * @param field           The field to update (must be one of: "NAME", "CONTACTNUMBER",
     *                        "AGE", "OCCUPATION", "CURRENTPOLICIES").
     * @param newFieldContent The new value to set for the specified field.
     * @throws BobbyWasabiException if the specified field is invalid.
     */
    public void updateClientField(int index, String field, String newFieldContent) throws BobbyWasabiException {
        switch (field) {
        case ("NAME"):
            this.get(index).setName(newFieldContent);
            break;
        case ("CONTACTNUMBER"):
            this.get(index).setContactNumber(newFieldContent);
            break;
        case ("AGE"):
            this.get(index).setAge(Parser.getIntegerFromString(newFieldContent));
            break;
        case ("OCCUPATION"):
            this.get(index).setOccupation(newFieldContent);
            break;
        case ("CURRENTPOLICIES"):
            this.get(index).setCurrentPolicies(newFieldContent);
            break;
        default:
            throw new BobbyWasabiException("This is not a valid field to change for client!");
        }
    }

    /**
     * Returns a string representation of all clients in the list.
     *
     * @return A formatted string listing all clients.
     */
    @Override
    public String toString() {
        return this.convertClientsToString();
    }

}
