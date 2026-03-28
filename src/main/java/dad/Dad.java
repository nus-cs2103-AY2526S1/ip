package dad;
import dad.Ui;;
import java.util.Scanner;


public class Dad {

    private TaskList tasks;
    private Storage savedTasks;

    public Dad(String filename) {
        this.savedTasks = new Storage(filename);
        this.tasks = savedTasks.loadFile();
	assert this.tasks != null : "Tasks missing!";
    }

    /**
     * Returns the intial message
     */
    public String getIntro() {
	return Ui.printIntro();
    }

    /** 
     * Processes the prompt and returns an appropriate response
     * 
     * @param prompt The String that the bot will respond to
     * 
     * @return The String that contains the reply
     */
    public String getResponse(String prompt) {
        return Parser.parse(prompt, tasks);
    }

    /**
     * Saves the current TaskList into a storage file
     */
    public void saveTasks() {
        savedTasks.saveFile(tasks);
    }
}
