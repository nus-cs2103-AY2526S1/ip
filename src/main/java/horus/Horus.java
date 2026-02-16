package horus;

import horus.tasks.TaskList;

import java.io.IOException;

/**
 * A program that can be used to manage a list of tasks and save onto a local file
 */
public class Horus {
    private final TaskList taskList;
    private final Storage saveFile;
    private final Ui ui;
    private final Parser parser;
    private static final String DEFAULT_FILEPATH = "data/taskdata.txt";
    private String greetingMessage;

    /**
     * Initializes Horus and retrieves any previously saved tasks from a save file.
     *
     * @param filePath A string representing the file path where any tasks added will be saved.
     *                 If the file already exists, Horus will initialize with any tasks saved on the file.
     */
    public Horus(String filePath) {
        taskList = new TaskList();
        parser = new Parser(taskList);
        ui = new Ui();
        greetingMessage = "";
        try {
            saveFile = new Storage(filePath);
            if (!saveFile.checkIsNew()) {
//                greetingMessage += "Retrieving saved tasks from local file.\n";
                greetingMessage += "Summoning the record of oaths from the Chapter’s vaults.\n";
                greetingMessage += taskList.readFromFile(saveFile.readFile());
            }
        } catch (IOException e) {
//            greetingMessage += "Error: Unable to access local files";
            greetingMessage += "Error: The archives are sealed. Local data cannot be reclaimed.";
            throw new RuntimeException();
        }

//        greetingMessage += "\nHello! I'm Horus\nWhat can I do for you?";
        greetingMessage += "\nStand firm, warrior. I am Horus Lupercal,"
                + " primarch of the Lunar Wolves, Warmaster of the Imperium of Man and keeper of your sacred charges."
                + "\nSpeak now, what duty shall I inscribe for you?";
    }

    /**
     * Initializes Horus using the default filepath.
     */
    public Horus() {
        this(Horus.DEFAULT_FILEPATH);
    }

    /**
     * Runs the Horus program, where the program will continuously execute input commands until program exits.
     */
    public void run() {
        boolean isExit = false;
        ui.printOutputString(greetingMessage);

        while (!isExit) {
            String inputStr = ui.getInputStr();
            String outputStr = this.getResponse(inputStr);
            ui.printOutputString(outputStr);
        }

    }

    /**
     * Returns a greeting message to print when starting Horus.
     *
     * @return String representing greeting message.
     */
    public String getGreetingMessage() {
        return this.greetingMessage;
    }

    /**
     * Parses and executes user commands and return and response for the user
     *
     * @param inputStr String representing user command
     * @return String representing response for the user
     */
    public String getResponse(String inputStr) {
        String outputStr = "";

        boolean isSaveCommand = inputStr.equals("bye") || inputStr.equals("save");
        if (isSaveCommand) {
            outputStr += this.save();
        }

        outputStr += parser.parse(inputStr);
        return outputStr;
    }

    /**
     * Saves contents of taskList into local file
     *
     * @return String representing response for the user
     */
    public String save() {
        String[] taskListData = taskList.saveToFile();
        String outputStr = saveFile.writeToFile(taskListData);
        return outputStr;
    }

    /**
     * Initializes and run Horus using Java's IO
     */
    public static void main(String[] args) {
        Horus horus = new Horus();
        horus.run();
    }

}
