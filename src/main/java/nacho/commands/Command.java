package nacho.commands;

import nacho.ChatContext;

// Interface inspired by https://medium.com/@neerukapoor/command-design-pattern-in-java-7d06dfdd31
/**
 * Interface of Commands
 * <p>
 *     Every command should have an execute method
 * </p>
 */
public interface Command {
    public void execute(String[] args, ChatContext context);

}
