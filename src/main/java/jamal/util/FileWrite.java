package jamal.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Logic for writing into Files
 * Mainly to read and write onto Storage
 */
public class FileWrite {

    /**
     * Appends line to the end of file
     *
     * @param filePath Pathing for data file to be written on
     * @param line String to append to the end of data file
     */
    public static void addLine(String filePath, String line) { //type, mark, priority, desc, by/from, to
        try {
            FileWriter fw = new FileWriter(filePath, true); //append mode, strictly to add new lines
            fw.write(line + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.print("Unable to write data");
        }
    }

    /**
     * Rewrites file with marked line
     *
     * @param filePath Pathing for data file to be written on
     * @param mark True/false value to determine string going into idx line's second value for mark/unmark
     * @param idx Index of line in data file to be marked/unmarked
     */
    public static void markLine(String filePath, boolean mark, int idx) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            assert idx >= 0 : "idx must be greater than or equal to 0";
            String line = lines.get(idx);

            String[] temp = line.split("`");
            temp[1] = mark ? "M" : "UM";
            String changedLine = String.join("`", temp);

            lines.set(idx, changedLine);

            Files.write(path, lines);
        } catch (IOException e) {
            System.out.print("Unable to change data");
        }
    }

    /**
     * Rewrites file with appropriate priority
     *
     * @param filePath Pathing for data file to be written on
     * @param priority number to be rewritten
     * @param idx Index of line in data file to be prioritized
     */
    public static void prioritizeLine(String filePath, String priority, int idx) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            assert idx >= 0 : "idx must be greater than or equal to 0";
            String line = lines.get(idx);

            String[] temp = line.split("`");
            temp[2] = priority;
            String changedLine = String.join("`", temp);

            lines.set(idx, changedLine);

            Files.write(path, lines);
        } catch (IOException e) {
            System.out.print("Unable to change data");
        }
    }

    /**
     * Rewrites file after removing line
     *
     * @param filePath Pathing for data file to be written on
     * @param idx Index of line in data file to be removed
     */
    public static void deleteLine(String filePath, int idx) { //need to delete the whole file and rewrite everything ;-;
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            assert idx >= 0 : "idx must be greater than or equal to 0";
            lines.remove(idx);
            Files.write(path, lines);
        } catch (IOException e) {
            System.out.print("Unable to change data");
        }
    }
}
