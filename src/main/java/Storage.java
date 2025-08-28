/// This class handles the storage and updating of tasks in gokschat.txt
///
/// @author Ravichandran Gokul
public class Storage {
    // New fields declared
    private String filePath;

    /**
     * Constructs a new {@code Storage} object with the file path
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
}
