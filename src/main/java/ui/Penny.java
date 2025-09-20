package ui;

import java.io.IOException;


import command.Command;
import parser.InputParser;
import storage.Storage;


public class Penny {
    public static Storage storage;

    public Penny() {
        this.storage = new Storage();
    }

    public String getResponse(String userInput) {
        do {
            Command command = InputParser.parseCommand(userInput);
            try {
                storage.saveTasks();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return command.respond();
        } while (!Command.shouldExit);
    }
}
