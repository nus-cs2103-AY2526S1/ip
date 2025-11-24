package shaduke.clientcommands;

import shaduke.ShadukeException;

import java.util.Arrays;

/**
 * Parses client-related commands and returns the corresponding {@link ClientCommand} objects.
 */
public class ClientParser {

    /**
     * Parses a client command string and returns the corresponding {@link ClientCommand}.
     *
     * @param command the full command string to parse
     * @return the parsed {@link ClientCommand}
     * @throws ShadukeException if the command is invalid or unrecognized
     */
    public static ClientCommand parse(String command) throws ShadukeException {
        String[] words = command.split(" ");
        String keyword = words[1];
        String rest = (words.length > 2) ? String.join(" ", Arrays.copyOfRange(words, 2, words.length)) : null;

        switch (keyword) {
            case "add": return parseAddClient(rest);
            case "delete": return new DeleteClientCommand(Integer.parseInt(rest));
            case "list": return new ListClientCommand();
            case "assign": return parseAssign(rest);
            case "leave": return new LeaveCommand(Integer.parseInt(rest));
            case "tasks": return new ClientTasksCommand(Integer.parseInt(rest));
            default: throw new ShadukeException("Clients are blur, need instructions");
        }
    }

    /**
     * Parses the arguments for adding a client.
     *
     * @param rest the argument string containing name, email, and phone
     * @return the {@link AddClientCommand} with parsed details
     */
    private static ClientCommand parseAddClient(String rest) {
        String[] halves = rest.split(" /email ");
        String name = halves[0];
        String[] quarters = halves[1].split(" /phone ");
        String email = quarters[0];
        String phone = quarters[1];
        return new AddClientCommand(name, email, phone);
    }

    /**
     * Parses the arguments for assigning a client to a task.
     *
     * @param rest the argument string containing task and client indices
     * @return the {@link AssignClientCommand} with parsed indices
     */
    private static ClientCommand parseAssign(String rest) {
        String[] halves = rest.split(" ");
        int taskIndex = Integer.parseInt(halves[0]);
        int clientIndex = Integer.parseInt(halves[1]);

        return new AssignClientCommand(taskIndex, clientIndex);
    }
}