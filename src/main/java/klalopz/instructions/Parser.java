package klalopz.instructions;

import klalopz.enums.InstructionKeyword;
import klalopz.exceptions.KlalopzException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Responsible for parsing user input and returning the corresponding instruction.
 * The parser separates the command keyword from its arguments and creates
 * the appropriate Instruction object.
 */
public class Parser {

    /**
     * Parses the input string and returns the corresponding Instruction.
     * The first word of the input is treated as the command, and the remainder
     * (if any) as arguments for that command.
     *
     * @param input User input string containing the command and optional arguments.
     * @return The Instruction object corresponding to the parsed command.
     */
    public static Instruction parse(String input) {
        assert input != null : "Input string should not be null";

        String[] splitInput = input.split(" ", 2);
        String instruction = splitInput[0];
        assert !instruction.isBlank() : "Command keyword must not be blank";

        String arguments = splitInput.length > 1 ?  splitInput[1] : "";

        InstructionKeyword keyword;
        try {
            keyword = InstructionKeyword.fromString(instruction);
        } catch (IllegalArgumentException e) {
            return new InvalidInstruction("You inputted an invalid command, please refer to the website for all available commands!");
        }

        return switch (keyword) {
            case LIST -> new ListInstruction();

            case MARK -> {
                if (arguments.isEmpty()) {
                    yield new InvalidInstruction("Missing arguments for MARK");
                }
                yield new MarkInstruction(arguments); // 1 Argument
            }

            case UNMARK -> {
                if (arguments.isEmpty()) {
                    yield new InvalidInstruction("Missing arguments for UNMARK");
                }
                yield new UnmarkInstruction(arguments); // 1 Argument
            }

            case FIND -> {
                if (arguments.isEmpty()) {
                    yield new InvalidInstruction("Missing arguments for FIND");
                }
                yield new FindInstruction(arguments); // 1 Argument
            }

            case DEADLINE -> {
                if (!arguments.contains("/")) {
                    yield new InvalidInstruction("Klalopz doesn't understand this format. (deadline details /dd-mm-yyyy)");
                }

                String[] tempStorage = arguments.split("/", 2);
                if (tempStorage.length < 2 || tempStorage[0].isBlank() || tempStorage[1].isBlank()) {
                    yield new InvalidInstruction("Klalopz doesn't understand this format. (deadline details /dd-mm-yyyy)");
                }

                try {
                    LocalDate.parse(tempStorage[1].trim(), Instruction.inputDateFormat);
                } catch (DateTimeParseException e) {
                    yield new InvalidInstruction("Klalopz doesn't know this date format! Please use dd-MM-yyyy.");
                }

                yield new DeadlineInstruction(arguments);
            }

            case EVENT -> {
                String[] parts = arguments.split("/", 3);
                if (parts.length < 3 || parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
                    yield new InvalidInstruction("Klalopz doesn't understand this format." +
                            " Please use (event title / startDate / endDate)");
                }

                try {
                    LocalDate.parse(parts[1].trim(), Instruction.inputDateFormat);
                    LocalDate.parse(parts[2].trim(), Instruction.inputDateFormat);
                } catch (Exception e) {
                    yield new InvalidInstruction("Klalopz doesn't know this date format! Please use dd-MM-yyyy.");
                }

                yield new EventInstruction(arguments);
            }

            case TODO -> {
                if (arguments.isEmpty()) {
                    yield new InvalidInstruction("Missing arguments for TODO");
                }
                yield new ToDoInstruction(arguments); // 1 Argument
            }

            case DELETE -> {
                if (arguments.isEmpty()) {
                    yield new InvalidInstruction("Missing arguments for DELETE");
                }
                yield new DeleteInstruction(arguments); // 1 Argument
            }

            case ADD_TAG -> {
                if (arguments.isEmpty()) {
                    yield new InvalidInstruction("Missing arguments for ADD_TAG");
                }

                String[] splitArgs = arguments.trim().split(" ", 2); // split into at most 2 parts

                if (splitArgs.length < 2) {
                    yield new InvalidInstruction("Invalid format! Usage: add_tag {index} {tag_num/name}");
                }

                String tagPart = splitArgs[1].trim();

                if (tagPart.isEmpty()) {
                    yield new InvalidInstruction("Tag cannot be empty! Usage: add_tag {index} {tag_num/name}");
                }

                yield new SetTagInstruction(arguments);
            }

            case DELETE_TAG -> {
                if (arguments.isEmpty()) {
                    yield new InvalidInstruction("Missing arguments for DELETE_TAG");
                }
                yield new DeleteTagInstruction(arguments); // 2 Arguments
            }

            case HELP -> new HelpInstruction();

            case EXIT -> new ExitInstruction();
        };


    }
}
