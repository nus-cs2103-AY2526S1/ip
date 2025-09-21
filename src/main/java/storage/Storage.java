package storage;

import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public TaskList loadSaveFile() throws Exception {
        File f = new File(filepath);
        TaskList tasks = new TaskList();
        if (f.exists()) {
            Scanner s = new Scanner(f);
            boolean error = false;
            StringBuilder errorStack = new StringBuilder();
            while (s.hasNext()) {
                try {
                    tasks.loadTask(s.nextLine());
                } catch (Exception e) {
                    error = true;
                    errorStack.append(e.getMessage()).append("\n");
                }
            }
            if (error) throw new Exception(errorStack.toString());
        }


        return tasks;
    }

    public void saveToFile(TaskList t) throws IOException {
        File f = new File("./save.txt");
        FileWriter fw = new FileWriter(f);
        StringBuilder sb = new StringBuilder();
        for (Task i : t.tasks) {
            if (i instanceof ToDo td) {
                sb.append("todo|").append(td.isDone ? "1|" : "0|").append(td.description).append(System.lineSeparator());
            } else if (i instanceof Deadline dl) {
                sb.append("deadline|").append(dl.isDone ? "1|" : "0|").append(dl.description).append("|").append(dl.deadline)
                        .append(System.lineSeparator());
            } else if (i instanceof Event ev) {
                sb.append("event|").append(ev.isDone ? "1|" : "0|").append(ev.description).append("|").append(ev.from)
                        .append("|").append(ev.to).append(System.lineSeparator());
            }
        }
        fw.write(sb.toString());
        fw.close();
    }
}
