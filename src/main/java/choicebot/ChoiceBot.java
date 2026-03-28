package choicebot;

import choicebot.command.Command;
import choicebot.command.ExitCommand;
import choicebot.parser.Parser;
import choicebot.storage.Storage;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * The main class of ChoiceBot.
 * ChoiceBot is a chatbot used to add, delete and manage tasks using command inputs.
 */
public class ChoiceBot {
    private TaskList tasks;
    private Storage storage;
    private UI ui;
    private boolean isEnded = false;

    /**
     * Constructs instance of ChoiceBot using the specified file path.
     *
     * @param filePath File used to load and save task list data.
     */
    public ChoiceBot(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile().getTaskList());
        } catch (ChoiceBotException e) {
            ui.displayMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the instance of ChoiceBot using the specified file path.
     */
    public void run() {
        isEnded = false;
        UI.welcome();
        while (!isEnded) {
            String input = ui.readUserInput();
            String response = getResponse(input);
            ui.displayMessage(response);
        }
    }

    /**
     * Returns String response sent by ChoiceBot given user input.
     *
     * @param input Text input send by user to ChoiceBot.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command instanceof ExitCommand) {
                isEnded = true;
            }
            return command.execute(tasks, ui, storage);
        } catch (ChoiceBotException e) {
            return ui.displayMessage(e.getMessage());
        }
    }

    /**
     * The entry point of the chatbot.
     *
     * @param args Command-line arguments (Not used yet).
     */
    public static void main(String[] args) {
        new ChoiceBot("data/tasks.txt").run();
    }
}
