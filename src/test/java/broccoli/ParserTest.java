package broccoli;

import broccoli.Tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
  TaskList  taskList = new TaskList(new ArrayList<Task>());
  Storage storage = new Storage(taskList, "./data/dataForTest.txt");
  Ui userInterface = new Ui();
    @Test
    public void isProcessCommmand_withByeCommand_success(){
        Scanner testScanner = new Scanner("bye\n");
        Parser testParser = new Parser(storage, userInterface, testScanner );
        String output = testParser.processCommand("bye", taskList);
       // System.out.println("captured:" + output);
        assertTrue(output.contains("Bye! Wish you come back with tasks done!"));
    }
}
