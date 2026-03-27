package mochi;

import javafx.util.Pair;

/**
 * Handles main Mochi application logic.
 */
public class Mochi {
    private final TaskList taskList;
    private final CommandParser cmd;
    private final Ui ui;

    /**
     * Constructor for Mochi application that takes in a data file name.
     *
     * @param fileName the name of the data file located in the /data folder
     */
    public Mochi(String fileName) {
        assert !fileName.isEmpty() : "Data file name not specified.";
        this.ui = new Ui();
        this.cmd = new CommandParser();
        FileHandler fh = new FileHandler(fileName);
        this.taskList = new TaskList(fh.load(), fh);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        cmd.read(input);
        return output();
    }

    /**
     * Main program flow for user input and command handling.
     */
    public String output() {
        try {
            if (cmd.is("list")) {
                ui.print(taskList.toString());
            } else if (cmd.is("mark")) {
                try {
                    ui.print(taskList.complete(cmd.markCommand(taskList.size())));
                } catch (MarkingException e) {
                    ui.error(e);
                }
            } else if (cmd.is("unmark")) {
                try {
                    ui.print(taskList.undo(cmd.unmarkCommand(taskList.size())));
                } catch (MarkingException e) {
                    ui.error(e);
                }
            } else if (cmd.is("todo")) {
                try {
                    ui.print(taskList.add(cmd.todoCommand()));
                } catch (ToDoException e) {
                    ui.error(e);
                }
            } else if (cmd.is("deadline")) {
                try {
                    ui.print(taskList.add(cmd.deadlineCommand()));
                } catch (DeadlineException e) {
                    ui.error(e);
                }
            } else if (cmd.is("event")) {
                try {
                    ui.print(taskList.add(cmd.eventCommand()));
                } catch (EventException e) {
                    ui.error(e);
                }
            } else if (cmd.is("delete")) {
                try {
                    ui.print(taskList.remove(cmd.deleteCommand(taskList.size())));
                    ui.print(taskList.toString());
                } catch (MochiException e) {
                    ui.error(e);
                }
            } else if (cmd.is("find")) {
                ui.print(taskList.getTasksWithWord(cmd.findCommand()));
            } else if (cmd.is("tag")) {
                try {
                    Pair<Integer, String> res = cmd.tagCommand();
                    ui.print(taskList.tag(res.getKey(), res.getValue()));
                } catch (MochiException e) {
                    ui.error(e);
                }
            } else if (cmd.is("untag")) {
                try {
                    Pair<Integer, String> res = cmd.tagCommand();
                    ui.print(taskList.untag(res.getKey(), res.getValue()));
                } catch (MochiException e) {
                    ui.error(e);
                }
            } else if (cmd.is("help")) {
                ui.showHelp();
            } else if (cmd.is("bye")) {
                ui.exit();
            } else {
                throw new MochiException();
            }
        } catch (Exception e) {
            ui.error(e);
        }
        return ui.output();
    }
}
