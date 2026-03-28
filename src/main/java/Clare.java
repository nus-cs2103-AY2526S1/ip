
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import clare.exception.StringConvertExceptions;
import clare.parser.Parser;
import clare.storage.Storage;
import clare.task.TaskList;
import clare.ui.UI;

/**
 * Represents the main class of the application
 */
public class Clare {
    private static final Scanner scanner = new Scanner(System.in);
    private final UI ui = new UI();
    private final Parser parser;

    /**
     * Constructs the Clare instance and load initial data
     */
    public Clare() {
        TaskList taskList;
        String storagePath = "data/data.txt";
        Storage storage = new Storage(storagePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            storage.createFile();
            taskList = new TaskList();
        } catch (StringConvertExceptions e) {
            ui.showMessage("Error data format " + e);
            taskList = new TaskList();
        }
        parser = new Parser(ui, storage, taskList);
    }

    private void run() {
        while (true) {
            String msg = scanner.nextLine();
            if (msg.startsWith("bye")) {
                ui.farewell();
                break;
            }
            parser.processCommand(msg);
        }
    }

    public static void main(String[] args) {
        Clare clare = new Clare();
        clare.run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        parser.processCommand(input);
        System.setOut(originalOut);
        return outputStream.toString();
    }
}
