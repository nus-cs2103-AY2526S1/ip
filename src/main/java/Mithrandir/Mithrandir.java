package Mithrandir;

import java.io.IOException;

public class Mithrandir {
    public static void main(String[] args) throws Exception {
        Application app = new Application();
        try {
            app.run();
        } catch (IOException e) {
            System.err.println("IOException");
        }
    }
}
