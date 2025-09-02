package LunarBot;

import LunarBot.Command.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Parser {
    public static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Command parse(String input) {
        String[] tmp = input.split(" ");
        String command = tmp[0];
        return switch (command) {
        case "list" -> new ListCommand();
        case "todo" -> new TodoCommand(input.substring(input.indexOf(" ") + 1));
        case "deadline" -> new DeadlineCommand((input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1)),
                LocalDateTime.parse(input.split("/by ")[1], SAVE_FORMAT));
        case "event" -> {
            String[] tmp2 = input.split("/from ")[1].split(" /to ");
            yield new EventCommand(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                    LocalDateTime.parse(tmp2[0], SAVE_FORMAT), LocalDateTime.parse(tmp2[1], SAVE_FORMAT));
        }
        case "mark" -> new MarkCommand(Integer.valueOf(tmp[1]) - 1);
        case "unmark" -> new UnmarkCommand(Integer.valueOf(tmp[1]) - 1);
        case "delete" -> new DeleteCommand(Integer.valueOf(tmp[1]) - 1);
        case "bye" -> new ByeCommand();
        default -> new AddCommand(input.substring(input.indexOf(" ") + 1));
        };
    }
}
