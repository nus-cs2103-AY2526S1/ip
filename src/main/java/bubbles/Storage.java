package bubbles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for handling reading and writing to the hard disk.
 * The {@code Storage} class is responsible for loading saved tasks from disk and
 * appending or overwriting task data.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a {@code Storage} object with the given file path.
     *
     * @param filePath the path to the file used for storing tasks
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and adds it to the task list.
     *
     * @return an {@code ArrayList} of {@code Task} loaded from file
     * @throws FileNotFoundException if the storage file does not exist
     */
    public ArrayList<Task> load() throws FileNotFoundException{
        try{
            File f = new File(filePath);
            ArrayList<Task> tasklist = new ArrayList<>();

            if(!f.exists()){
                throw new FileNotFoundException("File not found a: " + filePath);
            }

            Scanner s = new Scanner(f);
            while(s.hasNext()){
                String str = s.nextLine();
                char ch = str.charAt(2);

                String[] words = str.split("] ");

                if (ch == 'T'){
                    Todo todo  = new Todo(words[1]);

                    if(str.contains("[X]")){
                        todo.setDone();
                    }
                    tasklist.add(todo);
                }

                if (ch == 'D') {
                    try {
                        String by = words[1].split("by:")[1].substring(0, words[1].split("by:")[1].length() - 1);
                        Deadline deadline = new Deadline(words[1].split(" \\(by")[0] + " /by" + by);

                        if (str.contains("[X]")) {
                            deadline.setDone();
                        }

                        tasklist.add(deadline);
                    } catch (Exception e) {
                        System.out.println("Error loading deadline: " + e.getMessage());
                    }
                }

                if (ch == 'E'){
                    String desc = words[1].split("\\(from:")[0];
                    String from = words[1].split("from:")[1].split("to: ")[0];
                    String to = words[1].split("from:")[1].split("to: ")[1].substring(0, words[1].split("from:")[1].split("to: ")[1].length() - 1);

                    Event event = new Event(desc + "/from" + from + "/to " + to);

                    if(str.contains("[X]")){
                        event.setDone();
                    }

                    tasklist.add(event);
                }
            }

            return tasklist;
        } catch (BubblesException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Appends the given text to the file.
     *
     * @param textToAdd the text to append
     * @throws IOException if an I/O error occurs
     */
    public static void append(String textToAdd) throws IOException {
        try (FileWriter fw = new FileWriter("bubbles.txt", true)) {
            fw.write(textToAdd);
        }
    }

    /**
     * Overwrites the file with the given text.
     *
     * @param textToAdd the text to write
     * @throws IOException if an I/O error occurs
     */
    public static void write(String textToAdd) throws IOException {
        try (FileWriter fw = new FileWriter("bubbles.txt")) {
            fw.write(textToAdd);
        }
    }

}
