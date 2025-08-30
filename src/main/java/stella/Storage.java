package stella;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;

public interface Storage {
    public final String DATA_STORAGE_PATH = "../data/stella.txt";

    public static Task lineToTask(String description) {
        Task newTask = null;
        if (description.charAt(1) == 'T') {
            newTask = new ToDo(description.substring(7));
        }
        else if (description.charAt(1) == 'D') {
            int pointer1 = description.indexOf("(by: ");
            newTask = new Deadline(description.substring(7, pointer1 -1),
                                   description.substring(pointer1+5, description.length() - 1));

        }
        else if (description.charAt(1) == 'E') {
            int pointer1 = description.indexOf(" (from: ");
            int pointer2 = description.indexOf(" | to: ");
            newTask = new Event(description.substring(7, pointer1),
                                description.substring(pointer1 +8, pointer2),
                                description.substring(pointer2+7, description.length()-1));
        }
        if (description.charAt(7) == 'X') {
            newTask.markDone();
        }
        return newTask;

    }

    public static ArrayList<Task> readFile() {
        for (int attempt = 1; attempt <= 2; attempt++) {
            try {

                BufferedReader reader = new BufferedReader(new FileReader((DATA_STORAGE_PATH)));
                String task_desription;
                ArrayList<Task> temp = new ArrayList<>();
                while ((task_desription = reader.readLine()) != null) {
                    temp.add(Storage.lineToTask(task_desription));
                }

                return temp;
            } catch (FileNotFoundException e) {
                File folder = new File("../data");
                folder.mkdirs();
                File dataFile = new File(DATA_STORAGE_PATH);

            }
            catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }

        return new ArrayList<>();
    }

    public static void addTask(Task task) {
        try {
            FileWriter writer = new FileWriter(DATA_STORAGE_PATH, true);
            writer.write(task.toString());
            writer.write(System.lineSeparator());
            writer.close();

        }
        catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
    public static void modifyTaskList(ArrayList<Task> list) {
        try {

            FileWriter writer = new FileWriter(DATA_STORAGE_PATH, false);
            for (int i = 0 ; i < list.size(); i++) {
                writer.write(list.get(i).toString());
                writer.write(System.lineSeparator());
            }
            writer.close();

        }
        catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

    }

}



