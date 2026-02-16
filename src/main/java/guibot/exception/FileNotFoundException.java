package guibot.exception;

/**
 * Exception when user tries to access a file not in the archive directory.
 */
public class FileNotFoundException extends GuibotException {
    /**
     * Creates a FileNotFoundException.
     */
    public FileNotFoundException(String[] archiveFiles) {
        super(String.format("The file specified does not exist. Here is the list of files in the archive:\n%s",
               String.join("\n", archiveFiles)));
    }
}
