package Leo.Display;

import Leo.Functions.Functions;
import Leo.ZeroLengthException;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;

/**
 * The UI class manages the interaction between the user and the chatbot,
 * outputting messages to the user interface and receiving input from the user.
 */
public class UI {
    //Solution below adapted from https://www.perplexity.ai/search/how-to-obtain-user-input-in-cl-KvQBaeNySBqYFdBIYZJrjw
    Scanner scanner = new Scanner(System.in);
    Functions func;

    /**
     * Takes in user input from the command line.
     *
     * @param functions An object that contains the possible functions of the chatbot.
     */
    public void getUserInput(Functions functions) {
        this.func = functions;
        Messages.Greetings();

        //Solution of using equalsIgnoreCase() suggested by IntelliJ code completion
        String callFunction = "";
        while(!callFunction.equalsIgnoreCase("bye")) {
            callFunction = scanner.nextLine();
            //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
            try {
                func.SearchFunctions(callFunction);
            }
            catch (ZeroLengthException |
                   IndexOutOfBoundsException |
                   IOException |
                   DateTimeException |
                   NumberFormatException
                    e){
                System.out.println(
                        "\t"
                        + e.getMessage()
                );
            }
            Messages.MessageBreak();
        }

        scanner.close();
    }
}
