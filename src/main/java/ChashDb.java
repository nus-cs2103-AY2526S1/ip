//Imports
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class ChashDb {
    private final String fileLocation;

    ChashDb(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    ChashDb() {
        this.fileLocation = "./ChashData/chashdb.txt";
    }

    private static Task parseTask(String line) throws ChashException {
        String[] tmp = line.split(" \\| ", 2);
        //Sanity check
        if (tmp.length != 2) {
            throw new ChashException("Invalid line");
        }

        Task task;
        switch (tmp[0]) {
        case Todo.TASKTYPE:
            tmp = tmp[1].split(" \\| ", 2);
            //Sanity check
            if (tmp.length != 2) {
                throw new ChashException("Todo task invalid");
            }
            task = new Todo(tmp[1]);
            break;

        case Deadline.TASKTYPE:
            tmp = tmp[1].split(" \\| ", 3);
            //Sanity check
            if (tmp.length != 3) {
                throw new ChashException("Deadline task invalid");
            }
            task = new Deadline(tmp[1], tmp[2]);
            break;

        case Event.TASKTYPE:
            tmp = tmp[1].split(" \\| ", 4);
            //Sanity check
            if (tmp.length != 4) {
                throw new ChashException("Event task invalid");
            }
            task = new Event(tmp[1], tmp[2], tmp[3]);
            break;
        
        default:
            throw new ChashException("Invalid task type");
        }

        //Check if task was saved as done and toggle if necessary
        return (tmp[0].equals("1")) ? task.toggleDone() : task;
    }

    private boolean fileExistsElseCreate() throws IOException {
        Path path = Paths.get(this.fileLocation);

        //Check if file exists
        if (Files.exists(path)) {
            return true;
        }

        //Check intermediate directories if exist
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        //Create file
        Files.createFile(path);

        return false;
    }

    public ArrayList<Task> loadDb() throws IOException {
        try {
            //Check if file and intermediate directories exists
            if (!fileExistsElseCreate()) {
                throw new ChashException("There was no CHASHDB file");
            }

            //Read file and parse input
            Path path = Paths.get(this.fileLocation);
            ArrayList<Task> tasks = new ArrayList<Task>();
            Scanner scanner = new Scanner(path);
            for (int i = 1; scanner.hasNextLine(); i += 1) {
                String dataline = scanner.nextLine();
                //Check for last line (assumed empty) and break
                if (dataline.isEmpty()) {
                    break;
                }

                try {
                    tasks.add(ChashDb.parseTask(dataline));
                } catch (ChashException ex) {
                    System.err.println("CHASHDB: Line " + i + " Invalid");
                }
            }
            scanner.close();

            return tasks;
        } catch (ChashException ex) {
            return new ArrayList<Task>();
        } catch (IOException ex) {
            //ex.printStackTrace();
            throw new IOException("There was an IO error accessing CHASHDB", ex);
        }
    }

    public void writeDb(ArrayList<Task> tasks) throws IOException {
        //Java try-with-resource technique
        try {
            //Check if file and intermediate directories exists and create if needed
            fileExistsElseCreate();
            
            //Get file writer
            Path path = Paths.get(this.fileLocation);
            BufferedWriter writer = Files.newBufferedWriter(
                path,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
            );
            //BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileLocation));

            for (Task task : tasks) {
                writer.write(task.exportString() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            //ex.printStackTrace();
            throw new IOException("Error writing to CHASHDB", ex);
        }
    }

    @Override
    public String toString() {
        return "CHASHDB @ " + this.fileLocation;
    }
}
