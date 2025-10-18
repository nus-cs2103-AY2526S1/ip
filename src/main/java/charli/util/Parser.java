package charli.util;

import charli.command.AddDeadlineCommand;
import charli.command.AddEventCommand;
import charli.command.AddTodoCommand;
import charli.command.Command;
import charli.command.DeleteCommand;
import charli.command.ExitCommand;
import charli.command.FindCommand;
import charli.command.ListCommand;
import charli.command.MarkCommand;
import charli.command.TagCommand;
import charli.exception.CharliException;

public class Parser {

    private static final String HELP_MESSAGE = """
            Please speak my language...
            
            COMMANDS YOU CAN MAKE:
            
            rotation                         
            played <n> | unplayed <n>       
            bop <song>               
            drop <song> /by <dd/mm/yyyy HHmm>     
            show <event> /from <time> /to <time> 
            delete <n>                       
            find <keyword>                   
            bye                              

            EXAMPLES:
            
            bop Apple
            drop "Rich Man" /by 19/2/2025 1800
            show "studio session" /from 2pm /to 3pm
            played 2
            
            Now, you try!
            """;

    public static Command parse(String input) throws CharliException {

        assert input != null : "User input is null";

        String[] parts = input.split(" ", 2);
        String command = parts[0];

        return switch (command) {
            case "rotation" -> new ListCommand();
            case "bye" -> new ExitCommand();
            case "bop" -> new AddTodoCommand(input);
            case "drop" -> new AddDeadlineCommand(input);
            case "show" -> new AddEventCommand(input);
            case "played" -> new MarkCommand(input, true);
            case "unplayed" -> new MarkCommand(input, false);
            case "delete" -> new DeleteCommand(input);
            case "find" -> new FindCommand(input);
            case "tag" -> new TagCommand(input, true);
            case "untag" -> new TagCommand(input, false);
            default -> throw new CharliException("You gave an unknown command: " + command + "\n" + HELP_MESSAGE);
        };
    }
}
