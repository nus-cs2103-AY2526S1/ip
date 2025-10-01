package cat;

public class Ui {
    public static String showWelcome() {
        return "Hello! I am your neighbourhood stray cat.\n"
                + "What can I do for you?\n";
    }

    public static String showAsk() {
        return "Enter your meows to record down as shown: \n"
                + "<t> | <todo>\n"
                + "<d> | <deadline> | <2/12/2019 1800>\n"
                + "<e> | <event> | <1/12/2019 1800> | <2/12/2019 1800>\n"
                + "<list>\n"
                + "<find> | <key>\n"
                + "<delete> | <index>\n"
                + "<mark> | <index>\n"
                + "<unmark> | <index>\n"
                + "<bye>\n";
    }


    public static String showBye() {
        return "Bye. Hope to see you again soon with Chicken Cat Treats\n";
    }

    public static String showInput(String userInput){
        return "You have Meowed: " + userInput + "\n";
    }

    public static String inValidInput(){
        return "Invalid Input\n";
    }

    public static String emptyInput(){
        return "empty Input\n";
    }


}
