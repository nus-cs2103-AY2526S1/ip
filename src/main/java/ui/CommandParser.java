package ui;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;


public class CommandParser {
    private TaskService service;
    private ui ui;

    public CommandParser(TaskService service, ui ui) {
        this.service = service;
        this.ui = ui;
    }

    public void run() {
        boolean isRunning = true;
        while(isRunning){
            String input = ui.readcommand();
            Command command = parseCommand(input);

            if (command != null) {
                command.execute();
                if (command.isExit()) {
                    isRunning = false;
                }
            } else {
                ui.showError("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public Command parseCommand(String input){
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0];
        String arguments = "";
        if (parts.length > 1){
             arguments = parts[1];
        }
        switch (commandWord) {
            case "bye":
                return new ExitCommand(ui);
            case "list":
                return new ListCommand(service, ui);
            case "mark":
                return new MarkCommand(service, ui, arguments);
            case "unmark":
                return new UnmarkCommand(service, ui, arguments);
            case "todo":
                return new AddTodoCommand(service, ui, arguments);
            case "deadline":
                return new AddDeadlineCommand(service, ui, arguments);
            case "event":
                return new AddEventCommand(service, ui, arguments);
            case "delete":
                return new DeleteCommand(service, ui, arguments);
            case "find":
                return new FindCommand(service, ui, arguments);
            default:
                return null;
        }
    }
}
