package arn;
import java.util.ArrayList;

public class Gui extends Ui {
    protected  ArrayList<String> responses;

    public Gui() {
        super();
        responses = new ArrayList<>();
    }

    @Override
    public void displayMsg(String msg) {
        responses.add(msg);
    }

    @Override
    public void displayBye() {
        responses.add("Bye. Hope to see you again soon!");
    }

    public String getResponses() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < responses.size(); i++) {
            sb.append(responses.get(i));
            if (i < responses.size() - 1) {
                sb.append("\n");
            }
        }
        String result =  sb.toString();
        responses.clear();
        return result;
    }
}
