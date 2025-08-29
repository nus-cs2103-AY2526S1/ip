package aurora;

import aurora.command.Command;
import aurora.command.CommandReader;
import aurora.task.Task;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;

/**
 * Aurora is a chatbot that manages tasks.
 */
public class Aurora {

    private static final String INTRO = "Hello! I'm Aurora. What can I do for you?";
    private static final String OUTRO = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        speak(INTRO);
        loop(scanner, list);
        speak(OUTRO);
        scanner.close();
    }

    private static void speak(String content) {
        System.out.println("Aurora: " + content);
    }

    private static void loop(Scanner s, List<Task> list) {
        String input = s.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            Command command = CommandReader.read(input);
            speak(command.execute(list));
            save(list);
            input = s.nextLine();
        }
    }

    private static void save(List<Task> list) {
        try {
            File tasks = new File("./data/aurora.csv");
            File parentDir = tasks.getParentFile();

            if (!parentDir.exists() && !parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir);
            }

            try (FileWriter fw = new FileWriter(tasks)) {
                for (Task task : list) {
                    fw.write(task.toCSV());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
