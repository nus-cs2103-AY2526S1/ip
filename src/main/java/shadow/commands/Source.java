package shadow.commands;

import java.io.IOException;

import shadow.storage.Storage;

/**
 * Represents a command that sets a source file path for the application's storage.
 * This command allows the user to designate either a temporary or default file path
 * for task storage. The file path can either be explicitly provided or set to a default value.
 */
public class Source extends Command {

    private final String filePath;

    private Source(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Executes the command to set the source file path for the application's storage.
     * If the file path is "default", it resets the source path to the predefined default value.
     * Otherwise, it attempts to set a temporary source file path based on the provided input.
     *
     * @return a message indicating whether the default file path was set or if the temporary
     *         file path was successfully updated, including the specific file path if applicable
     * @throws IllegalArgumentException if an error occurs while reading from the specified file
     */
    @Override
    public String execute() {
        if (filePath.equalsIgnoreCase("default")) {
            Storage.getInstance().setDefaultSourcePath();
            return "Default file path set";
        }

        try {
            Storage.getInstance().setTemporarySourcePath(filePath);
            return "Temporary file path set to " + filePath;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading from file: " + e.getMessage());
        }
    }

    /**
     * Factory method to create a new Source command from the given input parts.
     * Expects the first element of the parts array to be the literal "source", and
     * the second element to be the file path to be used.
     *
     * @param parts an array of strings containing the command elements;
     *              parts[0] should be "source", and parts[1] should be the file path
     * @return a new Source instance initialized with the provided file path
     * @throws IllegalArgumentException if the input format is invalid (e.g., parts length is not 2)
     * @throws AssertionError if parts[0] do not equal "source"
     */
    public static Source of(String[] parts) {
        assert(parts[0].equals("source"));
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: source <file path>");
        }
        return new Source(parts[1]);
    }


}
