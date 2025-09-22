package izayoi.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the reading and writing of a file
 */
public class FileManager {
    public static final String SAVE_DIR = "./data";
    public static final String SAVE_FILE = "./data/userdata.txt";
    private final File file;

    /**
     * Initializes a new FileManager for a specified file
     */
    public FileManager(String filepath) {
        File saveDir = new File(SAVE_DIR);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        this.file = new File(filepath);
    }

    /**
     * Reads the file if it exists
     * @return the list of lines in the file, or an empty list if it is a new file
     */
    public List<String> readFile() throws FileNotFoundException {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        Scanner scanner = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        scanner.close();
        return list;
    }

    /**
     * Writes to the file with the provided data
     */
    public void writeToFile(List<String> data) throws IOException {
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String s : data) {
            writer.write(s);
            writer.newLine();
        }
        writer.close();
    }
}
