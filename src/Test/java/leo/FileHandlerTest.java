package leo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import leo.functions.task.Deadline;
import leo.functions.task.Event;
import leo.functions.task.Task;
import leo.functions.task.ToDo;

public class FileHandlerTest {
    //Test loading the list file
    @Test
    public void fileHandler_fileExists_success() throws IOException {
        FileHandler fh = new FileHandler(".dataTest/temp.txt");
        assertEquals(fh, fh.loadFile());
    }

    @Test
    public void fileHandler_fileCreation_success() throws IOException {
        //Solution adapted from https://www.perplexity.ai/search/how-to-assert-equal-for-such-i-6HsiIA11RLadybjjfSz1Fw#2
        FileHandler fhTemp = new FileHandler(".dataTest/temp.txt");
        assertEquals(fhTemp, fhTemp.loadFile());
    }

    @Test
    public void fileHandler_appendTextToFile_success() throws IOException {
        File tempFile = new File(".dataTest/temp.txt");
        FileHandler fhTemp = new FileHandler(".dataTest/temp.txt");
        fhTemp.appendToFile("Hello World!");

        StringBuilder temp = new StringBuilder();
        Scanner scanner = new Scanner(tempFile);

        while (scanner.hasNext()) {
            temp.append(scanner.nextLine());
        }

        scanner.close();

        FileWriter cleanFile = new FileWriter(tempFile, false);
        cleanFile.write("");
        cleanFile.close();

        assertEquals("Hello World!", temp.toString());
    }

    @Test
    public void fileHandler_appendTextToFileWrongInput_exceptionThrown() throws IOException {
        FileHandler fhTemp = new FileHandler(".data");

        //Solution adapted from https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
        Exception exception = assertThrows(IOException.class, () -> {
            fhTemp.appendToFile("Hello World!");
        });
        assertEquals("Invalid file path given", exception.getMessage());
    }

    @Test
    public void fileHandler_deleteFromFile_success() throws IOException {
        File tempFile = new File(".dataTest/temp.txt");
        ArrayList<Task> tempListItems = new ArrayList<>();
        tempListItems.add(new Task("borrow books"));
        tempListItems.add(new Task("return books"));
        tempListItems.add(new Task("buy bandages"));

        FileHandler fhTemp = new FileHandler(".dataTest/temp.txt");

        fhTemp.deleteFromFile(tempListItems);

        StringBuilder temp = new StringBuilder();
        Scanner scanner = new Scanner(tempFile);

        while (scanner.hasNext()) {
            temp.append(scanner.nextLine());
        }

        scanner.close();

        FileWriter cleanFile = new FileWriter(tempFile, false);
        cleanFile.write("");
        cleanFile.close();

        assertEquals(("Task |   | borrow books"
                        + "Task |   | return books"
                        + "Task |   | buy bandages"),
                temp.toString());
    }

    @Test
    public void fileHandler_deleteFromFileWrongFile_exceptionThrown() throws IOException {
        ArrayList<Task> tempListItems = new ArrayList<>();
        tempListItems.add(new Task("borrow books"));
        tempListItems.add(new Task("return books"));
        tempListItems.add(new Task("buy bandages"));

        FileHandler fhTemp = new FileHandler(".data");

        //Solution adapted from https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
        Exception exception = assertThrows(IOException.class, () -> {
            fhTemp.deleteFromFile(tempListItems);
        });

        assertEquals("Invalid file path given", exception.getMessage());
    }

    @Test
    public void fileHandler_getToDoTaskFromFile_success() {
        try {
            File tempFile = new File(".dataTest/temp.txt");
            FileWriter writer = new FileWriter(tempFile, false);
            writer.write("ToDo |   | return book");
            writer.close();

            FileHandler fhTemp = new FileHandler("testFile.txt");
            //Solution adapted from
            // https://www.perplexity.ai/search/how-to-print-from-file-in-java-FCd0GTNlTKmE4Ycj3waKmg#6
            Class<?> clazz = fhTemp.getClass();
            Method privateMethod = clazz.getDeclaredMethod("getTaskFromFile", String.class);

            privateMethod.setAccessible(true);
            Object returnValue = privateMethod.invoke(fhTemp, "ToDo |   | return book");
            Task actualTask = (Task) returnValue;

            Task expectedTask = new ToDo("return book");

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            assertEquals(expectedTask.toString(), actualTask.toString());
        } catch (IOException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void fileHandler_getDeadlineTaskFromFile_success() {
        try {
            File tempFile = new File(".dataTest/temp.txt");
            FileWriter writer = new FileWriter(tempFile, false);
            writer.write("Deadline |   | return book | Jan 12 2025, 2000");
            writer.close();

            FileHandler fhTemp = new FileHandler("testFile.txt");
            //Solution adapted from
            // https://www.perplexity.ai/search/how-to-print-from-file-in-java-FCd0GTNlTKmE4Ycj3waKmg#6
            Class<?> clazz = fhTemp.getClass();
            Method privateMethod = clazz.getDeclaredMethod("getTaskFromFile", String.class);

            privateMethod.setAccessible(true);
            Object returnValue = privateMethod.invoke(fhTemp, "Deadline |   | return book | Jan 12 2025, 2000");
            Task actualTask = (Task) returnValue;

            Task expectedTask = new Deadline("return book", "12/01/2025 2000");

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            assertEquals(expectedTask.toString(), actualTask.toString());
        } catch (IOException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void fileHandler_getEventTaskFromFile_success() {
        try {
            File tempFile = new File(".dataTest/temp.txt");
            FileWriter writer = new FileWriter(tempFile, false);
            writer.write("Event |   | project meeting | Mar 02 2024, 1000-Mar 02 2024, 1030");
            writer.close();

            FileHandler fhTemp = new FileHandler("testFile.txt");
            //Solution adapted from
            // https://www.perplexity.ai/search/how-to-print-from-file-in-java-FCd0GTNlTKmE4Ycj3waKmg#6
            Class<?> clazz = fhTemp.getClass();
            Method privateMethod =
                    clazz.getDeclaredMethod("getTaskFromFile", String.class);

            privateMethod.setAccessible(true);
            Object returnValue =
                    privateMethod.invoke(fhTemp, "Event | X | project meeting | Mar 02 2024, 1000-Mar 02 2024, 1030");
            Task actualTask = (Task) returnValue;

            Task expectedTask = new Event("project meeting", "02/03/2024 1000", "02/03/2024 1030");
            expectedTask.markTask();

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            assertEquals(expectedTask.toString(), actualTask.toString());
        } catch (IOException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void fileHandler_getNullTaskFromFile_success() throws IOException, NoSuchMethodException {
        File tempFile = new File(".dataTest/temp.txt");
        FileWriter writer = new FileWriter(tempFile, false);
        writer.write("   ");
        writer.close();

        FileHandler fhTemp = new FileHandler("testFile.txt");
        //Solution adapted from https://www.perplexity.ai/search/how-to-print-from-file-in-java-FCd0GTNlTKmE4Ycj3waKmg#6
        Class<?> clazz = fhTemp.getClass();
        Method privateMethod = clazz.getDeclaredMethod("getTaskFromFile", String.class);

        privateMethod.setAccessible(true);

        Throwable cause = null;
        //Solution adapted from https://www.perplexity.ai/search/how-to-assert-equals-a-private-7_Vt5uKlTo6ZrIhZbrIY7A#3
        try {
            privateMethod.invoke(fhTemp, "");
        } catch (InvocationTargetException | IllegalAccessException e) {
            cause = e.getCause();
        }

        String error = "Failed to load the task ("
                + ") due to invalid format.";

        FileWriter cleanFile = new FileWriter(tempFile, false);
        cleanFile.write("");
        cleanFile.close();

        assertEquals(error, cause.getMessage());
    }

    @Test
    public void fileHandler_getEventTaskFromFileWithInvalidFormat_exceptionThrown()
            throws IOException, NoSuchMethodException {
        File tempFile = new File(".dataTest/temp.txt");
        FileWriter writer = new FileWriter(tempFile, false);
        writer.write("Event |   | project meeting");
        writer.close();

        FileHandler fhTemp = new FileHandler("testFile.txt");
        //Solution adapted from https://www.perplexity.ai/search/how-to-print-from-file-in-java-FCd0GTNlTKmE4Ycj3waKmg#6
        Class<?> clazz = fhTemp.getClass();
        Method privateMethod = clazz.getDeclaredMethod("getTaskFromFile", String.class);

        privateMethod.setAccessible(true);

        String error = "Failed to load the task ("
                + "Event |   | project meeting"
                + ") due to invalid format.";

        Throwable cause = null;
        //Solution adpated from https://www.perplexity.ai/search/how-to-assert-equals-a-private-7_Vt5uKlTo6ZrIhZbrIY7A#3
        try {
            privateMethod.invoke(fhTemp, "Event |   | project meeting");
        } catch (InvocationTargetException | IllegalAccessException e) {
            cause = e.getCause();
        }

        FileWriter cleanFile = new FileWriter(tempFile, false);
        cleanFile.write("");
        cleanFile.close();

        assertEquals(error, cause.getMessage());
    }

    @Test
    public void fileHandler_getTaskFromFileWithInvalidFormat_exceptionThrown()
            throws IOException, NoSuchMethodException {
        File tempFile = new File(".dataTest/temp.txt");
        FileWriter writer = new FileWriter(tempFile, false);
        writer.write("   ");
        writer.close();

        FileHandler fhTemp = new FileHandler("testFile.txt");
        //Solution adapted from https://www.perplexity.ai/search/how-to-print-from-file-in-java-FCd0GTNlTKmE4Ycj3waKmg#6
        Class<?> clazz = fhTemp.getClass();
        Method privateMethod = clazz.getDeclaredMethod("getTaskFromFile", String.class);

        privateMethod.setAccessible(true);

        String error = "Failed to load the task ("
                + ") due to invalid format.";

        Throwable cause = null;
        //Solution adpated from https://www.perplexity.ai/search/how-to-assert-equals-a-private-7_Vt5uKlTo6ZrIhZbrIY7A#3
        try {
            privateMethod.invoke(fhTemp, "");
        } catch (InvocationTargetException | IllegalAccessException e) {
            cause = e.getCause();
        }

        FileWriter cleanFile = new FileWriter(tempFile, false);
        cleanFile.write("");
        cleanFile.close();

        assertEquals(error, cause.getMessage());
    }

    @Test
    public void fileHandler_retrieveTasksFromFile_success() {
        try {
            File tempFile = new File(".dataTest/temp.txt");
            FileWriter writer = new FileWriter(tempFile, false);
            writer.write("ToDo |   | return book\n"
                    + "Deadline |   | return book | Jan 12 2025, 2000\n"
                    + "Event |   | project meeting | Mar 02 2024, 1000-Mar 02 2024, 1030"
            );
            writer.close();

            FileHandler fhTemp = new FileHandler(".dataTest/temp.txt");
            ArrayList<Task> actualListItems = fhTemp.retrieveTasksFromFile();

            ArrayList<Task> expectedListItems = new ArrayList<>();
            expectedListItems.add(new ToDo("return book"));
            expectedListItems.add(new Deadline("return book", "12/01/2025 2000"));
            expectedListItems.add(new Event("project meeting", "02/03/2024 1000", "02/03/2024 1030"));

            FileWriter cleanFile = new FileWriter(tempFile, false);
            cleanFile.write("");
            cleanFile.close();

            assertEquals(expectedListItems.toString(), actualListItems.toString());
        } catch (IOException e) {
            System.out.println("Testing failed");
        }
    }

    @Test
    public void fileHandler_retrieveTasksFromFileInvalidFormat_exceptionThrown() {
        FileHandler fhTemp = new FileHandler(".data");

        //Solution adapted from https://www.perplexity.ai/search/how-to-assertequal-a-thrown-er-mtR92GBxS9OyDApnjrM04A#5
        Exception exception = assertThrows(IOException.class, () -> {
            fhTemp.retrieveTasksFromFile();
        });
        assertEquals("Invalid file path given", exception.getMessage());
    }
}
