package chiikawa;

import java.util.Scanner;

import chiikawa.task.DeadlineTask;
import chiikawa.task.EventTask;
import chiikawa.task.Task;
import chiikawa.task.ToDoTask;

/**
 * Class consisting of all outputs that users will see.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Returns the next line of input.
     *
     * @return next line of input by user.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints out what Chiikawa would say when bidding farewell.
     *
     * @return string representation of what Chiikawa will say.
     */
    public String showBye() {
        String output = "bye~ bye~!! soon... see soon? ₍ᐢ.  ̫.ᐢ₎";
        System.out.println(output);
        this.scanner.close();
        return output;
    }

    /**
     * Prints out what Chiikawa would say when showing users the list
     * and the actual list.
     *
     * @param taskList the taskList that the user is operating with.
     * @return string representation of what Chiikawa will say.
     */
    public String showList(TaskList taskList) {
        String output = "i- i twhink these you tasks...?\n";
        System.out.print(output);
        if (!taskList.toString().isEmpty()) {
            output += taskList + "\n";
            System.out.println(taskList);
        }

        return output;
    }

    /**
     * Prints out what Chiikawa would say when a task is marked as complete,
     * and the actual task itself.
     *
     * @param markedTask Task that has just been marked complete.
     * @return string representation of what Chiikawa will say.
     */
    public String showMark(Task markedTask) {
        String output = "okee! you finis~~\n";
        System.out.print(output);

        output += markedTask.toString();
        System.out.println(markedTask.toString());

        return output;
    }

    /**
     * Prints out what Chiikawa would say when a task is marked as incomplete,
     * and the actual task itself.
     *
     * @param unmarkedTask Task that has just been marked incomplete.
     * @return string representation of what Chiikawa will say.
     */
    public String showUnmark(Task unmarkedTask) {
        String output = "ohh.... slowpoke!!\n";
        System.out.print(output);

        output += unmarkedTask.toString();
        System.out.println(unmarkedTask.toString());

        return output;
    }

    /**
     * Prints out what Chiikawa would say when a task is deleted
     * and the actual task itself.
     *
     * @param deletedTask Task that has just been deleted.
     * @return string representation of what Chiikawa will say.
     */
    public String showDelete(Task deletedTask) {
        String output = "oke... i kill!! say bai bai to:\n";
        System.out.print(output);

        output = output + deletedTask.toString() + "\n";
        System.out.println(deletedTask.toString());

        output = output + "now only have " + Task.getTaskCount() + " tasks...";
        System.out.println("now only have " + Task.getTaskCount() + " tasks...");

        return output;
    }

    /**
     * Prints out what Chiikawa would say when a task has its priority updated.
     * @param updatedTask Task that has just had its priority updated.
     * @return String representation of what Chiikawa will say.
     */
    public String showUpdatePriority(Task updatedTask) {
        String output = "oke! pwiowity changedd!! now it iz:\n";
        output += updatedTask.toString();
        System.out.println(output);

        return output;
    }

    /**
     * Prints Chiikawa's reply to user finding tasks that contains keyword.
     *
     * @param keyword the word that we will try to match to tasks.
     * @return string representation of what Chiikawa will say.
     */
    public String showFind(String keyword) {
        String output = "okay.. i keep look out for: " + keyword;
        System.out.println(output);

        return output;
    }

    /**
     * Prints Chiikawa's reply to user finding tasks that contains keyword.
     *
     * @param keyword the word that we will try to match to tasks.
     * @return string representation of what Chiikawa will say.
     */
    public String showFilter(String keyword) {
        String output = "okay.. i help u fewter: " + keyword;
        System.out.println(output);

        return output;
    }

    /**
     * Prints out what Chiikawa would say when any new Task is added
     * and the actual task itself.
     *
     * @param newTask new Task that is created.
     * @return string representation of what Chiikawa will say.
     */
    public String showAddTask(Task newTask) {
        String output = "";

        if (newTask instanceof ToDoTask) {
            output += "wahhh! oke, me add add:";
        } else if (newTask instanceof DeadlineTask) {
            output += "ohh! de..deadline?! uuuhh, me add me add:";
        } else if (newTask instanceof EventTask) {
            output += "wowzies! yayy! can me go? me add me add:";
        }
        System.out.println(output);
        System.out.println("  " + newTask.toString());
        System.out.println("wuuu! " + Task.getTaskCount() + " tasks in list now!");

        output = output + "\n  " + newTask.toString() + "\n" + "wuuu! " + Task.getTaskCount() + " tasks in list now!";
        return output;
    }

    /**
     * Prints out what Chiikawa would say when user enters an invalid command.
     *
     * @return string representation of what Chiikawa will say.
     */
    public String showInvalid() {
        String output = "wha...wha? i dun kno... scawy...";
        System.out.println(output);

        return output;
    }

    /**
     * Prints out what Chiikawa would say when there is an error
     * and the error that is being thrown.
     *
     * @param errorMessage String representation of the error.
     * @return string representation of what Chiikawa will say.
     */
    public String showError(String errorMessage) {
        String output = "uh oh.." + errorMessage;
        System.out.println(output);

        return output;
    }

    /**
     * Prints out what Chiikawa would say when there is a loading error.
     *
     * @return string representation of what Chiikawa will say.
     */
    public String showLoadingError() {
        String output = "load loadin loadin pwobem!!";
        System.out.println(output);

        return output;
    }

    /**
     * Prints out Chiikawa's greeting.
     *
     * @return string representation of Chiikawa greeting.
     */
    public String showWelcome() {
        String chiikawaArt = "₍ᐢ.  ̫.ᐢ₎";

        showLine();

        String output = "";
        output = output + "hewwo~! " + chiikawaArt + "\n" + "me chiikawa!!\nyou... do somethin'?";
        System.out.println(output);

        return output;
    }

    /**
     * Prints a new line.
     */
    public void showLine() {
        System.out.println("__________________________________________________________________");
    }
}
