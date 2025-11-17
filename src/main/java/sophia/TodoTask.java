package sophia;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Contains information of what to do in task
 */
public final class TodoTask extends Task {

    public TodoTask(String input) {
        super(input);
    }

    /**
     * For TodoTask to write its information
     * @param bw to write into specified file
     * @throws IOException
     */
    @Override
    public void write(BufferedWriter bw) throws IOException {
        bw.write("T | " + (isDone() ? 1 : 0) + " | " + getName() + "\n");
        bw.flush();
    }
    /**
     * String representation of TodoTask
     * **/
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
