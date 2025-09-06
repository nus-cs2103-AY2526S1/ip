package phuc.gui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Utility class to capture System.out output and convert it to strings.
 */
public class OutputCapture {
    private final ByteArrayOutputStream outputStream;
    private final PrintStream originalOut;
    private final PrintStream captureStream;

    /**
     * Constructs a new OutputCapture instance for capturing System.out output.
     * Initializes the capture streams and stores the original System.out reference.
     * Uses UTF-8 encoding for character handling.
     *
     * @throws RuntimeException if UTF-8 encoding is not supported
     */
    public OutputCapture() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        try {
            captureStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported", e);
        }
    }

    /**
     * Starts capturing System.out output.
     */
    public void startCapture() {
        System.setOut(captureStream);
        outputStream.reset();
    }

    /**
     * Stops capturing and returns the captured output as a string.
     *
     * @return the captured output
     */
    public String stopCapture() {
        System.setOut(originalOut);
        captureStream.flush();
        try {
            String temp = outputStream.toString(StandardCharsets.UTF_8.name());
            temp = temp.substring(41, temp.length() - 41);
            return temp;
        } catch (UnsupportedEncodingException e) {
            return outputStream.toString();
        }
    }

    /**
     * Captures output from a Runnable task and returns it as a string.
     *
     * @param task the task to execute while capturing output
     * @return the captured output
     */
    public static String captureOutput(Runnable task) {
        OutputCapture capture = new OutputCapture();
        capture.startCapture();
        task.run();
        return capture.stopCapture();
    }
}
