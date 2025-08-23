package BobbyWasabi.Storage;

import BobbyWasabi.Exceptions.BobbyWasabiException;
import BobbyWasabi.Parser.Parser;
import BobbyWasabi.Tasks.*;
import BobbyWasabi.Tasks.ToDo;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private String folderpath;

    public Storage(String filepath, String folderpath) {
        this.filepath = filepath;
        this.folderpath = folderpath;
    }

    /**
     * Checks if the data folders and file used to store the tasks for the list exists
     * If they do not, it will create them
     * End result is ./data/BobbyWasabiTasks.txt created
     */
    public void createDataStorage() throws BobbyWasabiException {
        File folder = new File(this.folderpath);
        File file = new File(this.filepath);

        // check if folder exists
        if (!folder.exists()) {
            // create the folder if it does not exist
            if (!folder.mkdirs()) {
                throw new BobbyWasabiException("Could not create the folder ./data!");
            }
        }

        // check if the file exists
        if (!file.exists()) {
            // create the file if it does not exist
            try {
                if (!file.createNewFile()) {
                    throw new BobbyWasabiException("Could not create the file!");
                }
            } catch (IOException e) {
                throw new BobbyWasabiException("Could not create the file!");
            }
        }

    }


    /**
     * This function reads from the data file BobbyWasabiTasks.txt
     * It creates a task array by adding all the tasks in the data file
     *
     *
     * @return ArrayList of tasks loaded from the datafile
     * @throws BobbyWasabiException
     */
    public ArrayList<Task> load() throws BobbyWasabiException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            File file = new File(this.filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task = taskParser(scanner.nextLine());
                tasks.add(task);
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new BobbyWasabiException("File not found!");
        }
    }

    /**
     * Parses a string line which is intended to be from the BobbyWasabiTasks.txt file
     * Return a task created from information parsed from the line
     *
     * @param line String line
     * @return BobbyWasabi.BobbyWasabi.Task created from parsed string
     */
    public Task taskParser(String line) throws BobbyWasabiException {
        String[] infos = line.split("\\|");

        String type = infos[0];
        String description = infos[1];
        boolean isMarked = infos[2].equals("[X]");


        if (type.equals("T")) {
            return new ToDo(description, isMarked);
        } else if (type.equals("D")) {

            LocalDateTime dateTime = Parser.parseDateString(infos[3]);
            return new Deadline(description, isMarked, dateTime);

        } else if (type.equals("E")) {
            return new Event(description, isMarked, infos[3], infos[4]);
        }

        return null;
    }

    /**
     * Given an arraylist of tasks, this would:
     * Clear the current data file
     * Update the fresh file with all the tasks in accordance with the tasks given as arg
     *
     * @param tasks Arraylist of tasks to be reflected into data file
     */
    public void updateDataFileFromTasks(TaskList tasks) throws BobbyWasabiException {
        try {
            // clear the current data file
            PrintWriter writer = new PrintWriter(this.filepath);
            writer.print("");
            writer.close();

            // update the fresh data file with current tasks
            for (int i = 0; i < tasks.size(); i++) {
                Task cur = tasks.get(i);
                String line = cur.getData();
                fileWrite(line);
            }

        } catch (FileNotFoundException | BobbyWasabiException e) {
            throw new BobbyWasabiException(e.getMessage());
        }

    }

    /**
     * Given a string line, writes that line to the database file
     *
     * @param line String line to be written
     */
    public void fileWrite(String line) throws BobbyWasabiException {
        try {
            FileWriter filewriter = new FileWriter(this.filepath);
            filewriter.write(line);
            filewriter.close();
        } catch (IOException e) {
            throw new BobbyWasabiException(e.getMessage());
        }
    }

}
