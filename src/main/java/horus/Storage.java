package horus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * Class that represents a file with methods to read and write said file
 */
public class Storage {
    private final File file;
    private final boolean isNewFile;

    /**
     * Creates an object representing the file at the input file path
     * Creates directories if directories in file path does not exist
     * Creates file if file does not exist
     *
     * @param filePath A string representing the file path to be initialized
     * @throws IOException If file path cannot be accessed by the program
     */
    public Storage(String filePath) throws IOException {
        String directoryPath = filePath.substring(0, filePath.lastIndexOf('/'));

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); //Create relevant directories if they don't already exist
        }

        file = new File(filePath);
        isNewFile = file.createNewFile();
    }

    /**
     * Checks if file represented by Storage was newly created
     *
     * @return true if file was newly created and false otherwise
     */
    public boolean checkIsNew() {
        return isNewFile;
    }

    /**
     * Writes the contents of fileContents into this.file
     *
     * @param fileContents An array of strings with each string representing a line to be written
     * @return String to be printed by Ui
     */
    public String writeToFile(String[] fileContents) {
        try {
            FileWriter taskWriter = new FileWriter(file);
            for (int i = 0; i < fileContents.length; i++) {
                taskWriter.write(fileContents[i] + "\n");
            }
            taskWriter.close();
        } catch (IOException e) {
//            return "Error: Unable to access local file, tasks not saved.";
            return "Error: The archive denies us, your sacred duties lie unsaved in the void.";
        }

//        return "Task list saved.";
        return "By the Emperor’s will, the task litany is secured within the Chapter’s vaults.";
    }

    /**
     * Reads and returns the contents of this.file.
     *
     * @return An array of strings with each string representing a line in the file.
     * @throws IOException if file cannot be accessed.
     */
    public String[] readFile() throws IOException {
        Scanner scanner = new Scanner(file);

        //Counts number of lines in the file
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            lineCount++;
        }

        String[] fileContents = new String[lineCount];

        //Resetting the line the scanner is at
        scanner.close();
        scanner = new Scanner(file);

        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            String taskData = scanner.nextLine();
            fileContents[lineNumber] = taskData;
            lineNumber++;
        }

        scanner.close();
        return fileContents;
    }
}
