package aurora;

import aurora.command.Command;
import aurora.command.CommandReader;

import aurora.storage.Storage;
import aurora.task.Task;
import aurora.task.TaskReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


/**
 * Aurora is a chatbot that manages tasks.
 */
public class Aurora {

    private static final String INTRO = "Hello! I'm Aurora. What can I do for you?";
    private static final String OUTRO = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("./data/aurora.txt");
        List<Task> list = storage.load();

        speak(INTRO);
        loop(scanner, list, storage);
        speak(OUTRO);
        scanner.close();
    }

    private static void speak(String content) {
        System.out.println("Aurora: " + content);
    }

    private static void loop(Scanner s, List<Task> list, Storage storage) {
        String input = s.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            Command command = CommandReader.read(input);
            speak(command.execute(list));
            storage.save(list);
            input = s.nextLine();
        }
    }
}
