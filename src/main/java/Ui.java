import exceptions.DukeyException;

/* Ui to process user input */
class Ui {

    /* parser to decide how to respond to user input */
    private Parser parser;

    /**
     * Initialises ui with parser.
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Get user input and process it.
     * @param input user input.
     * @return response message.
     * @throws DukeyException if user input is unrecognised by parser.
     */
    public String reply(String input) throws DukeyException {
        //get the command
        String command = "";
        //the remaining user input
        String rest = "";

        int firstSpaceIndex = input.indexOf(" "); //find the first whitespace
        if (firstSpaceIndex > 0) {
            command = input.substring(0, firstSpaceIndex); //everything before first white space is the command
            rest = input.substring(firstSpaceIndex + 1); //remaining text
        } else {
            command = input;
            rest = "";
        }
        //send to parser to decide corresponding behaviour
        assert(command != "");
        return parser.parse(command, rest);
    }
}



