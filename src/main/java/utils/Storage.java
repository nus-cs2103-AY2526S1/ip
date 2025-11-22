package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.ApunableException;
import models.Savable;

/**
 * A util that handles storing and loading savables from and to a provided {@code filePath}. 
 */
public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Saves the savable into file.
     * 
     * @param savable savable(usually list of certain items) added by users. 
     */
    public void save(Savable savable) throws ApunableException {
        File dataFile = new File(FILE_PATH);

        // Ensure parent directories exist
        File parentDirectory = dataFile.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            boolean isDirCreated = parentDirectory.mkdirs();
            if (!isDirCreated) {
                throw new ApunableException("Failed to create parent directories for file: " + FILE_PATH);
            }
        }

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new ApunableException("Failed to create new file");
        }

        try {
            FileWriter fw = new FileWriter(dataFile);
            
            fw.write(savable.getFormattedString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    /**
     * Loads the Strings from {@code FILE_PATH} specified.
     */
    public ArrayList<String> load() throws ApunableException {
        ArrayList<String> lines = new ArrayList<>();

        File dataFile = new File(FILE_PATH);
        Scanner sc;
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new ApunableException("data file does not exist");
        }
        
        while (sc.hasNext()) {
            lines.add(sc.nextLine());
        }
    
        sc.close();

        return lines;
    }
}
