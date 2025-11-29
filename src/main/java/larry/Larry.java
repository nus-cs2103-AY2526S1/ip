package larry;

import java.util.Scanner;

import larry.model.Deadline;
import larry.model.Event;
import larry.model.Task;
import larry.model.TaskList;
import larry.model.Todo;
import larry.parser.Parser;
import larry.storage.Storage;
import larry.ui.Ui;

/**
 * Entry pt. of the Larry application.
 * Wires Ui, Storage and TaskList, and dispatches user commands.
 */
public class Larry {

    /** Starts the console application. */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("data/larry.txt");
        TaskList tasks = new TaskList(storage.load());

        ui.showGreeting();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            String cmd = Parser.commandWord(input);

            if ("bye".equals(cmd)) {
                break;
            }

            switch (cmd) {
                case "find": {
                    String keyword = Parser.argTail(input, "find").trim();
                    if (keyword.isEmpty()) {
                        ui.showError("Usage: find <keyword>");
                        break;
                    }
                    java.util.List<larry.model.Task> hits = tasks.find(keyword);
                    ui.showFound(hits, keyword);
                    break;
                }
                case "list": {
                    ui.showList(tasks.asList());
                    break;
                }
                case "mark": {
                    int idx = Parser.parseIndex(Parser.argTail(input, "mark"));
                    if (!tasks.isValidIndex(idx)) {
                        ui.showError("Invalid task index.");
                        break;
                    }
                    Task t = tasks.get(idx);
                    t.markDone();
                    ui.showMarked(t);
                    storage.save(tasks.asList());
                    break;
                }
                case "unmark": {
                    int idx = Parser.parseIndex(Parser.argTail(input, "unmark"));
                    if (!tasks.isValidIndex(idx)) {
                        ui.showError("Invalid task index.");
                        break;
                    }
                    Task t = tasks.get(idx);
                    t.markUndone();
                    ui.showUnmarked(t);
                    storage.save(tasks.asList());
                    break;
                }
                case "delete": {
                    int idx = Parser.parseIndex(Parser.argTail(input, "delete"));
                    if (!tasks.isValidIndex(idx)) {
                        ui.showError("Invalid task index.");
                        break;
                    }
                    Task removed = tasks.delete(idx);
                    ui.showDeleted(removed, tasks.size());
                    storage.save(tasks.asList());
                    break;
                }
                case "todo": {
                    String desc = Parser.argTail(input, "todo");
                    if (desc.isEmpty()) {
                        ui.showError("OOPS!!! The description of a todo cannot be empty.");
                        break;
                    }
                    Task t = new Todo(desc);
                    tasks.add(t);
                    ui.showAdded(t, tasks.size());
                    storage.save(tasks.asList());
                    break;
                }
                case "deadline": {
                    String body = Parser.argTail(input, "deadline");
                    int byIdx = body.toLowerCase().indexOf("/by");
                    String desc = (byIdx == -1) ? body : body.substring(0, byIdx).trim();
                    String by   = (byIdx == -1) ? ""   : body.substring(byIdx + 3).trim();

                    if (desc.isEmpty()) {
                        ui.showError("OOPS!!! The description of a deadline cannot be empty.");
                        break;
                    }
                    if (byIdx == -1 || by.isEmpty()) {
                        ui.showError("OOPS!!! Please specify a due time using '/by <when>'.");
                        break;
                    }

                    Task t = new Deadline(desc, by);
                    tasks.add(t);
                    ui.showAdded(t, tasks.size());
                    storage.save(tasks.asList());
                    break;
                }
                case "event": {
                    String body = Parser.argTail(input, "event");
                    int fromIdx = body.toLowerCase().indexOf("/from");
                    int toIdx   = body.toLowerCase().indexOf("/to");
                    String desc = (fromIdx == -1) ? body : body.substring(0, fromIdx).trim();
                    String from = (fromIdx == -1) ? ""   : body.substring(fromIdx + 5, (toIdx == -1 ? body.length() : toIdx)).trim();
                    String to   = (toIdx   == -1) ? ""   : body.substring(toIdx + 3).trim();
                    if (desc.isEmpty()) {
                        ui.showError("OOPS!!! The description of an event cannot be empty.");
                        break;
                    }
                    if (fromIdx == -1 || from.isEmpty()) {
                        ui.showError("OOPS!!! Please specify a start time using '/from <start>'.");
                        break;
                    }
                    if (toIdx == -1 || to.isEmpty()) {
                        ui.showError("OOPS!!! Please specify an end time using '/to <end>'.");
                        break;
                    }

                    Task t = new Event(desc, from, to);
                    tasks.add(t);
                    ui.showAdded(t, tasks.size());
                    storage.save(tasks.asList());
                    break;
                }
                case "help": {
                    ui.showHelp();
                    break;
                }
                default: {
                    if (!input.isEmpty()) {
                        ui.showError("OOPS!!! I'm sorry, but I don't know what that means.");
                    }
                    break;
                }
            }
        }

        ui.showExit();
    }
}
