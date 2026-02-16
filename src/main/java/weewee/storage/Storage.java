package weewee.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import weewee.exception.WeeweeException;
import weewee.task.Deadline;
import weewee.task.Event;
import weewee.task.Task;
import weewee.task.TaskList;
import weewee.task.ToDo;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    // opening up data into array at the start of the chatbot
    public TaskList load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // create ./data if missing
                file.createNewFile(); // create empty file
                return new TaskList(tasks);
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Task t = parseTask(line);
                if (t == null) {
                    // corrupted line detected(null in the array) = discard entire file
                    System.out.println("Save file corrupted, starting fresh.");
                    br.close();
                    return new TaskList(new ArrayList<>());
                }
                tasks.add(t);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("OOPS couldn't load tasks: " + e.getMessage());
        }
        return new TaskList(tasks);
    }

    //everytime tasks changes, data file is rewrote with the new task array after bye
    public void save(TaskList tasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task t : tasks.getAll()) {
                bw.write(formatTask(t));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("OOPS couldn't save tasks: " + e.getMessage());
        }
    }

    //read the task from the saved file
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        try {
            switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(parts[2]);
                if (parts[1].equals("1")) {
                    todo.setDone();
                }
                return todo;
            case "D":
                Deadline dl = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) {
                    dl.setDone();
                }
                return dl;
            case "E":
                Event ev = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) {
                    ev.setDone();
                }
                return ev;
            default:
            }
        } catch (WeeweeException e) {
            System.out.println("Save file corrupted (bad date/time): " + e.getMessage());
            return null;
        }
        return null;
    }

    //format to write the task into the saved file
    private String formatTask(Task t) {
        if (t instanceof ToDo) {
            return "T | " + (t.getIsdone().equals("[X]") ? "1" : "0") + " | " + t.getTaskName();
        } else if (t instanceof Deadline d) {
            return "D | " + (d.getIsdone().equals("[X]") ? "1" : "0") + " | " + d.getTaskName()
                    + " | " + d.getRawDate();
        } else if (t instanceof Event e) {
            return "E | " + (e.getIsdone().equals("[X]") ? "1" : "0") + " | " + e.getTaskName()
                    + " | " + e.getRawStart() + " | " + e.getRawEnd();
        }
        return "";
    }
}


