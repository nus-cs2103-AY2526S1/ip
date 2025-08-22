import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import java.io.IOException;

public class LynxFileManager {

    private static final Path filePath = Paths.get("./data/log.txt");

    public static void createFile() {
        try {
            // Ensure directory exists
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            // Create the file if it doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            LynxUI.line();
            System.out.println("⚠️ Warning: Lynx couldn't set up your data file!\n"
                    + "Details: " + e.getMessage() + "\n"
                    + "Your tasks may not be saved. Please check your file permissions or disk space.");
        }
    }

    public static List<String> readFromFile() {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            LynxUI.line();
            System.out.println("⚠️ Oops! Lynx couldn't read your data file.\n"
                    + "Details: " + e.getMessage() + "\n"
                    + "Your tasks could not be loaded. Starting with an empty list.");
            return List.of();
        }
    }

    public static void writeToFile(List<String> text) {
        try {
            Files.write(filePath, text);
        } catch (IOException e) {
            LynxUI.printBox("⚠️ Oops! Lynx couldn't save your tasks.\n"
                    + "Details: " + e.getMessage() + "\n"
                    + "Any changes made during this session may not be saved.");

            System.out.println("Would you like to try saving again? (yes/no)");
            String input = TaskLynx.getScanner().nextLine().trim();
            if (input.equalsIgnoreCase("yes")) {
                writeToFile(text);
            }
        }
    }

}

