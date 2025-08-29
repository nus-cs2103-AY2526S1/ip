package application;

import java.util.ArrayList;

import exception.RomidasException;
import tasks.DeadlineTask;
import tasks.Event;
import tasks.Task;
import tasks.TodoTask;

public class TextTaskConverter  {
    public static ArrayList<Task> convertToTask(ArrayList<String> lines) throws RomidasException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines){
            if (line.trim().isEmpty()) {
                continue; // Skip empty lines
            }
            String[] parts = line.split("\\|");
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].strip();
            }
            Task task = switch (parts[0]) {
                case "T" -> TodoTask.toTask(parts);
                case "D" -> DeadlineTask.toTask(parts);
                case "E" -> Event.toTask(parts);
                default -> throw new RomidasException("I'm sorry, I don't recognise that command. "
                        + "Try one of event, todo, deadline");
            };
            tasks.add(task);
        }
        return tasks;
    }

    public static ArrayList<String> convertToText(ArrayList<Task> tasks) throws RomidasException {
        ArrayList<String> lines = new ArrayList<>();
        for(Task task : tasks){
            String line = task.toText();
            lines.add(line);
        }
        return lines;
    }
}
