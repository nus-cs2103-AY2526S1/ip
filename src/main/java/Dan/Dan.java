package Dan;

import Dan.Parser.Parser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import Dan.Storage.Storage;
import Dan.Task.TaskList;
import Dan.Command.Command;

public class Dan {

    private final TaskList tasks;

    public Dan(String filePath) {
        Path path = Paths.get( filePath);
        Storage storage = new Storage(path);
        this.tasks = new TaskList(storage.load());
    }

    public String getReponse(String input) {
        try {
            Command cmd = Parser.parseUserInput(input);
            return cmd.execute(this.tasks);
        } catch (IllegalArgumentException e) {
            return "Invalid input";
        }
    }

    public static void unrecognisedInput() throws IllegalArgumentException {
        throw new IllegalArgumentException("I do not recognise this input.");
    }
}
