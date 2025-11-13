package taskbot;

import java.util.Scanner;

import command.Command;
import misc.TaskBotException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Class handling the running of TaskBot.TaskBot
 */
public class TaskBot {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initialises the internal attributes required for TaskBot.TaskBot to run
     * @param filePath path of file where task list is locally stored
     */
    public TaskBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public String getResponse(String input) {
        if (input.trim().isEmpty()) {
            return "What do you want to do?";
        }
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            return response;
        } catch (TaskBotException e) {
            return ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new TaskBot("./data/TaskBot.TaskBot.txt").runTextMode();
    }

    /**
     * Shows TaskBot's welcome message
     * @return
     */
    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Runs TaskBot.TaskBot
     */
    public void runTextMode() {
        System.out.println(ui.showWelcome());
        Scanner sc = new Scanner(System.in);
        boolean shouldExit = false;

        while (!shouldExit) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            try {
                Command c = Parser.parse(input);
                String response = c.execute(tasks, ui, storage);
                System.out.println(response);
                shouldExit = c.shouldExit();
            } catch (TaskBotException e) {
                System.out.println(ui.showError(e.getMessage()));
            }
        }
        sc.close();
    }
}
