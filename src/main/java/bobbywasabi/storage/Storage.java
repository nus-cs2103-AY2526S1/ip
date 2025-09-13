package bobbywasabi.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

import bobbywasabi.client.Client;
import bobbywasabi.client.ClientList;
import bobbywasabi.exceptions.BobbyWasabiException;
import bobbywasabi.parser.Parser;
import bobbywasabi.tasks.Deadline;
import bobbywasabi.tasks.Event;
import bobbywasabi.tasks.Task;
import bobbywasabi.tasks.TaskList;
import bobbywasabi.tasks.ToDo;

/**
 * Handles loading, saving, and managing persistent storage for tasks and clients.
 * <p>
 * Provides methods to create storage files, parse stored data into objects, and
 * write updates back to the file system. Supports separate storage for tasks
 * and clients.
 */
public class Storage {
    private String taskListFilePath;
    private String folderPath;
    private String clientListFilePath;

    /** Specifies the type of storage to use in file operations. */
    public enum StorageType {
        CLIENTLIST,
        TASKLIST;
    }

    /**
     * Constructs a Storage object with specified file paths.
     *
     * @param folderPath         Path to the folder where storage files reside.
     * @param taskListFilePath   Path to the task list file.
     * @param clientListFilePath Path to the client list file.
     */
    public Storage(String folderPath, String taskListFilePath, String clientListFilePath) {
        this.folderPath = folderPath;
        this.taskListFilePath = taskListFilePath;
        this.clientListFilePath = clientListFilePath;
    }

    /**
     * Returns the file path corresponding to the given storage type.
     *
     * @param storageType The type of storage (TASKLIST or CLIENTLIST).
     * @return Path to the corresponding file as a String.
     */
    public String getFilePath(StorageType storageType) {
        switch (storageType) {
        case TASKLIST:
            return this.taskListFilePath;
        case CLIENTLIST:
            return this.clientListFilePath;
        default:
            return "";
        }
    }

    /**
     * Ensures the required data storage folder and files exist.
     * <p>
     * Creates the folder and the task/client list files if they are missing.
     *
     * @throws BobbyWasabiException if the folder or files could not be created.
     */
    public void createDataStorage() throws BobbyWasabiException {
        createFolderIfNotExists(new File(this.folderPath));

        createFileIfNotExists(new File(this.taskListFilePath), "task file");
        createFileIfNotExists(new File(this.clientListFilePath), "client file");
    }

    /**
     * Creates a folder if it does not already exist.
     *
     * @param folder the folder to check/create
     * @throws BobbyWasabiException if the folder could not be created
     */
    private void createFolderIfNotExists(File folder) throws BobbyWasabiException {
        if (!folder.exists() && !folder.mkdirs()) {
            throw new BobbyWasabiException("Could not create the folder: "
                    + folder.getAbsolutePath());
        }
    }

    /**
     * Creates a file if it does not already exist.
     *
     * @param file        the file to check/create
     * @param description a human-readable description of the file
     * @throws BobbyWasabiException if the file could not be created
     */
    private void createFileIfNotExists(File file, String description) throws BobbyWasabiException {
        if (file.exists()) {
            return;
        }

        try {
            if (!file.createNewFile()) {
                throw new BobbyWasabiException("Could not create the "
                        + description + ": " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new BobbyWasabiException(e.getMessage());
        }
    }

    /**
     * Loads tasks from the task list file.
     *
     * @return A TaskList containing all tasks stored in the file.
     * @throws BobbyWasabiException If the file cannot be found or a task cannot be parsed.
     */
    public TaskList loadTaskList() throws BobbyWasabiException {
        try {
            TaskList tasks = new TaskList();

            File file = new File(this.taskListFilePath);

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task = taskParser(scanner.nextLine());
                tasks.add(task);
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new BobbyWasabiException("Task file not found!");
        }
    }

    /**
     * Loads clients from the client list file.
     *
     * @return A ClientList containing all clients stored in the file.
     * @throws BobbyWasabiException If the file cannot be found or a client cannot be parsed.
     */
    public ClientList loadClientList() throws BobbyWasabiException {
        try {
            ClientList clients = new ClientList();

            File file = new File(this.clientListFilePath);

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Client client = clientParser(scanner.nextLine());
                clients.add(client);
            }

            return clients;
        } catch (FileNotFoundException e) {
            throw new BobbyWasabiException("Client file not found!");
        }
    }

    /**
     * Parses a line from the client file into a Client object.
     *
     * @param line String representing a client (fields separated by "|").
     * @return Client object created from the line.
     * @throws BobbyWasabiException If age is not a valid integer or line format is invalid.
     */
    public Client clientParser(String line) throws BobbyWasabiException {
        String[] infos = line.split("\\|", -1);
        assert infos.length == 5 : "Client line does not contain enough fields: " + line;

        try {
            String name = infos[0];
            String contactNumber = infos[1];
            int age = Integer.parseInt(infos[2]);
            String occupation = infos[3];
            String currentPolicies = infos[4];
            return new Client(name, contactNumber, age, occupation, currentPolicies);
        } catch (NumberFormatException e) {
            throw new BobbyWasabiException("Not a valid age!");
        }

    }

    /**
     * Parses a line from the task file into a Task object.
     *
     * @param line String representing a task (fields separated by "|").
     * @return Task object created from the line.
     * @throws BobbyWasabiException If the line cannot be parsed into a valid Task.
     */
    public Task taskParser(String line) throws BobbyWasabiException {
        String[] infos = line.split("\\|");
        assert infos.length >= 3 : "Task line does not contain enough fields: " + line;

        String type = infos[0];
        String description = infos[1];
        boolean isMarked = infos[2].equals("[X]");

        if (type.equals("T")) {
            return new ToDo(description, isMarked);
        } else if (type.equals("D")) {

            LocalDateTime dateTime = Parser.parseDateString(infos[3]);
            return new Deadline(description, isMarked, dateTime);

        } else if (type.equals("E")) {
            LocalDateTime start = Parser.parseDateString(infos[3]);
            LocalDateTime end = Parser.parseDateString(infos[4]);
            return new Event(description, isMarked, start, end);
        } else {
            return null;
        }

    }

    /**
     * Writes all tasks in the TaskList to the task file.
     *
     * @param tasks TaskList containing tasks to save.
     * @throws BobbyWasabiException If writing to the file fails.
     */
    public void updateDataFileFromTasks(TaskList tasks) throws BobbyWasabiException {
        try {
            // clear the current data file to prevent duplication when writing
            PrintWriter writer = new PrintWriter(this.taskListFilePath);
            writer.print("");
            writer.close();

            // update the fresh data file with current tasks
            for (int i = 0; i < tasks.size(); i++) {
                Task cur = tasks.get(i);
                String line = cur.getData();
                fileWrite(line, StorageType.TASKLIST);
            }

        } catch (FileNotFoundException | BobbyWasabiException e) {
            throw new BobbyWasabiException(e.getMessage());
        }

    }

    /**
     * Writes all clients in the ClientList to the client file.
     *
     * @param clients ClientList containing clients to save.
     * @throws BobbyWasabiException If writing to the file fails.
     */
    public void updateDataFileFromClients(ClientList clients) throws BobbyWasabiException {
        try {
            // clear the current data file to prevent duplication when writing
            PrintWriter writer = new PrintWriter(this.clientListFilePath);
            writer.print("");
            writer.close();

            // update the fresh data file with current tasks
            for (int i = 0; i < clients.size(); i++) {
                Client cur = clients.get(i);
                String line = cur.getData();
                fileWrite(line, StorageType.CLIENTLIST);
            }

        } catch (FileNotFoundException | BobbyWasabiException e) {
            throw new BobbyWasabiException(e.getMessage());
        }

    }

    /**
     * Writes a single line to the specified storage file.
     *
     * @param line        The line to write (cannot be null or empty).
     * @param storageType Type of storage (TASKLIST or CLIENTLIST) to write to.
     * @throws BobbyWasabiException If writing fails.
     */
    public void fileWrite(String line, StorageType storageType) throws BobbyWasabiException {
        assert line != null && !line.trim().isEmpty()
                : "Attempting to write an empty task line";
        String filePath = this.getFilePath(storageType);
        try {
            FileWriter filewriter = new FileWriter(filePath, true);
            filewriter.write(line + System.lineSeparator());
            filewriter.close();
        } catch (IOException e) {
            throw new BobbyWasabiException(e.getMessage());
        }
    }

}
