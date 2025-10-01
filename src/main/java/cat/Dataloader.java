package cat;

import java.io.*;
import java.util.*;


public class Dataloader {
    private static final String DATA_FILE = "meow.txt";

    //private List<String> data;
    private ArrayList<Task> tasks;


    public Dataloader() {
        //data = new ArrayList<>();
        this.tasks = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {

                    // Split by the pipe character "|"
                    String[] parts = line.split("\\|");
                    String name = parts[2].trim();

                    switch (line.charAt(0)) {
                        case 'T' -> {
                            Todo todo = new Todo(name);
                            tasks.add(todo);
                        }
                        case 'D' -> {
                            String endDate = parts[3].trim();
                            Deadline deadline = new Deadline(name, endDate);
                            tasks.add(deadline);
                        }
                        case 'E' -> {
                            // Remove leading/trailing whitespace
                            String startDate = parts[3].trim();
                            String endDate = parts[4].trim();
                            Event event = new Event(name, startDate, endDate);
                            tasks.add(event);
                        }
                    }
                    if (line.charAt(4) == '1'){
                        tasks.get(tasks.size() - 1).setDone();
                    }

                }
                //System.out.println("Data loaded successfully from " + DATA_FILE);
            } catch (IOException e) {
                //System.err.println("Error reading file: " + e.getMessage());
            }
        } else {
            //System.out.println("File not found: " + DATA_FILE);
        }
    }


    public ArrayList<Task> getTasks() {

        return tasks;
    }
}

