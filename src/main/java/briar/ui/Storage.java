package briar.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import briar.exception.BriarException;

/**
 * Represents the storage used to load and save to file.
 */
public class Storage {
    private static final String SAMPLE_DATA = "T|1|read book"
            + System.lineSeparator() + "D|1|return book|2019-10-15"
            + System.lineSeparator() + "E|0|project meeting|Aug 6th 2pm|4pm"
            + System.lineSeparator() + "T|1|join sports club";

    private String fileDirectory;
    private String fileName;


    /**
     * Creates a storage instance used to load and save to file.
     */
    public Storage(String fileDirectory, String fileName) {
        this.fileDirectory = fileDirectory;
        this.fileName = fileName;
    }

    /**
     * Loads all data from a file into a string.
     * @return String of all data in the file.
     */
    public String load() throws BriarException {
        // ChatGPT was used to learn how to improve the code quality of this code.
        // ChatGPT suggested closing the scanner after use and changing mkdir to mkdirs.
        try {
            File file = new File(fileDirectory + "/" + fileName);
            Scanner scanner = new Scanner(file);
            String str = "";
            while (scanner.hasNext()) {
                str += scanner.nextLine() + "\n";
            }
            scanner.close();
            return str;
        } catch (FileNotFoundException exception) {
            File directory = new File(fileDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            write(SAMPLE_DATA);
            return load();
        }
    }

    /**
     * Writes all data from a string to a file.
     * @param textToAdd String to be written into the file.
     * @throws BriarException If unable to write to file.
     */
    public void write(String textToAdd) throws BriarException {
        try {
            FileWriter fw = new FileWriter(fileDirectory + "/" + fileName);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException exception) {
            throw new BriarException("Aww, unable to write to file! >.<");
        }
    }
}
