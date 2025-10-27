package JavaX;

import ui.CommandParser;
import command.Command;
import ui.TaskService;
import ui.ui;

public class Luka {
    private TaskService service;
    private ui ui;

    public Luka() {
        this.service = new TaskService();
        this.ui = new ui();
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        CommandParser parser = new CommandParser(service, ui);
        boolean isRunning = true;
        while(isRunning){
            Command command = parser.parseCommand(input);

            if (command != null) {
                command.execute();
                return command.toString();
            } else if (input.equals("help")) {
                return "valid command: \n" + "adding task : todo, event, deadline\n"
                        + "listing task : list, find\n" + "else : mark, unmark, delete";
            } else {
                return "I'm sorry, but I don't know what that means :-(";
            }
        }
        return null;
    }
}
