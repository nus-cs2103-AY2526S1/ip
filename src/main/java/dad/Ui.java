package dad;

class Ui {

    /**
     * Returns the intro message as a String
     */
     public static String printIntro() {
         return Ui.printLine() + "\n"
                 + "Whadd'ya want?" + "\n"
                 + Ui.printLine();
     }

    /**
     * Returns a String to make a line 
     */
    public static String printLine() {
        return "  ----------------------------------";
    }

    /**
     * Formats the given String for output
     *
     * @param contents The String to be printed to output
     *
     * @return The String formatted for output
     */
    public static String print(String contents) {
        return contents + "\n";
    }
}


