package com.oadultradeepfield.smartotter.command;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.oadultradeepfield.smartotter.SmartOtterException;

/**
 * Enum representing all supported command types in SmartOtter.
 * Each command type is associated with a keyword and a factory
 * method to create its corresponding {@link Executable} instance.
 */
public enum CommandType {
    TODO("todo", AddTodoCommand::fromInput),
    DEADLINE("deadline", AddDeadlineTaskCommand::fromInput),
    EVENT("event", AddEventTaskCommand::fromInput),
    LIST("list", ListCommand::fromInput),
    FIND("find", FindCommand::fromInput),
    TODAY("today", TodayCommand::fromInput),
    BYE("bye", ByeCommand::fromInput),
    MARK("mark", MarkCommand::fromInput),
    UNMARK("unmark", UnmarkCommand::fromInput),
    DELETE("delete", DeleteCommand::fromInput);

    private final String keyword;
    private final CommandFactory factory;

    CommandType(String keyword, CommandFactory factory) {
        this.keyword = keyword;
        this.factory = factory;
    }

    /**
     * Returns the {@link CommandType} corresponding to the given keyword.
     *
     * @param keyword the command keyword to look up
     * @return the matching {@link CommandType}, or null if no match is found
     */
    public static CommandType fromKeyword(String keyword) {
        return Arrays.stream(values())
            .filter(cmd -> cmd.keyword.equalsIgnoreCase(keyword))
            .findFirst()
            .orElse(null);
    }

    public Executable create(String input) throws SmartOtterException {
        return factory.create(input);
    }

    /**
     * Returns all command keywords for fuzzy matching.
     */
    public static Set<String> allKeywords() {
        return Arrays.stream(values())
            .map(CommandType::keyword)
            .collect(Collectors.toSet());
    }

    private String keyword() {
        return keyword;
    }

    @FunctionalInterface
    private interface CommandFactory {
        Executable create(String input) throws SmartOtterException;
    }
}
