package ui;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import command.Command;
import command.CommandRegistry;
import task.TaskList;
import utils.Storage;

/** The main chatbot class, start the bot by calling the listen function */
public class Lmbd {
    /** A TaskList object associated with this class */
    private static boolean isLoadable = true;
    public final TaskList tasks;
    /** A static flag that disables loading the TaskList */

    private Scanner sc = new Scanner(System.in);

    /**
     * A map between a String representing the command and the corresponding Command
     * object
     */
    private final Map<String, Command> commands = new HashMap<>();

    private boolean exit = false;

    public Lmbd() {
        this("lmbd.save", CommandRegistry.getCommands());
    }

    /**
     * Creates a Lmbd object from the specified commands Uses the default lmbd.save
     * file
     */
    public Lmbd(Command... cmds) {
        this("lmbd.save", cmds);
    }

    /**
     * Creates a Lmbd object from the specified commands Uses the specified save
     * file
     */
    public Lmbd(String saveFile, Command... cmds) {
        for (Command command : cmds) {
            commands.put(command.getCmd(), command);
        }

        if (!isLoadable) {
            tasks = new TaskList();
            return;
        }

        TaskList temp;
        try {
            temp = Storage.load(saveFile);
        } catch (Exception e) {
            temp = new TaskList(saveFile);
        }

        tasks = temp;
    }

    /** Returns the commands stored in this Lmbd object */
    public Collection<Command> getCommands() {
        return commands.values();
    }

    /** Returns the command associated with the given command name */
    public Command getCommand(String cmdName) {
        return commands.get(cmdName);
    }

    /**
     * Returns a boolean indicating if the given command name is stored in this
     * object
     */
    public boolean isCommand(String cmdName) {
        return commands.containsKey(cmdName);
    }

    /**
     * Prints a greeting message
     *
     * @deprecated No longer in use since JavaFX was introduced
     */
    @Deprecated
    public void greet() {
        System.out.println("Hello! I'm LMBD. What can I do for you?");
    }

    /** Closes the chatbot */
    public void exit() {
        exit = true;
        sc.close();
        System.exit(0);
    }

    /**
     * Starts listening for commands from stdin
     *
     * @deprecated No longer in use since JavaFX was introduced
     */
    @Deprecated
    public void listen() {
        while (!exit) {
            System.out.print("$ ");
            if (sc.hasNextLine()) {
                String[] in = sc.nextLine().split(" ");
                handleCommand(in[0], Arrays.copyOfRange(in, 1, in.length));
            } else {
                break;
            }
        }
    }

    public String getResponse(String input) {
        String[] in = input.split(" ");
        return handleCommand(in[0], Arrays.copyOfRange(in, 1, in.length));
    }

    private String handleCommand(String cmdString, String[] args) {
        Command command = commands.getOrDefault(cmdString, null);
        if (command == null) {
            return String.format("Unrecognised command: %s", cmdString);
        }

        return command.call(this, args);
    }

    public void setTaskListSaveable(boolean s) {
        tasks.setIsSaveable(s);
    }

    public static void disableLoading() {
        isLoadable = false;
    }

    public static void enableLoading() {
        isLoadable = true;
    }
}
