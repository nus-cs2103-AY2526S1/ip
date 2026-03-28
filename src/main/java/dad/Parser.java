package dad;

import java.util.Arrays;
import dad.commands.Command;

public class Parser {

    /**
      * Parses the given raw text into its component command and arguments and returns an appropriate response.
      * 
      * @param rawText The raw input
      * @param taskList The current list of tasks
      * @return The String to be fed to the output 
      */
    public static String parse(String rawText, TaskList tasks) {
        try {
            String[] commands = rawText.split(" ");

            Command cmd = Command.of(commands, tasks);
            return cmd.execute();
        } catch (DadException err) {
            return err.toString();
        }
    }
}



