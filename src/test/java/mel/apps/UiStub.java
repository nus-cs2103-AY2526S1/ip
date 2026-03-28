package mel.apps;

import java.util.Scanner;

public class UiStub extends Ui {

    public String lastPrinted;

    public UiStub() {
        super(null);

    }

    @Override
    public void printOut(String s) {
        lastPrinted = s;

    }
}
