package jamal.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Storage to store filepath to data file for task data
 */
public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if data file exists through filePath
     * Create new datafile with filepath if file path does not exist
     * Ensure folder directory is maintained for data
     * Reads all lines from data file of filepath and organise into a list
     *
     * @return List of task data strings
     */
    public List<String> load() { //give a list of task strings
        File dataFile = new File(filePath);
        if (!dataFile.exists()) { //does not yet exist, then create
            createDataFile(dataFile);
        }
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Unable to read File\n");
        }
        return List.of(); //return empty list
    }

    /**
     * Creates a datafile with parent directory following file path
     *
     * @param dataFile
     */
    public void createDataFile(File dataFile) {
        File parentDir = dataFile.getParentFile();
        if (parentDir != null) { //guard clause for no parent directory for data file
            parentDir.mkdirs(); //create the parent directories
        }
        try {
            if (dataFile.createNewFile()) { //attempt to create file, true if successful
                System.out.print("Data file created\n");
            } else {
                System.err.print("Data file already exists or cannot be created\n");
            }
        } catch (IOException e) {
            System.err.print("Unable to create data file\n");
        }
    }

    /**
     * Calls on FileWrite to mark line
     */
    public void markLine(int idx) {
        assert idx >= 0 : "idx must be greater than or equal to 0";
        FileWrite.markLine(this.filePath, true, idx);
    }

    /**
     * Calls on FileWrite to unmark line
     */
    public void unmarkLine(int idx) {
        assert idx >= 0 : "idx must be greater than or equal to 0";
        FileWrite.markLine(this.filePath, false, idx);
    }

    /**
     * Calls on FileWrite to prioritize line
     */
    public void prioritizeLine(int idx, int priority) {
        assert idx >= 0 : "idx must be greater than or equal to 0";
        FileWrite.prioritizeLine(this.filePath, String.valueOf(priority), idx);
    }

    /**
     * Calls on FileWrite to delete line
     */
    public void deleteLine(int idx) {
        assert idx >= 0 : "idx must be greater than or equal to 0";
        FileWrite.deleteLine(this.filePath, idx);
    }

    /**
     * Calls on FileWrite to add line
     */
    public void addLine(String line) {
        FileWrite.addLine(this.filePath, line);
    }
}
