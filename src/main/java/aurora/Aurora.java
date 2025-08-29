package aurora;

import aurora.command.Command;
import aurora.command.CommandReader;
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
        List<Task> list = load();

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
            File tasks = new File("./data/aurora.txt");
            File parentDir = tasks.getParentFile();

            if (!parentDir.exists() && !parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir);
            }

            try (FileWriter fw = new FileWriter(tasks)) {
                for (Task task : list) {
                    fw.write(task.toText());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Task> load() {
        List<Task> result = new ArrayList<>();
        try {
            File tasks = new File("./data/aurora.txt");
            Scanner s = new Scanner(tasks);
            while (s.hasNextLine()) {
                result.add(TaskReader.fromText(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            return result;
        }

        return result;
    }
}
