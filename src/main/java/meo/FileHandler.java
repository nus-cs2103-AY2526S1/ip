package meo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import meo.data.TextList;
import meo.task.Deadline;
import meo.task.Event;
import meo.task.Task;
import meo.task.ToDo;

import java.util.ArrayList;

public class FileHandler {
    /**
     * Writes the current task list into save file.
     * 
     * @param taskList Current task list.
     */
    public static void writeFile(ArrayList<Task> taskList) {
        try {
            File dataFile = new File("src/main/data/meo.txt");
            if (dataFile.createNewFile()) {
                //System.out.println("File created");
            }
            FileWriter writer = new FileWriter(dataFile);
            int index = 0;
            while (index < taskList.size()) {
                Task task = taskList.get(index);
                String taskContent = "";
                String taskStatus = task.isTaskDone() ? "1" : "0";
                if (taskList.get(index) instanceof ToDo) {
                    taskContent = "[T][" + taskStatus + "] " + task.getDescription() + "\n";
                };

                if (taskList.get(index) instanceof Event) {
                    Event e = (Event) task;
                    taskContent = "[E][" + taskStatus + "] " + e.getDescription() + " | " + e.getFrom() + "-" + e.getTo() + "\n";
                };

                if (taskList.get(index) instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    taskContent = "[D][" + taskStatus + "] " + d.getDescription() + " | " + d.getDeadline() + "\n";
                };
                writer.write(taskContent);
                index++;
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Uh oh... Something is wrong! Error: " + e);
        }
    }

    protected static ArrayList<String> readFile(){
        try {
            ArrayList<String> data = new ArrayList<String>();
            File dataFile = new File("src/main/data/meo.txt");
            File fileDir = new File("src/main/data");
            fileDir.mkdirs();
            if (dataFile.createNewFile()) {
                // System.out.println("File created.");
                return new ArrayList<String>();
            } else {
                // System.out.println("File existed");
            }
            Scanner reader = new Scanner(dataFile);
            while (reader.hasNextLine()) {
                data.add(reader.nextLine());
            }
            reader.close();
            return data;
        } catch(IOException e) {
            System.out.println("Uh oh... Something is wrong when trying to read file! Error: " + e);
            return new ArrayList<String>();
        }
    }

    public static TextList getList() {
        ArrayList<String> data = readFile();
        int index = 0;
        TextList list = new TextList();
        while (index < data.size()) {
            String task = data.get(index);
            String taskType = task.substring(1,2);
            String taskStatus = task.substring(4, 5);
            String taskDesc = task.substring(7);
            int timeIndex = taskDesc.indexOf("|");
            Task newTask = null;

            if (taskType.equals("T")) {
                newTask = new ToDo(taskDesc);
            }
            
            if (taskType.equals("E")) {
                int toIndex = taskDesc.indexOf("-");
                String from = taskDesc.substring(timeIndex + 1, toIndex).trim();
                String to = taskDesc.substring(toIndex + 1).trim();
                taskDesc = taskDesc.substring(0, timeIndex - 1);
                newTask = new Event(taskDesc, from, to);
            }

            if (taskType.equals("D")) {
                String deadline = taskDesc.substring(timeIndex + 1).trim();
                taskDesc = taskDesc.substring(0, timeIndex - 1);
                newTask = new Deadline(taskDesc, deadline);
            }

            if (newTask != null && taskStatus.equals("1")) {
                newTask.isMarked();
            }
            list.add(newTask);    
            index++;
        }
        return list;
    }
}
