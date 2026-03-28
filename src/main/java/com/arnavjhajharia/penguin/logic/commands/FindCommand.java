package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.PenguinException;
import com.arnavjhajharia.penguin.model.TaskList;

/**
 * Command to find tasks containing given keywords.
 */
public final class FindCommand implements Command {
    private final String query;


    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public CommandResult execute(TaskList tasks) throws PenguinException {
        StringBuilder result = tasks.find(query);
        return CommandResult.of(result.toString());
    }
}
