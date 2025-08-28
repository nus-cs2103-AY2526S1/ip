package evansbot.task;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
            return tasks;
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                continue;
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        sc.close();
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(formatTask(task) + System.lineSeparator());
        }
        fw.close();
    }

    private String formatTask(Task task) {
        String status = task.isDone ? "1" : "0";
        if (task instanceof ToDo) {
            return "T | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            String byStr;
            if (d.getByDate() != null) {
                byStr = String.valueOf(d.byDate);
            } else {
                byStr = d.byRaw;
            }
            return "D | " + status + " | " + d.description + " | " + byStr;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            String fromStr;
            String toStr;
            if (e.getFromDate() != null && e.getToDate() != null) {
                fromStr = String.valueOf(e.fromDate);
                toStr = String.valueOf(e.toDate);
            } else {
                fromStr = e.fromRaw;
                toStr = e.toRaw;
            }
            return "E | " + status + " | " + e.description + " | " + fromStr + " | " + toStr;
        }
        return "";
    }

}
