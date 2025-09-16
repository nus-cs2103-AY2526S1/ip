package Chunky;

import Chunky.Command.*;
import Chunky.Parser.Parser;
import Chunky.Statistics.TaskStatisticsCalculator;
import Chunky.Statistics.TaskStatsFormatter;
import Chunky.Storage.Storage;
import Chunky.Task.TaskList;
import Chunky.Ui.Ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Chunky {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Map<String, Command> commands;
    private TaskStatisticsCalculator statisticsCalculator;
    private TaskStatsFormatter statsFormatter;

    public Chunky(String filePath) {
        initializeComponents(filePath);
        initializeCommands();
    }

    private void initializeComponents(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = loadTasksFromStorage();
        this.statisticsCalculator = new TaskStatisticsCalculator(tasks);
        this.statsFormatter = new TaskStatsFormatter();
    }

    private TaskList loadTasksFromStorage() {
        try {
            return new TaskList(storage.load());
        } catch (IOException e) {
            return new TaskList();
        }
    }

    private void initializeCommands() {
        this.commands = new HashMap<>();
        commands.put("list", new ListCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("todo", new AddTaskCommand());
        commands.put("deadline", new AddTaskCommand());
        commands.put("event", new AddTaskCommand());
        commands.put("find", new FindCommand());
    }

    public String getResponse(String input) {
        if (isEmptyInput(input)) {
            return "Please enter a command!";
        }

        try {
            return processCommand(input);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private boolean isEmptyInput(String input) {
        return input.trim().isEmpty();
    }

    private String processCommand(String input) throws Exception {
        String commandName = Parser.getCommand(input);

        if (isByeCommand(commandName)) {
            return handleByeCommand();
        }

        return executeCommand(commandName, input);
    }

    private boolean isByeCommand(String commandName) {
        return commandName.equals("bye");
    }

    private String handleByeCommand() throws Exception {
        storage.save(tasks.getTasks());
        return "Bye! Hope to see you again soon!\nTasks have been saved.";
    }

    private String executeCommand(String commandName, String input) throws Exception {
        Command command = commands.get(commandName);
        if (command != null) {
            return command.execute(input, tasks, storage);
        } else {
            return "Unknown command: " + commandName;
        }
    }

    public String getTaskStats() {
        int totalTasks = tasks.size();
        int completedTasks = 0;

        for (int i = 0; i < totalTasks; i++) {
            if (tasks.get(i).getDone()) {
                completedTasks++;
            }
        }

        return String.format("Tasks: %d total | %d completed | %d remaining",
                totalTasks, completedTasks, totalTasks - completedTasks);
    }

    public String getDetailedStats() {
        return statsFormatter.formatDetailedStats(
                statisticsCalculator.calculateDetailedStats()
        );
    }

    // Existing methods remain the same...
    public void run() {
        showWelcome();
        processUserCommands();
        cleanup();
    }

    private void showWelcome() {
        ui.showWelcome();
    }

    private void processUserCommands() {
        boolean shouldExit = false;
        while (!shouldExit) {
            shouldExit = processUserCommand();
        }
    }

    private boolean processUserCommand() {
        try {
            String input = getUserInput();
            String response = getResponse(input);
            displayResponse(response);
            return shouldExit(input);
        } catch (Exception e) {
            ui.showError(e.getMessage());
            return false;
        }
    }

    private String getUserInput() {
        ui.showLine();
        return ui.readCommand();
    }

    private void displayResponse(String response) {
        System.out.println(response);
        ui.showLine();
    }

    private boolean shouldExit(String input) {
        return Parser.getCommand(input).equals("bye");
    }

    private void cleanup() {
        ui.close();
    }

    public static void main(String[] args) {
        new Chunky("./Chunky.txt").run();
    }
}