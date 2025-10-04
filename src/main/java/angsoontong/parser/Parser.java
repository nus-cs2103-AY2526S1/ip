package angsoontong.parser;

import angsoontong.task.*;
import angsoontong.ui.Ui;
import angsoontong.storage.Storage;

public class Parser {
    /**
     * helper that runs runnable, and then subsequently saves mutated task list
     * @param mutation Runnable executing the command involved
     * @param storage Storage to save the new task list to
     * @param tasks TaskList to call the command on
     */
    private static void mutateAndSave(Runnable mutation, TaskList tasks, Storage storage) {
        mutation.run();
        tasks.save(storage);
    }

    /**
     * main parsing command to read input
     * @param input String input from the user
     * @param tasks TaskList containing current tasks
     * @param ui UI to receive String messages from to reply user
     * @param storage To write changes in task list to
     */
    public static String parse(String input, TaskList tasks, Ui ui, Storage storage) {
        String[] words = input.split(" ");
        String command = words[0];

        switch (command) {
            case "bye":
                return "Bye. Why you still here?!";

            case "sing":
                return "OK 来!";

            case "list":
                return ui.showList(tasks);

            case "mark":
                int markIndex = Integer.parseInt(words[1]) - 1;
                Task marked = tasks.get(markIndex);

                mutateAndSave(() -> marked.markDone(), tasks, storage);
                return ui.showMarked(marked);

            case "unmark":
                int unmarkIndex = Integer.parseInt(words[1]) - 1;
                Task unmarked = tasks.get(unmarkIndex);

                mutateAndSave(() -> unmarked.markUndone(), tasks, storage);
                return ui.showUnmarked(unmarked);

            case "delete":
                int deleteIndex = Integer.parseInt(words[1]) - 1;
                Task deleted = tasks.delete(deleteIndex);

                tasks.save(storage);
                return ui.showDeleted(deleted, tasks.size());

            case "find":
                String keyword = input.length() > 4 ? input.substring(5).trim() : "";
                if (keyword.isEmpty()) {
                    ui.show("Eh, tell me what to find leh! (e.g., find book)");
                }
                var indices = tasks.findIndices(keyword);
                return ui.showFindResults(indices, tasks);

            case "todo":
                Task todo = new ToDo(input.substring(5));

                mutateAndSave(() -> tasks.add(todo), tasks, storage);
                return ui.showAdded(todo, tasks.size());

            case "deadline":
                String[] deadlineParts = input.split("/by ");
                Task deadline = new Deadline(deadlineParts[0].substring(9), deadlineParts[1]);

                mutateAndSave(() -> tasks.add(deadline), tasks, storage);
                return ui.showAdded(deadline, tasks.size());

            case "event":
                String[] eventParts = input.split("/from |/to ");
                Task event = new Event(eventParts[0].substring(6), eventParts[1], eventParts[2]);

                mutateAndSave(() -> tasks.add(event), tasks, storage);
                return ui.showAdded(event, tasks.size());

            case "tag":
                // Usage: tag <index> #tag1 #tag2 ...
                String[] w = input.trim().split("\\s+");
                if (w.length < 3) {
                    return ui.show("Eh use properly! : tag <index> #tag1 #tag2");
                }
                int index;

                try {
                    index = Integer.parseInt(w[1]) - 1;
                } catch (NumberFormatException e) {
                    return ui.show("Eh use properly! : tag <index> #tag1 #tag2");
                }

                if (index < 0 || index >= tasks.size()) {
                    return "Index out of range.";
                }
                Task t = tasks.get(index);

                // collect the rest as tags (normalize in Task)
                java.util.List<String> tags = new java.util.ArrayList<>();
                for (int i = 2; i < w.length; i++) tags.add(w[i]);

                t.addTags(tags);
                tasks.save(storage);
                return ui.showTagged(t);

            case "untag": {
                // Usage: untag <index> #tag1 #tag2 ...
                w = input.trim().split("\\s+");
                if (w.length < 3) {
                    return ui.show("Eh use properly! : untag <index> [#tag1 #tag2 ... | all]");
                }

                int idx;
                try {
                    idx = Integer.parseInt(w[1]) - 1;
                } catch (NumberFormatException e) {
                    return ui.show("Eh use properly! : untag <index> [#tag1 #tag2 ... | all]");
                }
                if (idx < 0 || idx >= tasks.size()) {
                    return "Index out of range.";
                }

                t = tasks.get(idx);

                if (w.length == 3 && (w[2].equalsIgnoreCase("all")
                        || w[2].equalsIgnoreCase("--all"))) {
                    t.clearTags();
                    tasks.save(storage);
                    return ui.showUntagged(t);
                }

                java.util.List<String> tagsToRemove = new java.util.ArrayList<>();
                for (int i = 2; i < w.length; i++) tagsToRemove.add(w[i]);

                t.removeTags(tagsToRemove);
                tasks.save(storage);
                return ui.showUntagged(t);
            }

            default:
                return "Eh! Say properly leh, I don't know what that means la!\n";
        }
    }
}
