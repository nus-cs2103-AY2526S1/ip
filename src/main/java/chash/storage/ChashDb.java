//Imports
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChashDb {
    private final String fileLocation;

    ChashDb(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    ChashDb() {
        this.fileLocation = "./ChashData/chashdb.txt";
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
        Files.createFile(path);

        return false;
    }

    public ArrayList<Task> loadDb(ChashUi ui) throws IOException {
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
                //Process line if not blank
                if (!dataline.isBlank()) {
                    try {
                        tasks.add(TaskParser.fromExportString(dataline));
                    } catch (ChashException ex) {
                        //note: could not think of a better way to print these logs
                        //other than passing the ui object
                        ui.printErr("CHASHDB: Line " + i + " Invalid");
                    }
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

    public void writeDb(List<Task> tasks) throws IOException {
        //Java try-with-resource technique requires the method to implement AutoCloseable
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

            for (Task t : tasks) {
                writer.write(t.exportString() + "\n");
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
