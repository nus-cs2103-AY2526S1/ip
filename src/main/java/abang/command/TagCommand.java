package abang.command;

import abang.task.TaskList;
import abang.ui.UI;
import abang.storage.Storage;
import abang.exception.AbangException;

public class TagCommand extends Command {

    private String[] inputArray;

    public TagCommand(String[] inputArray) {
        this.inputArray = inputArray;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws AbangException {
        if (inputArray.length < 2) {
            throw new AbangException("Please provide a task number and description to tag");
        }
        String description = inputArray[1];
        String[] descriptionArray = description.split(" ", 2);
        String number = descriptionArray[0].trim();
        String tagDescription = descriptionArray[1].trim();
        int index = Integer.parseInt(number);
        if (index < 1 || index > taskList.numTask()) {
            throw new AbangException("Please key in a valid number");
        }

        taskList.tag(index, tagDescription);

        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've tagged this task as ");
        sb.append(tagDescription + "\n");
        sb.append("  ").append(taskList.getTask(index));

        storage.save(taskList.toFileLines());
        return sb.toString();
    }
}
