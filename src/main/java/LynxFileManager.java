import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import java.io.IOException;

public class LynxFileManager {

    private static final Path filePath = Paths.get("./data/log.txt");

    public static void createFile() {
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("");
            }
        }
    }

    public static List<String> readFromFile() throws IOException {
        return Files.readAllLines(filePath);
    }

    public static void writeToFile(List<String> text) throws IOException {
        Files.write(filePath, text);
    }

}
