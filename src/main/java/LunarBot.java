import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class LunarBot {
    public static final String LINE = "__________________________________________";
    public static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello from LunarBot!\n");
        File saveFile = loadFile();
        if (saveFile == null) {
            System.out.println("Error occurred... exiting!");
            System.exit(1);
        }
        System.out.println("Nice to meet you! What can I do for you?\n" + LINE);
        writeFile(saveFile, echo(loadData(saveFile)));
        System.out.println(LINE);
        System.out.println("Hope to see you soon!\n");
    }

    /**
     * Loads the file at ./data/LunarBot.csv and creates it if it does not exist
     *
     * @return File object of the file stored at the location
     */
    public static File loadFile() {
        try {
            new File("./data/").mkdirs();
            File file = new File("./data/LunarBot.csv");
            if (file.createNewFile()) {
                System.out.println("Creating new save data: " + file.getName());
            } else {
                System.out.println("Saved data already exists! " +
                        "Loading from hard disk!");
            }
            return file;
        } catch (IOException exception) {
            System.out.println(exception.toString());
            return null;
        }
    }

    /**
     * Reads the input file and returns the saved data as a list of tasks
     *
     * @param file Input file
     * @return List of tasks
     */
    public static List<Task> loadData(File file) {
        try {
            List<Task> data = new ArrayList<>();
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] values = line.split(",");
                switch (values[0]) {
                case "X":
                    data.add(new Task(values[2], values[1].equals("true")));
                    break;
                case "T":
                    data.add(new Todo(values[2], values[1].equals("true")));
                    break;
                case "D":
                    data.add(new Deadline(values[2], values[1].equals("true"),
                            LocalDateTime.parse(values[3], SAVE_FORMAT)));
                    break;
                case "E":
                    data.add(new Event(values[2], values[1].equals("true"),
                            LocalDateTime.parse(values[3], SAVE_FORMAT), LocalDateTime.parse(values[4], SAVE_FORMAT)));
                    break;
                default:
                    System.out.println("Something wrong occurred... irregular occurrence in saved data");
                }
            }

            return data;
        } catch (IOException exception) {
            System.out.println(exception.toString());
            return null;
        }
    }

    /**
     * Writes the new data into the save file
     *
     * @param file file to save the data to
     * @param saveData data to save to the file
     */
    public static void writeFile(File file, List<Task> saveData) {
        try (FileWriter writer = new FileWriter(file)) {
            for (Task data : saveData) {
                writer.write(data.getAsCsv() + "\n");
            }
        } catch (IOException exception) {
            System.out.println(exception.toString());
        }
    }

    /**
     * Main loop of the function. Uses user input to create a task sheet
     *
     * @param saveData loaded data from save file
     * @return list of tasks
     */
    public static List<Task> echo(List<Task> saveData) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = saveData;

        while (true) {
            System.out.print("Input: ");
            String input = sc.nextLine();

            String[] tmp = input.split(" ");

            // Bye
            if (input.equals("bye")) {
                // exit
                return tasks;
            }
            // List
            else if (input.equals("list")) {
                // print history
                System.out.println("Printing history!");
                for (int i = 1; i < tasks.size() + 1; i++) {
                    System.out.print(i + ": ");
                    System.out.println(tasks.get(i-1).print());
                }
            }
            // Mark
            else if (tmp[0].equals("mark") && tmp.length > 1){
                if (!tmp[1].matches("[-+]?\\d+")) {
                    System.out.println("Operation not possible! Index has to be an integer!");
                    System.out.println(LINE);
                    continue;
                }
                int index = Integer.valueOf(tmp[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible! Need to specify a valid index to mark!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll mark that one off your list!");
                tasks.get(index).setCompleted(true);
                System.out.println(tasks.get(index).print());
            }
            // Unmark
            else if (tmp[0].equals("unmark") && tmp.length > 1){
                if (!tmp[1].matches("[-+]?\\d+")) {
                    System.out.println("Operation not possible! Index has to be an integer!");
                    System.out.println(LINE);
                    continue;
                }
                int index = Integer.valueOf(tmp[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible! Need to specify a valid index to unmark!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll unmark that!");
                tasks.get(index).setCompleted(false);
                System.out.println(tasks.get(index).print());
            }
            // Todos
            else if (tmp[0].equals("todo")) {
                if (tmp.length == 1) {
                    System.out.println("Operation not possible! TODO's descriptor cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay! I'll add this to your TODOs");
                tasks.add(new Todo(input.substring(input.indexOf(" ") + 1), false));
            }
            // Deadlines
            else if (tmp[0].equals("deadline")) {
                if (tmp.length == 1) {
                    System.out.println("Operation not possible! Deadline's descriptor cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                if (input.split("/by").length == 1) {
                    System.out.println("Operation not possible! Deadline's end time cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay! I'll add this to your deadlines~");
                tasks.add(new Deadline(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                        false, LocalDateTime.parse(input.split("/by ")[1], SAVE_FORMAT)));
            }
            // Events
            else if (tmp[0].equals("event")) {
                if (tmp.length == 1) {
                    System.out.println("Operation not possible! Event's descriptor cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                if (input.split("/from").length == 1) {
                    System.out.println("Operation not possible! Event's start time cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                if (input.split("/to").length == 1) {
                    System.out.println("Operation not possible! Event's end time cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                String[] tmp2 = input.split("/from ")[1].split(" /to ");
                System.out.println("Okay! I'll add this to your events!");
                tasks.add(new Event(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1), false,
                        LocalDateTime.parse(tmp2[0], SAVE_FORMAT), LocalDateTime.parse(tmp2[1], SAVE_FORMAT)));
            }
            // Delete
            else if (tmp[0].equals("delete") && tmp.length > 1){
                if (!tmp[1].matches("[-+]?\\d+")) {
                    System.out.println("Operation not possible! Index has to be an integer!");
                    System.out.println(LINE);
                    continue;
                }
                int index = Integer.valueOf(tmp[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible! Need to specify a valid index to delete!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll delete this one from your list!");
                System.out.println(tasks.get(index).print());
                tasks.remove(tasks.get(index));
            }
            // Catch
            else {
                System.out.println("added: " + input);
                tasks.add(new Task(input, false));
            }
            // add to history
            System.out.println(LINE);
        }
    }
}