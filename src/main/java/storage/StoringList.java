package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exceptions.CannotLoad;
import exceptions.CannotStore;
import exceptions.EventTimelineInvalid;
import exceptions.InvalidDateInput;
import exceptions.InvalidElementInList;
import task.Task;
import task.specific.Deadlines;
import task.specific.Events;
import task.specific.ToDo;

/**
 * This class is in charge of storing the user tasks into the file.
 * At the start, the file is either loaded or created from scratch.
 * After every action, the TaskList is stored back into the file
 * so that the state present in the document is the most accurage.
 *
 */

public class StoringList {

    /**
     * In charge of loading the list from Jocelyn.txt.
     * Used ChatGPT to store the list better in terms for format.
     * @return The final updated list from the file.
     * @throws CannotLoad Cannot load the list.
     * @throws InvalidDateInput User inputted the date with an inproper format.
     * @throws InvalidElementInList It is acting on an invalid element.
     * @throws EventTimelineInvalid User inputted an invalid timeframe.
     */
    public ArrayList<Task> load() throws CannotLoad, InvalidElementInList,
            InvalidDateInput, EventTimelineInvalid {
        File directory = new File("theData");
        ArrayList<Task> theList = new ArrayList<>();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File theFile = new File(directory, "Jocelyn.txt");
        if (!theFile.exists()) {
            try {
                theFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            Scanner nextLiner = new Scanner(theFile);
            while (nextLiner.hasNextLine()) {
                String t = nextLiner.nextLine();
                String[] p = t.split("\"\"");
                ArrayList<String> arraylist = new ArrayList<>(Arrays.asList(p));
                assert p.length >= 1 : "the input of the task is invalid";
                boolean finished = arraylist.get(1).contains("[X]");
                String description = "";
                String deadline = "";
                String startingTime = "";
                String endingTime = "";
                ArrayList<String> tags = new ArrayList<>();
                for (int i = 2; i < arraylist.size(); i++) {
                    if (arraylist.get(i).contains("#")) {
                        tags.add(arraylist.get(i));
                    } else if (arraylist.get(i).contains("\\by")) {
                        deadline = arraylist.get(i).substring(3);
                    } else if (arraylist.get(i).contains("\\from")) {
                        startingTime = arraylist.get(i).substring(5);
                    } else if (arraylist.get(i).contains("\\to")) {
                        endingTime = arraylist.get(i).substring(3);
                    } else {
                        description += arraylist.get(i);
                    }
                }
                Task specific = null;
                if (arraylist.get(0).contains("[ToDo]")) {
                    specific = new ToDo(description, finished, tags);
                } else if (arraylist.get(0).contains("[Deadline]")) {
                    specific = new Deadlines(description, deadline, finished, tags);
                } else if (arraylist.get(0).contains("[Events]")) {
                    specific = new Events(description, startingTime,
                            endingTime, finished, tags);
                } else {
                    throw new CannotLoad();
                }
                theList.add(specific);
            }
        } catch (IOException ee) {
            throw new CannotLoad();
        }
        return theList;
    }

    /**
     * Stores the updated list into the .txt file.
     *
     * @param t The ArrayList that you update every step.
     */
    public void store(ArrayList<Task> t) throws CannotStore {
        try {
            File directory = new File("theData");
            File theFinalFile = new File(directory, "Jocelyn.txt");
            FileWriter a = new FileWriter(theFinalFile, false);
            for (Task theTask : t) {
                a.write(theTask.store() + "\n");
            }
            a.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}




