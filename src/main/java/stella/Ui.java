package stella;

import stella.exception.ExcessParameterException;
import stella.exception.IncompleteInstructionException;
import stella.exception.InsufficientParameterException;
import stella.exception.UnknownInstructionException;

/**
 * Deals with the interactions with the user,
 * including greeting, responding to unexpected responses from user and farewell.
 */
public class Ui {
    private Parser parser;

    /**
     * Constructs a new Ui object with a specified parser.
     *
     * @param parser The parser.
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Interacts with user, while catching exceptions when user provide unexpected reply.
     *
     * @param userInput user's command.
     * @return Stella's response.
     */
    public String callInteraction(String userInput) {
        try {
            if (userInput.equals("bye")) {
                return "GoodBye!";
            } else {
                return parser.findCommand(userInput);
            }
        } catch (UnknownInstructionException e) {
            return e.getMessage() + " is invalid. Type new message: ";
        } catch (IncompleteInstructionException e) {
            return e.getMessage() + " is incomplete. Type new message: ";
        } catch (ExcessParameterException | InsufficientParameterException e) {
            return e.getMessage() + "Type new message: ";
        } catch (Exception e) {
            return "Did you key in the correct message? Type new message: ";
        }
    }
}
