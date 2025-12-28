package thecoolerduke.main;

import java.nio.file.Paths;

import thecoolerduke.feature.TaskHistoryManager;
import thecoolerduke.feature.TaskManager;

/**
 * A chatbot that helps keep track of your tasks, with persistent storage functionality.
 */
public class TheCoolerDuke {
    private static final String HOME_DIR = System.getProperty("user.dir");
    private static final String PATH_DIR = Paths.get(
            HOME_DIR, "src", "data", "TaskHistory.txt"
    ).toString();
    private final TaskManager tm;

    /**
     * Initialises the chatbot.
     *
     */
    public TheCoolerDuke() {
        TaskHistoryManager thm = new TaskHistoryManager(PATH_DIR);
        this.tm = new TaskManager(thm);
    }

    private String[] inputHandler(String msg) {
        //Split all inputs into [command, modifier]
        return msg.trim().split(" ", 2);
    }

    private String displayBotIntroduction() {
        return String.format("Hello! I'm TheCoolerDuke\nWhat can I do for you?\n\n%s\n\n%s",
                tm.startupTaskManager(),
                "PS: Use the \"help\" command to check out what you can do :)"
        );
    }

    /**
     * Provides the Task Manager feature for the chatbot.
     *
     * @param inputMessage Input string for bot to process.
     * @return An output string for display.
     */
    public String processResponse(String inputMessage) {
        //Split input into command and modifier
        String[] input = inputHandler(inputMessage);
        String command = input[0];
        String modifier = input.length > 1 ? input[1] : "";

        /*
        Note: bye command is listed here but the outputMessage will not be displayed.
        Instead, the JavaFX application will close without a reply from the bot.
        This is a temporary workaround until I can get Thread.sleep() to work with JavaFX.
         */
        if (!command.equals("bye")) {
            Command result = Command.validateCommand(command);

            if (result == null) {
                //command not recognised
                return "What do you mean? Please try again...";
            } else {
                //valid command, execute
                return result.execute(new String[]{modifier}, tm);
            }
        } else {
            return "Alright, I guess you're done :(\nGoodbye!";
        }
    }

    /**
     * Runs the initial startup sequence of the chatbot.
     *
     * @return Startup output string for display.
     */
    public String run() {
        //Startup task manager and display output message
        return displayBotIntroduction();
    }
}
