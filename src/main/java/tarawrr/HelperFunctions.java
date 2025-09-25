package tarawrr;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class of helper functions used across other classes
 */
public class HelperFunctions {

    /**
     * Removes the first word and first space or an empty string if no space is found
     * @param string
     * @return string without first word and space
     */
    public static String removeFirstWord(String string) {
        int i = string.indexOf(" ");
        if (i != -1) {
            return string.substring(i + 1).trim();  // Trim to handle any extra spaces
        }
        return "";  // Return empty string if no space is found
    }

    /**
     * Uses a FileWriter object to write to a file
     * @param filePath
     * @param textToAdd
     * @throws IOException
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true); // append mode
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes a task into a file
     * @param filePath
     * @param task
     */
    private static void addToData(String filePath, Task task) {
        try {
            writeToFile(filePath, task.toStorageString() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
